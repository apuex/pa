package com.github.apuex.pa.mhs.impl;
import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PAConfigChange implements PABase {
	private int priority;//
	private short type;//2
	private short version;//2
	private short changeType;//修改类型
	private int   agentID;//4
	private int   fsuID;//4
	private int   deviceID;//4
	private int   signalID;//4
	public PAConfigChange(){
		priority=PAMessage.Priority[PAMessage.BINTERFACE_CONFIG_CHANGE];
		type=PAMessage.BINTERFACE_CONFIG_CHANGE;
		version=PAMessage.VERSION;
	}
	public short getType(){
		return this.type;
	}
	public short getVersion(){
		return this.version;
	}
	public void setType(short type){
		this.type=type;
	}
	public void setVersion(short version){
		this.version=version;
	}
	public int getPriority() {
		return this.priority;
	}
	public void setChangeType(short changeType){
		this.changeType=changeType;
	}
	public short getChangeType(){
		return this.changeType;
	}
	public void setAgentID(int agentID){
		this.agentID=agentID;
	}
	public int getAgentID(){
		return this.agentID;
	}
	public void setFsuID(int fsuID){
		this.fsuID=fsuID;
	}
	public int getFsuID(){
		return this.fsuID;
	}
	public void setDeviceID(int deviceID){
		this.deviceID=deviceID;
	}
	public int getDeviceID(){
		return this.deviceID;
	}
	public void setSignalID(int signalID){
		this.signalID=signalID;
	}
	public int getSignalID(){
		return this.signalID;
	}
	public void byteToClass(byte b[]){
		int index=2;
		int len=0;
		this.version=Utility.byteToshort(b, index);
		index+=2;
		this.changeType=Utility.byteToshort(b, index);
		index+=2;
		this.agentID=Utility.byteToint(b, index);
		index+=4;
		this.fsuID=Utility.byteToint(b, index);
		index+=4;
		this.deviceID=Utility.byteToint(b, index);
		index+=4;
		this.signalID=Utility.byteToint(b, index);
		index+=4;
	}
	public int getClassLen(){
		int len=0;
		len+=2;//type
		len+=2;//version
		len+=2;//changeType
		len+=4;//agentID
		len+=4;//fsuID
		len+=4;//deviceID
		len+=4;//signalID
		return len;
	}
	public byte [] classToByte(){
		byte b[]=new byte[getClassLen()];
		int index=0;
		index+=Utility.shortTobyte(b, index, this.type);
		index+=Utility.shortTobyte(b, index, this.version);
		index+=Utility.shortTobyte(b, index, this.changeType);
		index+=Utility.intTobyte(b, index, this.agentID);
		index+=Utility.intTobyte(b, index, this.fsuID);
		index+=Utility.intTobyte(b, index, this.deviceID);
		index+=Utility.intTobyte(b, index, this.signalID);
		return b;
	}


	
}
