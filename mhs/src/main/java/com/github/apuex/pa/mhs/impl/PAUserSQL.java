package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessage;
import com.github.apuex.pa.utility.Utility;

public class PAUserSQL extends PABase {
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
        int index=2;
        this.version=Utility.byteToshort(b, index);
        index+=2;
        int sqlLen=Utility.byteToint(b, index);
        index+=4;
        this.sql=Utility.byteToStrPA(b, sqlLen, index);
        if(sqlLen>0){
            index+=(sqlLen+1);
        }
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
        len+=Utility.getStringGBKLen(this.sql);
        if(Utility.getStringGBKLen(this.sql)>0){
            len+=1;
        }
        return len;
    }
    public byte [] classToByte(){
        int len=0;
        byte b[]=new byte[getClassLen()];
        int index=0;
        index+=Utility.shortTobyte(b, index, this.type);
        index+=Utility.shortTobyte(b, index, this.version);
        index+=Utility.intTobyte(b, index,Utility.getStringGBKLen(this.sql));
        index+=Utility.strTobytePA(b, index, this.sql);
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