package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PAUserSQL implements PABase {
    private int priority;
    private short type;
    private short version;
    private String sql;
    public PAUserSQL(){
        priority=PAMessage.Priority[PAMessage.USER_SQL_TEXT];
        type=(short)PAMessage.USER_SQL_TEXT;
        version=(short)PAMessage.VERSION;
        sql="";
    }

    public void byteToClass(byte b[]){
        ByteBuffer bf = ByteBuffer
            .wrap(b)
            .order(ByteOrder.LITTLE_ENDIAN);
        short inputType = bf.getShort(); // skip type
        this.version=bf.getShort();
        final int sqlLen=bf.getInt();
        byte[] sqlBytes = new byte[sqlLen];
        bf.get(sqlBytes);
        this.sql=Utility.gb18030ByteToStr(sqlBytes);
        if(0 < sqlLen) bf.get(); // skip null terminator
    }
    public String getSQL() {
        return this.sql;
    }
    public void setSQL(String sql) {
        this.sql=sql;
    }

    private int getClassLen(){
        int len=0;
        len+=2;//type
        len+=2;//version
        len+=4;//sql len
        final int sqlLen =Utility.getStringGB18030Len(this.sql);
        len+=sqlLen;
        if(sqlLen >0){
            len+=1;
        }
        return len;
    }
    public byte [] classToByte(){
        int len=0;
        byte b[]=new byte[getClassLen()];
        ByteBuffer bf = ByteBuffer
            .wrap(b)
            .order(ByteOrder.LITTLE_ENDIAN);
        bf.putShort(this.type);
        bf.putShort(this.version);
        byte[] sqlBytes = Utility.strToGB18030Byte(this.sql);
        bf.putInt(sqlBytes.length);
        bf.put(sqlBytes);
        if(0 < sqlBytes.length) bf.put((byte)0);
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