package com.github.apuex.pa.mhs.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PADataRespond extends PABase {
	private int priority;
	private short type;
	private short version;
	private int requestType;
	private int oidsType;
	private Map<Integer,PADataObject> map =null;
	private List<PADataObject> lst=null;
	private List<PAHistoryDataObject> hisLst=null;
	private Map<Integer,PAHistoryDataObject> hisMap =null;
	public PADataRespond(){
		priority=PAMessage.Priority[PAMessage.DATA_RESPOND];
		type=PAMessage.DATA_RESPOND;
		version=PAMessage.VERSION;
		map=new HashMap<Integer,PADataObject>();
		lst=new ArrayList<PADataObject>();
		hisLst=new ArrayList<PAHistoryDataObject>();
		hisMap=new HashMap<Integer,PAHistoryDataObject>();
		requestType=0;
		oidsType=0;
	}
	public void add(PADataObject obj){//向map和lst中添加对象(实时数据)
		Integer id=new Integer(obj.getId());
		map.put(id, obj);
		lst.add(obj);
	}
	public void add(PAHistoryDataObject obj){//向lst中添加对象(历史数据)
		Integer id=new Integer(obj.getId());
		hisLst.add(obj);
		hisMap.put(id, obj);
	}
	public int getDataObjectSize(){//从lst中获得个数(实时数据)
		return lst.size();
	}
	public int getHistoryDataObjectSize(){//从hisLst中获得个数(历史数据)
		return hisLst.size();
	}
	public PADataObject get(int index){//通过下表从lst中获得对象(实时数据)
		return lst.get(index);
	}
	public PADataObject get(Long id){//通过对象ID从map中获得对象(实时数据)
		return map.get(id);
	}
	public PAHistoryDataObject getHis(int index){//通过下表从hisLst中获得对象(历史数据)
		return hisLst.get(index);
	}
	public PAHistoryDataObject getHis(Long id){//通过对象ID从map中获得对象(历史数据)
		return hisMap.get(id);
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
		
		int len=0;
		switch(requestType){
		case 0://实时数据
			{
				for(int i=0;i<count;i++){
					PADataObject dat=new PADataObject();
					dat.setType(Utility.byteToshort(b, index));
					index+=2;
					dat.setVersion(Utility.byteToshort(b, index));
					index+=2;
					dat.setId(Utility.byteToint(b, index));
					index+=4;
					dat.setValueType(Utility.byteToint(b, index));
					index+=4;
					dat.setState(Utility.byteToint(b, index));
					index+=4;
					dat.setFValue(Utility.byteTofloat(b, index));
					index+=4;
					dat.setIValue(Utility.byteToint(b, index));
					index+=4;
					
					len=Utility.byteToint(b, index);
					index+=4;
					dat.setSValue(Utility.byteToStrPA(b, len, index));
					if(len>0){
						index+=len+1;
					}
					dat.setValid(Utility.byteToint(b, index));
					index+=4;
					
					len=Utility.byteToint(b, index);
					index+=4;
					dat.setDesc(Utility.byteToStrPA(b, len, index));
					if(len>0){
						index+=len+1;
					}
					Integer id=new Integer(dat.getId());
					map.put(id, dat);
					lst.add(dat);
				}
			}
			break;
		case 4://历史数据
			{
				
			}
			break;
		default:
			break;
		}
	}
	public int getClassLen(){
		int len=16;
		int temp=0;
		for(Integer key:map.keySet()){
			PADataObject dat=map.get(key);
			len+=(4*9);
			temp=Utility.getStringGBKLen(dat.getSValue());
			if(temp>0){
				len+=temp+1;
			}
			temp=Utility.getStringGBKLen(dat.getDesc());
			if(temp>0){
				len+=temp+1;
			}
		}
		return len;
	}
	public byte [] classToByte(){
		byte b[]=new byte[getClassLen()];
		int index=0;
		index+=Utility.shortTobyte(b, index, type);
		index+=Utility.shortTobyte(b, index, version);
		index+=Utility.intTobyte(b, index, requestType);
		index+=Utility.intTobyte(b, index, oidsType);
		index+=Utility.intTobyte(b, index, map.size());
		for(Integer key:map.keySet()){
			PADataObject dat=map.get(key);
			index+=Utility.shortTobyte(b, index,dat.getType());
			index+=Utility.shortTobyte(b, index,dat.getVersion());
			index+=Utility.intTobyte(b, index, dat.getId());
			index+=Utility.intTobyte(b, index, dat.getValueType());
			index+=Utility.intTobyte(b, index, dat.getState());
			index+=Utility.floatTobyte(b, index, dat.getFValue());
			index+=Utility.intTobyte(b, index, dat.getIValue());
			index+=Utility.intTobyte(b, index, Utility.getStringGBKLen(dat.getSValue()));
			index+=Utility.strTobytePA(b, index, dat.getSValue());
			index+=Utility.intTobyte(b, index, dat.getValid());
			index+=Utility.intTobyte(b, index, Utility.getStringGBKLen(dat.getDesc()));
			index+=Utility.strTobytePA(b, index, dat.getDesc());
		}
		return b;
	}
	public short getType() {
		return this.type;
	}
	public int getPriority() {
		return this.priority;
	}
}
