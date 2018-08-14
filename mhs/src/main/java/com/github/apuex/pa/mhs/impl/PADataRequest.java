package com.github.apuex.pa.mhs.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PADataRequest extends PABase {
	private int priority;
	private short type;
	private short version;
	private int requestType;
	private int oidsType;
	private int sizeLen=16;
	private Map<Long,PADataOid> map =null;
	private List<PADataOid> lst=null;
	public PADataRequest(){
		priority=PAMessage.Priority[PAMessage.DATA_REQUEST];
		type=(short)PAMessage.DATA_REQUEST;
		version=(short)PAMessage.VERSION;
		map=new HashMap<Long,PADataOid>();
		lst=new ArrayList<PADataOid>();
		requestType=0;
		oidsType=0;
	}
	public void add(PADataOid obj){//向map和lst中添加对象
		Long id=new Long(obj.getId());
		map.put(id, obj);
		lst.add(obj);
	}
	public int getDataOidSize(){//从lst中获得个数
		return lst.size();
	}
	public PADataOid get(int index){//通过下表从lst中获得对象
		return lst.get(index);
	}
	public PADataOid get(Long id){//通过对象ID从map中获得对象
		return map.get(id);
	}
	public void clear(){
		lst.clear();
		map.clear();
	}
	public void byteToClass(byte b[]){
		int index=2;
		this.version=Utility.byteToshort(b, index);
		index+=2;
		this.requestType=Utility.byteToint(b, index);
		index+=4;
		this.oidsType=Utility.byteToint(b, index);
		index+=4;
		
		int count=Utility.byteToint(b, index);
		index+=4;
		for(int i=0;i<count;i++){
			PADataOid oid=new PADataOid();
			oid.setType(Utility.byteToshort(b, index));
			index+=2;
			oid.setVersion(Utility.byteToshort(b, index));
			index+=2;
			oid.setId(Utility.byteToint(b, index));
			index+=4;
			add(oid);
		}
	}
	public byte [] classToByte(){
		int len=sizeLen+(map.size()*8);
		byte b[]=new byte[len];
		int index=0;
		index+=Utility.shortTobyte(b, index, type);
		index+=Utility.shortTobyte(b, index, version);
		index+=Utility.intTobyte(b, index, requestType);
		index+=Utility.intTobyte(b, index, oidsType);
		index+=Utility.intTobyte(b, index, map.size());
		for(Long key:map.keySet()){
			PADataOid oid=map.get(key);
			index+=Utility.shortTobyte(b, index,oid.getType());
			index+=Utility.shortTobyte(b, index,oid.getVersion());
			index+=Utility.intTobyte(b, index, oid.getId());
		}
		return b;
	}
	public short getType() {
		return this.type;
	}
	public int getPriority() {
		return this.priority;
	}
	public short getVersion(){
		return this.version;
	}
}
