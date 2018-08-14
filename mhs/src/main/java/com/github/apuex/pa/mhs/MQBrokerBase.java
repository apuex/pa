package com.github.apuex.pa.mhs;
public interface MQBrokerBase {
	public void onMessage(PABase pPA,int type,int nLen);
}
