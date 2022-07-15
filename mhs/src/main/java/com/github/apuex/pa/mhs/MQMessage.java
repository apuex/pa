package com.github.apuex.pa.mhs;

public class MQMessage {
    private String pathName;
    private String destName;
    private PABase paBase;
    public short getType(){
        return paBase.getType();
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public PABase getPaBase() {
        return paBase;
    }

    public void setPaBase(PABase paBase) {
        this.paBase = paBase;
    }
}
