package com.github.apuex.pa.mhs.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.apuex.pa.mhs.PAMessage;


public class PAHistoryDataObject {
	private int type;
	private int version;
	private int id;
	private List<PAAIDataValue> lst=null;
	public PAHistoryDataObject(){
		type=PAMessage.HISTORY_DATA_OBJECT;
		version=PAMessage.VERSION;
		lst=new ArrayList<PAAIDataValue>();
		id=-1;
	}
	public void add(PAAIDataValue dat){
		lst.add(dat);
	}
	public PAAIDataValue get(int index){
		return lst.get(index);
	}
	public int getAIDataValueSize(){
		return lst.size();
	}
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	public int getType(){
		return this.type;
	}
	public int getVersion(){
		return this.version;
	}
}
