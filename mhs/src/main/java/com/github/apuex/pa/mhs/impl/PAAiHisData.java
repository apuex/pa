package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PAAiHisData implements PABase {
	private int priority;//
	private short type;//2
	private short version;//2
	private short periodType;//0表示天，1表示小时，2表示15分钟
	private int   dwID;//4
	private long RecordTime;
	private double RecordValue;
	private long MaxRecordTime;
	private double MaxRecordValue;
	private long MinRecordTime;
	private double MinRecordValue;
	private double AvgRecordValue;
	public PAAiHisData(){
		priority=PAMessage.Priority[PAMessage.AI_HISDATA];
		type=PAMessage.AI_HISDATA;
		version=PAMessage.VERSION;
		periodType=0;
		dwID=0;
		RecordTime=Utility.getLocalTime();
		RecordValue=0;
		MaxRecordTime=Utility.getLocalTime();
		MaxRecordValue=0;
		MinRecordTime=Utility.getLocalTime();
		MinRecordValue=0;
		AvgRecordValue=0;
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
	public double getRecordValue() {
		return this.RecordValue;
	}
	public void setRecordValue(double RecordValue) {
		this.RecordValue=RecordValue;
	}
	public long getMaxRecordTime() {
		return this.MaxRecordTime;
	}
	public void setMaxRecordTime(long MaxRecordTime) {
		this.MaxRecordTime=MaxRecordTime;
	}
	public double getMaxRecordValue() {
		return this.MaxRecordValue;
	}
	public void setMaxRecordValue(double MaxRecordValue) {
		this.MaxRecordValue=MaxRecordValue;
	}
	
	public long getMinRecordTime() {
		return this.MinRecordTime;
	}
	public void setMinRecordTime(long MinRecordTime) {
		this.MinRecordTime=MinRecordTime;
	}
	public double getMinRecordValue() {
		return this.MinRecordValue;
	}
	public void setMinRecordValue(double MinRecordValue) {
		this.MinRecordValue=MinRecordValue;
	}
	public double getAvgRecordValue() {
		return this.AvgRecordValue;
	}
	public void setAvgRecordValue(double AvgRecordValue) {
		this.AvgRecordValue=AvgRecordValue;
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
		this.RecordTime=Utility.byteToTime(b, index);
		index+=8;
		this.RecordValue=Utility.byteTodouble(b, index);
		index+=8;
		this.MaxRecordTime=Utility.byteToTime(b, index);
		index+=8;
		this.MaxRecordValue=Utility.byteTodouble(b, index);
		index+=8;
		this.MinRecordTime=Utility.byteToTime(b, index);
		index+=8;
		this.MinRecordValue=Utility.byteTodouble(b, index);
		index+=8;
		this.AvgRecordValue=Utility.byteTodouble(b, index);
		index+=8;
	}
	public int getClassLen(){
		int len=0;
		len+=2;//type
		len+=2;//version
		len+=2;//periodType
		len+=4;//dwID
		len+=8;//RecordTime
		len+=8;//RecordValue
		len+=8;//MaxRecordTime
		len+=8;//MaxRecordValue
		len+=8;//MinRecordTime
		len+=8;//MinRecordValue
		len+=8;//AvgRecordValue
		return len;
	}
	public byte [] classToByte(){
		byte b[]=new byte[getClassLen()];
		int index=0;
		index+=Utility.shortTobyte(b, index, this.type);
		index+=Utility.shortTobyte(b, index, this.version);
		index+=Utility.shortTobyte(b, index, this.periodType);
		index+=Utility.intTobyte(b, index, this.dwID);
		index+=Utility.timeToByte(b, index, this.RecordTime);
		index+=Utility.doubleTobyte(b, index, this.RecordValue);
		index+=Utility.timeToByte(b, index, this.MaxRecordTime);
		index+=Utility.doubleTobyte(b, index, this.MaxRecordValue);
		index+=Utility.timeToByte(b, index, this.MinRecordTime);
		index+=Utility.doubleTobyte(b, index, this.MinRecordValue);
		index+=Utility.doubleTobyte(b, index, this.AvgRecordValue);
		return b;
	}
}
