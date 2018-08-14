package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PAMessage;

public class PADataOid {
	private short type;
	private short version;
	private int id;
	public PADataOid(){
		type=PAMessage.DATA_OID;
		version=PAMessage.VERSION;
	}
	public void setType(short type){
		this.type=type;
	}
	public short getType(){
		return this.type;
	}
	public void setVersion(short version){
		this.version=version;
	}
	public short getVersion(){
		return this.version;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
}
