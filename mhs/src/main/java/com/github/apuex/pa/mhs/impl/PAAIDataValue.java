package com.github.apuex.pa.mhs.impl;

public class PAAIDataValue {
	private long tTime;
	private double fValue;
	public PAAIDataValue(){
		tTime=0;
		fValue=0;
	}
	public void setTime(long time){
		this.tTime=time;
	}
	public long getTime(){
		return this.tTime;
	}
	public void setFValue(double value){
		this.fValue=value;
	}
	public double getFValue(){
		return this.fValue;
	}
}
