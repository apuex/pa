package com.github.apuex.pa.mhs;

import com.github.apuex.pa.mhs.impl.*;
import com.github.apuex.pa.utility.Utility;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Session;

public class PAMessageConverter {
  private String pathName;
  private String destName;

  public BytesMessage toBytesMessage(PABase pa, Session ssn) throws JMSException {
    BytesMessage bytesMessage = ssn.createBytesMessage();
    byte msg[]=pa.classToByte();
    bytesMessage.writeBytes(msg);
    bytesMessage.setStringProperty("PathName", pathName);
    bytesMessage.setIntProperty("Lable", pa.getType());
    bytesMessage.setStringProperty("DestName", destName);
    bytesMessage.setJMSPriority(pa.getPriority());
    return bytesMessage;
  }

  public PABase fromBytesMessage(BytesMessage bm) throws JMSException {
    int nLen=(int)bm.getBodyLength();
    byte b[]=new byte[nLen];
    bm.readBytes(b);

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
      case PAMessage.DATA_OBJECT_CHANGE:
      case PAMessage.CONFIG_CHANGE:
        pPA=new PAConfigChange();
        //System.out.println("配置修改");
        break;
      case PAMessage.MHUSTATUS_REPORT:
      case PAMessage.SQL_EXC_STATUS:
      case PAMessage.MHUINFOREQUEST:
      case PAMessage.BASE:
      case PAMessage.FSU_STATUS:
      case PAMessage.RAWIO:
      case PAMessage.RAWIO_RESULT:
      case PAMessage.DATE_TIME:
        System.out.println("default to PABase, message type: " + msgType);
        //System.out.println("校时原语");
        break;
      case PAMessage.USER_SQL_TEXT:
        pPA=new PAUserSQL();
        //System.out.println("写库原语");
        break;
      case PAMessage.BINTERFACE_CONFIG_CHANGE:
        System.out.println("BINTERFACE_CONFIG_CHANGE default to PABase, message type: " + msgType);
        //System.out.println("配置修改");
        pPA=new PAConfigChange();
        break;
      case PAMessage.AI_HISDATA:
        //System.out.println("AI历史数据");
        pPA=new PAAiHisData();
        break;
      case PAMessage.DI_HISDATA:
        //System.out.println("DI历史数据");
        pPA=new PADiHisData();
        break;
      default:
        System.out.println("Un supported message type: " + msgType);
    }
    if(null != pPA) pPA.byteToClass(b);
    return pPA;
  }

  public void setPathName(String pathName) {
    this.pathName = pathName;
  }

  public void setDestName(String destName) {
    this.destName = destName;
  }
}
