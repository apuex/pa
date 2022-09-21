package com.github.apuex.pa.mhs.impl;

import com.github.apuex.pa.mhs.PAMessage;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PAUserSQLTest {
  @Test
  public void testEncodePAUserSQL() {
    byte[] expected = new byte[] {
        0x10, 0x00, 0x11, 0x00, 0x65, 0x01, 0x00, 0x00,
        0x69, 0x6E, 0x73, 0x65, 0x72, 0x74, 0x20, 0x69,
        0x6E, 0x74, 0x6F, 0x20, 0x42, 0x41, 0x49, 0x48,
        0x69, 0x73, 0x74, 0x6F, 0x72, 0x79, 0x51, 0x75,
        0x61, 0x72, 0x74, 0x65, 0x72, 0x28, 0x44, 0x61,
        0x74, 0x61, 0x4F, 0x62, 0x6A, 0x49, 0x44, 0x2C,
        0x52, 0x65, 0x63, 0x6F, 0x72, 0x64, 0x54, 0x69,
        0x6D, 0x65, 0x2C, 0x43, 0x75, 0x72, 0x72, 0x65,
        0x6E, 0x74, 0x56, 0x61, 0x6C, 0x75, 0x65, 0x2C,
        0x4D, 0x61, 0x78, 0x56, 0x61, 0x6C, 0x75, 0x65,
        0x2C, 0x4D, 0x61, 0x78, 0x4F, 0x63, 0x63, 0x75,
        0x72, 0x65, 0x64, 0x54, 0x69, 0x6D, 0x65, 0x2C,
        0x4D, 0x69, 0x6E, 0x56, 0x61, 0x6C, 0x75, 0x65,
        0x2C, 0x4D, 0x69, 0x6E, 0x4F, 0x63, 0x63, 0x75,
        0x72, 0x65, 0x64, 0x54, 0x69, 0x6D, 0x65, 0x2C,
        0x41, 0x76, 0x65, 0x72, 0x61, 0x67, 0x65, 0x56,
        0x61, 0x6C, 0x75, 0x65, 0x2C, 0x55, 0x6E, 0x61,
        0x76, 0x61, 0x69, 0x6C, 0x61, 0x62, 0x6C, 0x65,
        0x54, 0x69, 0x6D, 0x65, 0x2C, 0x45, 0x76, 0x65,
        0x6E, 0x74, 0x43, 0x6F, 0x75, 0x6E, 0x74, 0x2C,
        0x59, 0x65, 0x61, 0x72, 0x4E, 0x6F, 0x2C, 0x4D,
        0x6F, 0x6E, 0x74, 0x68, 0x4E, 0x6F, 0x2C, 0x44,
        0x61, 0x79, 0x4E, 0x6F, 0x2C, 0x48, 0x6F, 0x75,
        0x72, 0x4E, 0x6F, 0x2C, 0x51, 0x75, 0x61, 0x72,
        0x74, 0x65, 0x72, 0x4E, 0x6F, 0x2C, 0x48, 0x69,
        0x73, 0x74, 0x6F, 0x72, 0x79, 0x44, 0x61, 0x74,
        0x61, 0x29, 0x20, 0x76, 0x61, 0x6C, 0x75, 0x65,
        0x73, 0x20, 0x28, 0x31, 0x38, 0x32, 0x34, 0x39,
        0x31, 0x2C, 0x27, 0x32, 0x30, 0x32, 0x32, 0x2D,
        0x30, 0x39, 0x2D, 0x32, 0x30, 0x20, 0x31, 0x30,
        0x3A, 0x33, 0x30, 0x3A, 0x30, 0x30, 0x27, 0x2C,
        0x31, 0x31, 0x30, 0x2E, 0x30, 0x32, 0x30, 0x30,
        0x30, 0x2C, 0x31, 0x31, 0x30, 0x2E, 0x30, 0x32,
        0x30, 0x30, 0x30, 0x2C, 0x27, 0x32, 0x30, 0x32,
        0x32, 0x2D, 0x30, 0x39, 0x2D, 0x32, 0x30, 0x20,
        0x31, 0x30, 0x3A, 0x32, 0x35, 0x3A, 0x35, 0x39,
        0x27, 0x2C, 0x31, 0x31, 0x30, 0x2E, 0x30, 0x32,
        0x30, 0x2C, 0x27, 0x32, 0x30, 0x32, 0x32, 0x2D,
        0x30, 0x39, 0x2D, 0x32, 0x30, 0x20, 0x31, 0x30,
        0x3A, 0x32, 0x35, 0x3A, 0x35, 0x39, 0x27, 0x2C,
        0x31, 0x31, 0x30, 0x2E, 0x30, 0x32, 0x30, 0x30,
        0x30, 0x2C, 0x31, 0x32, 0x2E, 0x30, 0x30, 0x30,
        0x30, 0x30, 0x30, 0x2C, 0x30, 0x2C, 0x20, 0x32,
        0x30, 0x32, 0x32, 0x2C, 0x20, 0x39, 0x2C, 0x20,
        0x32, 0x30, 0x2C, 0x20, 0x31, 0x30, 0x2C, 0x20,
        0x32, 0x2C, 0x27, 0x27, 0x29, 0x00
    };
    PAUserSQL input = new PAUserSQL();
    input.setSQL("insert into BAIHistoryQuarter(DataObjID,RecordTime,CurrentValue,MaxValue,MaxOccuredTime,MinValue,MinOccuredTime,AverageValue,UnavailableTime,EventCount,YearNo,MonthNo,DayNo,HourNo,QuarterNo,HistoryData) values (182491,'2022-09-20 10:30:00',110.02000,110.02000,'2022-09-20 10:25:59',110.020,'2022-09-20 10:25:59',110.02000,12.000000,0, 2022, 9, 20, 10, 2,'')");
    byte[] output = input.classToByte();
    Assert.assertArrayEquals(expected, output);
  }

  @Test
  public void testDecodePAUserSQL() {
    byte[] input = new byte[] {
        0x10, 0x00, 0x11, 0x00, 0x65, 0x01, 0x00, 0x00,
        0x69, 0x6E, 0x73, 0x65, 0x72, 0x74, 0x20, 0x69,
        0x6E, 0x74, 0x6F, 0x20, 0x42, 0x41, 0x49, 0x48,
        0x69, 0x73, 0x74, 0x6F, 0x72, 0x79, 0x51, 0x75,
        0x61, 0x72, 0x74, 0x65, 0x72, 0x28, 0x44, 0x61,
        0x74, 0x61, 0x4F, 0x62, 0x6A, 0x49, 0x44, 0x2C,
        0x52, 0x65, 0x63, 0x6F, 0x72, 0x64, 0x54, 0x69,
        0x6D, 0x65, 0x2C, 0x43, 0x75, 0x72, 0x72, 0x65,
        0x6E, 0x74, 0x56, 0x61, 0x6C, 0x75, 0x65, 0x2C,
        0x4D, 0x61, 0x78, 0x56, 0x61, 0x6C, 0x75, 0x65,
        0x2C, 0x4D, 0x61, 0x78, 0x4F, 0x63, 0x63, 0x75,
        0x72, 0x65, 0x64, 0x54, 0x69, 0x6D, 0x65, 0x2C,
        0x4D, 0x69, 0x6E, 0x56, 0x61, 0x6C, 0x75, 0x65,
        0x2C, 0x4D, 0x69, 0x6E, 0x4F, 0x63, 0x63, 0x75,
        0x72, 0x65, 0x64, 0x54, 0x69, 0x6D, 0x65, 0x2C,
        0x41, 0x76, 0x65, 0x72, 0x61, 0x67, 0x65, 0x56,
        0x61, 0x6C, 0x75, 0x65, 0x2C, 0x55, 0x6E, 0x61,
        0x76, 0x61, 0x69, 0x6C, 0x61, 0x62, 0x6C, 0x65,
        0x54, 0x69, 0x6D, 0x65, 0x2C, 0x45, 0x76, 0x65,
        0x6E, 0x74, 0x43, 0x6F, 0x75, 0x6E, 0x74, 0x2C,
        0x59, 0x65, 0x61, 0x72, 0x4E, 0x6F, 0x2C, 0x4D,
        0x6F, 0x6E, 0x74, 0x68, 0x4E, 0x6F, 0x2C, 0x44,
        0x61, 0x79, 0x4E, 0x6F, 0x2C, 0x48, 0x6F, 0x75,
        0x72, 0x4E, 0x6F, 0x2C, 0x51, 0x75, 0x61, 0x72,
        0x74, 0x65, 0x72, 0x4E, 0x6F, 0x2C, 0x48, 0x69,
        0x73, 0x74, 0x6F, 0x72, 0x79, 0x44, 0x61, 0x74,
        0x61, 0x29, 0x20, 0x76, 0x61, 0x6C, 0x75, 0x65,
        0x73, 0x20, 0x28, 0x31, 0x38, 0x32, 0x34, 0x39,
        0x31, 0x2C, 0x27, 0x32, 0x30, 0x32, 0x32, 0x2D,
        0x30, 0x39, 0x2D, 0x32, 0x30, 0x20, 0x31, 0x30,
        0x3A, 0x33, 0x30, 0x3A, 0x30, 0x30, 0x27, 0x2C,
        0x31, 0x31, 0x30, 0x2E, 0x30, 0x32, 0x30, 0x30,
        0x30, 0x2C, 0x31, 0x31, 0x30, 0x2E, 0x30, 0x32,
        0x30, 0x30, 0x30, 0x2C, 0x27, 0x32, 0x30, 0x32,
        0x32, 0x2D, 0x30, 0x39, 0x2D, 0x32, 0x30, 0x20,
        0x31, 0x30, 0x3A, 0x32, 0x35, 0x3A, 0x35, 0x39,
        0x27, 0x2C, 0x31, 0x31, 0x30, 0x2E, 0x30, 0x32,
        0x30, 0x2C, 0x27, 0x32, 0x30, 0x32, 0x32, 0x2D,
        0x30, 0x39, 0x2D, 0x32, 0x30, 0x20, 0x31, 0x30,
        0x3A, 0x32, 0x35, 0x3A, 0x35, 0x39, 0x27, 0x2C,
        0x31, 0x31, 0x30, 0x2E, 0x30, 0x32, 0x30, 0x30,
        0x30, 0x2C, 0x31, 0x32, 0x2E, 0x30, 0x30, 0x30,
        0x30, 0x30, 0x30, 0x2C, 0x30, 0x2C, 0x20, 0x32,
        0x30, 0x32, 0x32, 0x2C, 0x20, 0x39, 0x2C, 0x20,
        0x32, 0x30, 0x2C, 0x20, 0x31, 0x30, 0x2C, 0x20,
        0x32, 0x2C, 0x27, 0x27, 0x29, 0x00
    };
    PAUserSQL output = new PAUserSQL();
    output.byteToClass(input);

    Assert.assertEquals(PAMessage.USER_SQL_TEXT, output.getType());
    Assert.assertEquals("insert into BAIHistoryQuarter(DataObjID,RecordTime,CurrentValue,MaxValue,MaxOccuredTime,MinValue,MinOccuredTime,AverageValue,UnavailableTime,EventCount,YearNo,MonthNo,DayNo,HourNo,QuarterNo,HistoryData) values (182491,'2022-09-20 10:30:00',110.02000,110.02000,'2022-09-20 10:25:59',110.020,'2022-09-20 10:25:59',110.02000,12.000000,0, 2022, 9, 20, 10, 2,'')", output.getSQL());
  }
}