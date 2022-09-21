package com.github.apuex.pa.mhs;

public interface PABase {
	short getType();
	short getVersion();
	int getPriority();
	void byteToClass(byte b[]);
	byte [] classToByte();

	public class Default implements PABase {

		@Override
		public short getType() {
			return 0;
		}

		@Override
		public short getVersion() {
			return 0;
		}

		@Override
		public int getPriority() {
			return 0;
		}

		@Override
		public void byteToClass(byte[] b) {

		}

		@Override
		public byte[] classToByte() {
			return new byte[0];
		}
	}
}
