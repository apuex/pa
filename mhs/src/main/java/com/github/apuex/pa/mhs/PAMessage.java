package com.github.apuex.pa.mhs;

public class PAMessage {
	public static final int BASE =0;//告警原语
	public static final int MHUINFOREQUEST=2;
	public static final int MHUSTATUS_REPORT=3;
	public static final int ALARM =4;//告警原语
	public static final int ALARM_REQUEST = 5;//请求告警原语
	public static final int CONFIG_CHANGE = 6;//配置变化原语
	public static final int DATA_RESPOND = 7;//实时数据相应原语
	public static final int DATA_OBJECT=8;
	public static final int HISTORY_DATA_OBJECT=9;
	public static final int DATA_OBJECT_CHANGE = 10;//数据对象变化原语
	public static final int DATA_OID=11;
	public static final int DATA_REQUEST = 12;//请求实时数据原语
	public static final int OPERATION_REPORT = 13;//操作事件报告原语
	public static final int OPERATION_PARA=14;
	public static final int OPERATION_REQUEST = 15;//操作原语
	public static final int USER_SQL_TEXT = 16;//写库原语
	public static final int RAWIO=17;
	public static final int RAWIO_RESULT=18;
	public static final int ALARM_ACK_INFO = 19;//告警确认原语
	public static final int DATE_TIME = 20;//校时原语
	public static final int SQL_EXC_STATUS = 21;//校时原语
	public static final int FSU_STATUS = 22;//校时原语
	public static final int BINTERFACE_CONFIG_CHANGE = 26;//B接口配置修改同步
	public static final int AI_HISDATA = 27;//写AI历史数据消息
	public static final int DI_HISDATA = 28;//写DI历史数据消息
	public static final int MAXPAMessage = 29;//最大消息数
	public static final String PathName="_REAL_DATA_SERVER";
	public static final String DestName="MHS_Input";
	public static final int VERSION=0x11;
	public static final int Priority[]={
			5,//0
			7,//1
			5,//2
			5,//3
			2,//4
			2,//5
			5,//6
			2,//7
			5,//8
			5,//9
			5,//10
			5,//11
			2,//12
			5,//13
			5,//14
			2,//15
			5,//16
			5,//17
			5,//18
			2,//19
			5,//20
			5,//21
			5,//22
			5,//23
			5,//24
			5,//25
			5,//26
			5,//27
			5//28
	};
	public static final String PAMHS[]={
			"BASE",//0
			"",//1
			"MHUINFOREQUEST",//2
			"MHUSTATUS_REPORT",//3
			"ALARM",//4
			"ALARM_REQUEST",//5
			"CONFIG_CHANGE",//6
			"DATA_RESPOND",//7
			"DATA_OBJECT",//8
			"HISTORY_DATA_OBJECT",//9
			"DATA_OBJECT_CHANGE",//10
			"DATA_OID",//11
			"DATA_REQUEST",//12
			"OPERATION_REPORT",//13
			"OPERATION_PARA",//14
			"OPERATION_REQUEST",//15
			"USER_SQL_TEXT",//16
			"RAWIO",//17
			"RAWIO_RESULT",//18
			"ALARM_ACK_INFO",//19
			"DATE_TIME",//20
			"SQL_EXC_STATUS",//21
			"FSU_STATUS",//22
			"",//23
			"",//24
			"",//25
			"BINTERFACE_CONFIG_CHANGE",//26
			"AI_HISDATA",//27
			"DI_HISDATA",//28
	};
}
