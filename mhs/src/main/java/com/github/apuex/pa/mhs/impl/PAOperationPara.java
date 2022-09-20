package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PAOperationPara {
	private short type;//2
	private short version;//2
	private int nType;//1: TRUN OFF£¬ 2:  TURN ON   3:  STRIKE  4£º¸¡µãÖµ£¬ 5£º×Ö·û´®
	private int	nDelay;//延时
	private double fValue;//浮点值
	private String szValue;//字符值
	public PAOperationPara(){
		this.type=PAMessage.OPERATION_PARA;
		this.version=PAMessage.VERSION;
		this.nType=0;
		this.fValue=-1;
		this.nDelay=2147483647;
		this.szValue="";
	}
	public void setNType(int nType){
		this.nType=nType;
	}
	public void setNDelay(int nDelay){
		this.nDelay=nDelay;
	}
	public void setFValue(double fValue){
		this.fValue=fValue;
	}
	public void setSZValue(String szValue){
		this.szValue=szValue;
	}
	public short getType(){
		return this.type;
	}
	public short getVersion(){
		return this.version;
	}
	public int getNType(){
		return this.nType;
	}
	public int getNDelay(){
		return this.nDelay;
	}
	public double getFValue(){
		return this.fValue;
	}
	public String getSZValue(){
		return this.szValue;
	}
	public int getClassLen(){
		int len=0;
		len+=2;//type
		len+=2;//version
		len+=4;//nType
		len+=4;//nDelay
		len+=4;//fValue
		len+=4;//字符长度
		len+=Utility.getStringGB18030Len(this.szValue);
		if(Utility.getStringGB18030Len(this.szValue)>0){
			len+=1;
		}
		return len;
	}
}
