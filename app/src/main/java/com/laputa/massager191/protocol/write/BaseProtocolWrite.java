package com.laputa.massager191.protocol.write;


import com.laputa.massager191.protocol.bean.Result;
import com.laputa.massager191.protocol.util.DataUtil;
import com.laputa.massager191.protocol.util.LogUtil;

/**
 *  公共部分Write实现类
 * @author zeej
 *
 */
public class BaseProtocolWrite extends AbstractBaseProtocolWrite{
	protected final static String ZERO = "00";
	@Override
	public byte[] writeForOpenOrClose(int open) {
		StringBuffer sb = new StringBuffer();
		String protocl = DataUtil.toHexString(Result.START.getProtocl());
		String openStr = toHexStringBySize(open,2);
		sb.append(protocl)
			.append(openStr)
			.append(ZERO)
			.append(ZERO)
			;
		String result = sb.toString();
		LogUtil.log( result);
		return DataUtil.hexStringToByte(result);
	}

	@Override
	public byte[] writeForElectricity() {
		StringBuffer sb = new StringBuffer();
		String protocl = DataUtil.toHexString(Result.Electricity.getProtocl());
		sb.append(protocl)
			.append(ZERO)
			.append(ZERO)
			.append(ZERO)
			;
		String result = sb.toString();
		LogUtil.log(result);
		return DataUtil.hexStringToByte(result);
	}

	@Override
	public byte[] writeForFactoryResetStatus() {
		StringBuffer sb = new StringBuffer();
		String protocl = DataUtil.toHexString(Result.FactoryResetStatus.getProtocl());
		sb.append(protocl)
			.append(ZERO)
			.append(ZERO)
			.append(ZERO)
			;
		String result = sb.toString();
		LogUtil.log(result);
		return DataUtil.hexStringToByte(result);
	}

	@Override
	public byte[] writeForTest() {
		StringBuffer sb = new StringBuffer();
		String protocl = DataUtil.toHexString(Result.Test.getProtocl());
		sb.append(protocl)
			.append(ZERO)
			.append(ZERO)
			.append(ZERO)
			;
		String result = sb.toString();
		LogUtil.log(result);
		return DataUtil.hexStringToByte(result);
	}
	
	@Override
	public byte[] writeForFirmwareVersion() {
		StringBuffer sb = new StringBuffer();
		String protocl = DataUtil.toHexString(Result.FirmwareVersion.getProtocl());
		sb.append(protocl)
			.append(ZERO)
			.append(ZERO)
			.append(ZERO)
			;
		String result = sb.toString();
		LogUtil.log(result);
		return DataUtil.hexStringToByte(result);
	}
	
	protected String toHexStringBySize(long value , int size){
		String result = "";
		String nowStr = String.valueOf(Long.toHexString(value));
		String zero = "";
		int num = size-nowStr.length();
		for (int i = 0; i < num; i++) {
			zero+="0";
		}
		result = zero + nowStr;
		return result;
	}

}
