package com.github.apuex.pa.mhs.impl;
import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PAOperationRequest implements PABase {
	private int priority;//
	private short type;//2
	private short version;//2
	private int dwOpId;//执行的ID
	private String szUserName;//执行的用户
	private int opType;//0表示执行，1表示取消执行
	public PAOperationPara opPara1=new PAOperationPara();
	public PAOperationPara opPara2=new PAOperationPara();
	public PAOperationRequest(){
		this.priority=PAMessage.Priority[PAMessage.OPERATION_REQUEST];
		this.type=PAMessage.OPERATION_REQUEST;
		this.version=PAMessage.VERSION;
	}
	public void setDwOpId(int dwOpId){
		this.dwOpId=dwOpId;
	}
	public void setSZUserName(String szUserName){
		this.szUserName=szUserName;
	}
	public void setOpType(int opType){
		this.opType=opType;
	}
	public int getDwOpId(){
		return this.dwOpId;
	}
	public String getSZUserName(){
		return this.szUserName;
	}
	public int getOpType(){
		return this.opType;
	}
	public PAOperationPara getPara1(){
		return this.opPara1;
	}
	public PAOperationPara getPara2(){
		return this.opPara2;
	}
	public int getClassLen(){
		int len=0;
		len+=2;//type
		len+=2;//version
		len+=4;//id
		len+=4;//用户字符长度
		len+=Utility.getStringGB18030Len(this.szUserName);
		if(Utility.getStringGB18030Len(this.szUserName)>0){
			len+=1;
		}
		len+=4;//opType
		
		len+=opPara1.getClassLen();
		len+=opPara2.getClassLen();
		return len;
	}
	public byte [] classToByte(){
		byte b[]=new byte[getClassLen()];
		int index=0;
		index+=Utility.shortTobyte(b, index, this.type);
		index+=Utility.shortTobyte(b, index, this.version);
		index+=Utility.intTobyte(b, index, this.dwOpId);
		index+=Utility.intTobyte(b, index,Utility.getStringGB18030Len(this.szUserName));
		index+=Utility.strTobytePA(b, index, this.szUserName);
		/*if(Utility.getStringGB18030Len(this.szUserName)>0){
			index+=1;
			b[index]=0x00;
		}*/
		
		index+=Utility.intTobyte(b, index, this.opType);
		index+=Utility.shortTobyte(b, index, this.opPara1.getType());
		index+=Utility.shortTobyte(b, index, this.opPara1.getVersion());
		index+=Utility.intTobyte(b, index,this.opPara1.getNType());
		index+=Utility.intTobyte(b, index,this.opPara1.getNDelay());
		index+=Utility.doubleTobyte(b, index, this.opPara1.getFValue());
		index+=Utility.intTobyte(b, index,Utility.getStringGB18030Len(this.opPara1.getSZValue()));
		index+=Utility.strTobytePA(b, index, this.opPara1.getSZValue());
		/*if(Utility.getStringGB18030Len(this.opPara1.getSZValue())>0){
			index+=1;
			b[index]=0x00;
		}*/
		index+=Utility.shortTobyte(b, index, this.opPara2.getType());
		index+=Utility.shortTobyte(b, index, this.opPara2.getVersion());
		index+=Utility.intTobyte(b, index,this.opPara2.getNType());
		index+=Utility.intTobyte(b, index,this.opPara2.getNDelay());
		index+=Utility.doubleTobyte(b, index, this.opPara2.getFValue());
		index+=Utility.intTobyte(b, index,Utility.getStringGB18030Len(this.opPara2.getSZValue()));
		index+=Utility.strTobytePA(b, index, this.opPara2.getSZValue());
		/*if(Utility.getStringGB18030Len(this.opPara2.getSZValue())>0){
			index+=1;
			b[index]=0x00;
		}*/
		return b;
	}
	public void byteToClass(byte b[]){
		int index=2;
		int len=0;
		this.version=Utility.byteToshort(b, index);
		index+=2;
		this.dwOpId=Utility.byteToint(b, index);
		index+=4;
		len=Utility.byteToint(b, index);
		index+=4;
		this.szUserName=Utility.byteToStrPA(b, len, index);
		if(len>0){
			index+=(len+1);
		}
		this.opType=Utility.byteToint(b, index);
		index+=4;
		index+=2;
		index+=2;
		this.opPara1.setNType(Utility.byteToint(b, index));
		index+=4;
		this.opPara1.setNDelay(Utility.byteToint(b, index));
		index+=4;
		this.opPara1.setFValue(Utility.byteTodouble(b, index));
		index+=8;
		len=Utility.byteToint(b, index);
		index+=4;
		this.opPara1.setSZValue(Utility.byteToStrPA(b, len, index));
		if(len>0){
			index+=(len+1);
		}
		index+=2;
		index+=2;
		this.opPara2.setNType(Utility.byteToint(b, index));
		index+=4;
		this.opPara2.setNDelay(Utility.byteToint(b, index));
		index+=4;
		this.opPara2.setFValue(Utility.byteTodouble(b, index));
		index+=8;
		len=Utility.byteToint(b, index);
		index+=4;
		this.opPara2.setSZValue(Utility.byteToStrPA(b, len, index));
		if(len>0){
			index+=(len+1);
		}
	}
	public short getType(){
		return this.type;
	}
	public short getVersion(){
		return this.version;
	}
	public int getPriority(){
		return this.priority;
	}
}
