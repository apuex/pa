package com.github.apuex.pa.mhs.impl;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferTest {
  @Test
  public void testReadOffset() {
    byte[] bytes = new byte[8];
    ByteBuffer bf = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
    bf.putDouble(123.456);
    Assert.assertEquals(8, bf.position());
  }

  @Test
  public void testSkipPositionRead() {
    byte[] input = new byte[]{
        0x00, 0x00, 0x00, 0x00,
        (byte)0xEF, (byte)0xBE, (byte)0xAD, (byte)0xDE
    };
    ByteBuffer bf = ByteBuffer
        .wrap(input)
        .order(ByteOrder.LITTLE_ENDIAN);
    bf.position(4);
    final int output = bf.getInt();
    Assert.assertEquals(0xDEADBEEF, output);
  }

  @Test
  public void testSkipPositionWrite() {
    byte[] expected = new byte[]{
        0x00, 0x00, 0x00, 0x00,
        (byte)0xEF, (byte)0xBE, (byte)0xAD, (byte)0xDE
    };
    byte[] output = new byte[8];
    for(int i = 0; i < output.length; ++i) {
      output[i] = 0;
    }
    ByteBuffer bf = ByteBuffer
        .wrap(output)
        .order(ByteOrder.LITTLE_ENDIAN);
    bf.position(4);
    bf.putInt(0xDEADBEEF);
    Assert.assertArrayEquals(expected, output);
  }
}
