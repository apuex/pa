package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PAMessage;

public class PADataObject {
	private short type;
	private short version;
	private int dwOid;//对应系统中的数据ID,由发送原语者从其他地方获取
	private int nValueType;//0表示输出类型,1表示DI类型,2表示浮点类型,3表示SI类型
	private int nState;//对应系统中的数据对象状态
	private double fValue;//浮点数值
	private int iValue;//整数量值
	private String szValue;//字符串值
	private int bValid;//是否有效
	private String strDesc;//
	public PADataObject(){
		type=PAMessage.DATA_OBJECT;
		version=PAMessage.VERSION;
		dwOid=-1;
		nValueType=-1;
		nState=0;
		fValue=0;
		iValue=0;
		szValue="";
		bValid=1;
		strDesc="";
	}
	public void setType(short type){
		this.type=type;
	}
	public void setVersion(short version){
		this.version=version;
	}
	public short getType(){
		return this.type;
	}
	public short getVersion(){
		return this.version;
	}
	public int getId(){
		return this.dwOid;
	}
	public void setId(int dwOid){
		this.dwOid=dwOid;
	}
	public int getValueType(){
		return this.nValueType;
	}
	public void setValueType(int nValueType){
		this.nValueType=nValueType;
	}
	public int getState(){
		return this.nState;
	}
	public void setState(int nState){
		this.nState=nState;
	}
	public double getFValue(){
		return this.fValue;
	}
	public void setFValue(double fValue){
		this.fValue=fValue;
	}
	public int getIValue(){
		return this.iValue;
	}
	public void setIValue(int iValue){
		this.iValue=iValue;
	}
	public String getSValue(){
		return this.szValue;
	}
	public void setSValue(String szValue){
		this.szValue=szValue;
	}
	public int getValid(){
		return this.bValid;
	}
	public void setValid(int Valid){
		this.bValid=Valid;
	}
	public String getDesc(){
		return this.strDesc;
	}
	public void setDesc(String strDesc){
		this.strDesc=strDesc;
	}
}
