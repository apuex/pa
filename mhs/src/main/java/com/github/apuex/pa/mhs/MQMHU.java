package com.github.apuex.pa.mhs;

import java.net.InetAddress;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.Topic;
import com.sun.messaging.jms.TopicConnection;
import com.github.apuex.pa.mhs.impl.PAAlarm;
import com.github.apuex.pa.mhs.impl.PAAlarmAckInfo;
import com.github.apuex.pa.mhs.impl.PAConfigChange;
import com.github.apuex.pa.mhs.impl.PADataRequest;
import com.github.apuex.pa.mhs.impl.PADataRespond;
import com.github.apuex.pa.mhs.impl.PAOperationReport;
import com.github.apuex.pa.mhs.impl.PAOperationRequest;
import com.github.apuex.pa.utility.Utility;

public class MQMHU implements MessageListener {
	private TopicConnection con;
	private TopicSession topicsession;
	private MQBrokerBase broker=null;
	private TopicPublisher publisher[]=new TopicPublisher[PAMessage.MAXPAMessage];
	private String PathName;
	private String DestName;
	private boolean connectState=false;
	public MQMHU(MQBrokerBase mq,String ip,int port,String user,String pwd){
		try{
			broker=mq;
			InetAddress netAddress=InetAddress.getLocalHost();
			PathName=String.format("%s\\%s", netAddress.getHostName(),PAMessage.PathName);//netAddress.getHostName()+"\\_REAL_DATA_SERVER";
			DestName=PAMessage.DestName;
			String portStr=String.format("%d", port);
			com.sun.messaging.TopicConnectionFactory qcf = new com.sun.messaging.TopicConnectionFactory();
			qcf.setProperty(ConnectionConfiguration.imqBrokerHostName,ip);
			qcf.setProperty(ConnectionConfiguration.imqBrokerHostPort,portStr);
			con=(TopicConnection) qcf.createTopicConnection(user,pwd);
			topicsession=con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		}catch (Exception  e) {
			e.printStackTrace();
			System.out.println("MQMHU-与消息断开");
			connectState=false;
		}
	}
	
	public void register(int nMessageType){
		try{
			Topic topic=new Topic(PAMessage.PAMHS[nMessageType]);
			TopicSubscriber subscriber=topicsession.createSubscriber(topic);
			publisher[nMessageType]=topicsession.createPublisher(topic);
			subscriber.setMessageListener(this);
		}catch (Exception  e) {
			e.printStackTrace();
			connectState=false;
			System.out.println("register-与消息断开");
		}
	}
	public boolean connect(){
		try{
			con.start();
			connectState=true;
		}catch (Exception  e) {
			e.printStackTrace();
			connectState=false;
			System.out.println("connect-与消息断开");
			return false;
		}
		return true;
	}
	public boolean close(){
		try{
			topicsession.close();
			con.close();
			connectState=false;
		}catch (Exception  e) {
			e.printStackTrace();
			System.out.println("close-与消息断开");
			connectState=false;
			return false;
		}
		return true;
	}
	public void run(){
	}
	public short ByteToShort(byte pMsg[],int nSize){
		short v=-1;
		v=(short) (pMsg[nSize+1]&0xFF);
		v=(short) ((v<<8)|(pMsg[nSize]&0xFF));
		return v;
	}
	public void onMessage(Message m) {
		try {
			BytesMessage msg1=(BytesMessage)m;
			int nLen=(int)msg1.getBodyLength();
			byte b[]=new byte[nLen];
			msg1.readBytes(b);
			
			short msgType=Utility.byteToshort(b, 0);
			PABase pPA=null;
			/*
			 告警原语：ALARM
			实时数据请求：DATA_REQUEST
			实时数据相应：DATA_RESPOND
			
			告警确认原语：ALARM_ACK_INFO
			FSU状态原语：FSU_STATUS
			写数据值原语：CPA_CLASS_OPERATION_REQUEST
			CPAOperationRequest
			 */
			
			switch(msgType){
			case PAMessage.ALARM:
				pPA=new PAAlarm();
				break;
			case PAMessage.ALARM_ACK_INFO:
				pPA=new PAAlarmAckInfo();
				//System.out.println("告警确认原语:");
				//Utility.printHex(b);
				break;
			case PAMessage.ALARM_REQUEST:
				//System.out.println("告警请求原语");
				break;
			case PAMessage.DATA_RESPOND:
				pPA=new PADataRespond();
				//System.out.println("实时数据响应原语");
				break;
			case PAMessage.DATA_REQUEST:
				pPA=new PADataRequest();
				//System.out.println("数据请求原语");
				break;
			case PAMessage.OPERATION_REQUEST:
				pPA=new PAOperationRequest();
				//System.out.println("数据请求原语");
				break;
			case PAMessage.OPERATION_REPORT:
				pPA=new PAOperationReport();
				//System.out.println("数据请求原语");
				break;
			case PAMessage.DATE_TIME:
				//System.out.println("校时原语");
				break;
			case PAMessage.USER_SQL_TEXT:
				//System.out.println("写库原语");
				break;
			case PAMessage.BINTERFACE_CONFIG_CHANGE:
				//System.out.println("配置修改");
				pPA=new PAConfigChange();
				break;
			default:
				break;
			}
			if(pPA!=null){
				pPA.byteToClass(b);
				if(broker!=null){
					broker.onMessage(pPA,msgType,nLen);
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
			connectState=false;
			System.out.println("onMessage-与消息断开");
		}
	}
	public boolean SendPA(PABase pa){
		try{
			BytesMessage msgbyte = topicsession.createBytesMessage();
			byte msg[]=pa.classToByte();
			msgbyte.writeBytes(msg);
			msgbyte.setStringProperty("PathName", PathName);
			msgbyte.setIntProperty("Lable", pa.getType());
			msgbyte.setStringProperty("DestName", DestName);
			msgbyte.setJMSPriority(pa.getPriority());
			publisher[pa.getType()].publish(msgbyte);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("SendPA-与消息断开");
			connectState=false;
		}
		return connectState;
	}
}
