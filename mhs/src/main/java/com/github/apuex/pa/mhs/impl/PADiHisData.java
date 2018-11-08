package com.github.apuex.pa.mhs.impl;


import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PADiHisData extends PABase {
	private int priority;//
	private short type;//2
	private short version;//2
	private short periodType;//0表示天，1表示小时，2表示15分钟
	private int   dwID;//4
	private long RecordTime;
	private int RecordValue;
	public PADiHisData(){
		priority=PAMessage.Priority[PAMessage.DI_HISDATA];
		type=PAMessage.DI_HISDATA;
		version=PAMessage.VERSION;
		periodType=0;
		dwID=0;
		RecordTime=Utility.getLocalTime();
		RecordValue=0;
	}
	public short getPeriodType() {
		return this.periodType;
	}
	public void setPeriodType(short periodType) {
		this.periodType=periodType;
	}
	public int getDwID() {
		return this.dwID;
	}
	public void setDwID(int dwID) {
		this.dwID=dwID;
	}
	public long getRecordTime() {
		return this.RecordTime;
	}
	public void setRecordTime(long RecordTime) {
		this.RecordTime=RecordTime;
	}
	public int getRecordValue() {
		return this.RecordValue;
	}
	public void setRecordValue(int RecordValue) {
		this.RecordValue=RecordValue;
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
	public void byteToClass(byte b[]){
		int index=2;
		int len=0;
		this.version=Utility.byteToshort(b, index);
		index+=2;
		this.periodType=Utility.byteToshort(b, index);
		index+=2;
		this.dwID=Utility.byteToint(b, index);
		index+=4;
		this.RecordTime=Utility.byteTolong(b, index);
		index+=4;
		this.RecordValue=Utility.byteToint(b, index);
		index+=4;
	}
	public int getClassLen(){
		int len=0;
		len+=2;//type
		len+=2;//version
		len+=2;//periodType
		len+=4;//dwID
		len+=4;//RecordTime
		len+=4;//RecordValue
		return len;
	}
	public byte [] classToByte(){
		byte b[]=new byte[getClassLen()];
		int index=0;
		index+=Utility.shortTobyte(b, index, this.type);
		index+=Utility.shortTobyte(b, index, this.version);
		index+=Utility.shortTobyte(b, index, this.periodType);
		index+=Utility.intTobyte(b, index, this.dwID);
		index+=Utility.longTobyte(b, index, this.RecordTime);
		index+=Utility.intTobyte(b, index, this.RecordValue);
		return b;
	}
}
