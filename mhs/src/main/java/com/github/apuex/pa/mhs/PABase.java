package com.github.apuex.pa.mhs;

public class PABase {
	public short getType(){
		return 0;
	}
	public short getVersion(){
		return 0;
	}
	public int getPriority(){
		return 0;
	}
	public void byteToClass(byte b[]){
	}
	public byte [] classToByte(){
		byte b[]=new byte[1024];
		return b;
	}
}
