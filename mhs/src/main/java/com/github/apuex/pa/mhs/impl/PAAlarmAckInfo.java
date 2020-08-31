package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PAAlarmAckInfo extends PABase {
	private int priority;//
	private short type;//2
	private short version;//2
	private int   dwID;//数据对象ID
	private long RiseTime;//发生时间
	private int nACK;//确认状态
	private int    Repair;//是否派休
	private long  conf_time;//告警确认时间
	private int	    dwUserID;//用户ID
	private String	strUsername;//用户名称
	private int  dwMessageStatus;
	public PAAlarmAckInfo(){
		priority=PAMessage.Priority[PAMessage.ALARM_ACK_INFO];
		type=PAMessage.ALARM_ACK_INFO;
		version=PAMessage.VERSION;
		dwID		= 0;
		RiseTime=0;
		nACK = 0;	
		Repair=0;
		conf_time=0;
		dwUserID = 0;
		strUsername = "";
		dwMessageStatus = 0;
	}
	public void setDwID(int dwId){
		this.dwID=dwId;
	}
	public void setRiseTime(long RiseTime){
		this.RiseTime=RiseTime;
	}
	public void setACK(int nACK){
		this.nACK=nACK;
	}
	public void setRepair(int Repair){
		this.Repair=Repair;
	}
	public void setConf_time(long conf_time){
		this.conf_time=conf_time;
	}
	public void setUserID(int dwUserID){
		this.dwUserID=dwUserID;
	}
	public void setUsername(String Username){
		this.strUsername=Username;
	}
	public void setMessageStatus(int MessageStatus){
		this.dwMessageStatus=MessageStatus;
	}
	public void byteToClass(byte b[]){
		int index=2;
		int len=0;
		this.version=Utility.byteToshort(b, index);//version
		index+=2;
		this.dwID=Utility.byteToint(b, index);//dwID
		index+=4;
		this.RiseTime=Utility.byteToint(b, index);//
		index+=4;
		this.nACK=Utility.byteToint(b, index);//
		index+=4;
		this.Repair=Utility.byteToint(b, index);//
		index+=4;
		this.conf_time=Utility.byteToint(b, index);//
		index+=4;
		this.dwUserID=Utility.byteToint(b, index);//
		index+=4;
		this.dwMessageStatus=Utility.byteToint(b, index);//
		index+=4;
		len=Utility.byteToint(b, index);
		index+=4;
		this.strUsername =Utility.byteToStrPA(b, len, index);
	}
	public int getClassLen(){
		int len=0;
		len+=2;//type
		len+=2;//version
		len+=4;//id
		len+=4;//RiseTime
		len+=4;//nACK
		len+=4;//Repair
		len+=4;//conf_time
		len+=4;//dwUserID
		len+=4;//dwMessageStatus
		len+=4;//strLen
		int l=Utility.getStringGBKLen(this.strUsername);//strUsername
		if(l>0){
			len+=l+1;
		}
		return len;
	}
	public byte [] classToByte(){
		byte b[]=new byte[getClassLen()];
		int index=0;
		index+=Utility.shortTobyte(b, index, this.type);
		index+=Utility.shortTobyte(b, index, this.version);
		index+=Utility.intTobyte(b, index, this.dwID);
		index+=Utility.longTobyte(b, index, this.RiseTime);
		index+=Utility.intTobyte(b, index, this.nACK);
		index+=Utility.intTobyte(b, index, this.Repair);
		index+=Utility.longTobyte(b, index, this.conf_time);
		index+=Utility.intTobyte(b, index,this.dwUserID);
		index+=Utility.intTobyte(b, index,this.dwMessageStatus);
		index+=Utility.intTobyte(b, index, Utility.getStringGBKLen(this.strUsername));
		index+=Utility.strTobytePA(b, index, this.strUsername);
		return b;
	}
	public int getDwID(){
		return this.dwID;
	}
	public long getRiseTime(){
		return this.RiseTime;
	}
	public int getACK(){
		return this.nACK;
	}
	public int getRepair(){
		return this.Repair;
	}
	public long getConf_time(){
		return this.conf_time;
	}
	public int getUserID(){
		return this.dwUserID;
	}
	public String getUsername(){
		return  this.strUsername;
	}
	public int getMessageStatus(){
		return this.dwMessageStatus;
	}

	public short getType() {
		return this.type;
	}
}
