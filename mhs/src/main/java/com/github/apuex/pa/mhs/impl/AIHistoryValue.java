package com.github.apuex.pa.mhs.impl;
public class AIHistoryValue {
	private long time=0;
	private double fValue=0;
	public void setTime(long time){
		this.time=time;
	}
	public void setFValue(double fValue){
		this.fValue=fValue;
	}
	public long getTime(){
		return this.time;
	}
	public double getFValue(){
		return this.fValue;
	}
}
