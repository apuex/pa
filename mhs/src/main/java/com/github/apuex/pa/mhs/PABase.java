package com.github.apuex.pa.mhs;

public interface PABase {
	short getType();
	short getVersion();
	int getPriority();
	void byteToClass(byte b[]);
	byte [] classToByte();
}
