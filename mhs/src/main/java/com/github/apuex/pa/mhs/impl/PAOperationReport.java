package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PAOperationReport extends PABase {
	private int priority;//
	private short type;//2
	private short version;//2
	private long RiseTime;
	private int dwOpId;//执行的ID
	private int SessionState;
	private int SessionResult;
	private String Description;
	private String UserName;//执行的用户
	public PAOperationReport(){
		this.priority=PAMessage.Priority[PAMessage.OPERATION_REPORT];
		this.type=PAMessage.OPERATION_REPORT;
		this.version=PAMessage.VERSION;
	}
	public void setRiseTime(long RiseTime){
		this.RiseTime=RiseTime;
	}
	public void setDwOpId(int dwOpId){
		this.dwOpId=dwOpId;
	}
	public void setSessionState(int SessionState){
		this.SessionState=SessionState;
	}
	public void setSessionResult(int SessionResult){
		this.SessionResult=SessionResult;
	}
	public void setDescription(String Description){
		this.Description=Description;
	}
	public void setUserName(String UserName){
		this.UserName=UserName;
	}
	public int getClassLen(){
		int len=0;
		len+=2;//type
		len+=2;//version
		len+=4;//时间
		len+=4;//id
		len+=4;//SessionState
		len+=4;//SessionResult
		len+=4;//描述字符长度
		len+=Utility.getStringGBKLen(this.Description);
		if(Utility.getStringGBKLen(this.Description)>0){
			len+=1;
		}
		len+=4;//用户名字符长度
		len+=Utility.getStringGBKLen(this.UserName);
		if(Utility.getStringGBKLen(this.UserName)>0){
			len+=1;
		}
		return len;
	}
	public byte [] classToByte(){
		byte b[]=new byte[getClassLen()];
		int index=0;
		index+=Utility.shortTobyte(b, index, this.type);
		index+=Utility.shortTobyte(b, index, this.version);
		index+=Utility.longTobyte(b, index, this.RiseTime);
		index+=Utility.intTobyte(b, index, this.dwOpId);
		index+=Utility.intTobyte(b, index, this.SessionState);
		index+=Utility.intTobyte(b, index, this.SessionResult);
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.Description));
		index+=Utility.strTobytePA(b, index, this.Description);
		index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.UserName));
		index+=Utility.strTobytePA(b, index, this.UserName);
		return b;
	}
	public void byteToClass(byte b[]){
		int index=2;
		int len=0;
		this.version=Utility.byteToshort(b, index);
		index+=2;
		this.RiseTime=Utility.byteToint(b, index);
		index+=4;
		this.dwOpId=Utility.byteToint(b, index);
		index+=4;
		this.SessionState=Utility.byteToint(b, index);
		index+=4;
		this.SessionResult=Utility.byteToint(b, index);
		index+=4;
		len=Utility.byteToint(b, index);
		index+=4;
		this.Description=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		len=Utility.byteToint(b, index);
		index+=4;
		this.UserName=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
	}
	public short getType(){
		return this.type;
	}
	public short getVersion(){
		return this.version;
	}
	public int getPriority(){
		return this.priority;
	}

	public long getRiseTime() {
		return RiseTime;
	}

	public int getDwOpId() {
		return dwOpId;
	}

	public int getSessionState() {
		return SessionState;
	}

	public int getSessionResult() {
		return SessionResult;
	}

	public String getDescription() {
		return Description;
	}

	public String getUserName() {
		return UserName;
	}
}
