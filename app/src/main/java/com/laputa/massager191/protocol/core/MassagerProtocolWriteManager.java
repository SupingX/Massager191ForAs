package com.laputa.massager191.protocol.core;


import com.laputa.massager191.protocol.bean.MycjMassagerInfo;
import com.laputa.massager191.protocol.bean.Result;
import com.laputa.massager191.protocol.util.DataUtil;
import com.laputa.massager191.protocol.util.LogUtil;
import com.laputa.massager191.protocol.write.AbstractMassagerProtocolWrite;

/**
 * 按摩器Write类
 *
 * @author zeej
 */
public class MassagerProtocolWriteManager extends AbstractMassagerProtocolWrite {

    private static MassagerProtocolWriteManager mMassagerProtoclWrite;

    private MassagerProtocolWriteManager() {
    }

    public static MassagerProtocolWriteManager newInstance() {
        if (null == mMassagerProtoclWrite) {
            mMassagerProtoclWrite = new MassagerProtocolWriteManager();
        }

        return mMassagerProtoclWrite;
    }

    private String toHexString(long value, int size) {
        String result;
        String nowStr = String.valueOf(Long.toHexString(value));
        String zero = "";
        int num = size - nowStr.length();
        for (int i = 0; i < num; i++) {
            zero += "0";
        }
        result = zero + nowStr;
        return result;
    }

    @Override
    public byte[] writeForCurrentMassager(int which) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.MassagerInfo.getProtocl());
        sb.append(protocl)
                .append(toHexString(which, 2))
                .append(ZERO)
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForLoader(int which) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.Loader.getProtocl());
        sb.append(protocl)
                .append(toHexString(which, 2))
                .append(ZERO)
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForPattern(int which) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.Pattern.getProtocl());
        sb.append(protocl)
                .append(toHexString(which, 2))
                .append(ZERO)
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForPower(int which) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.Power.getProtocl());
        sb.append(protocl)
                .append(toHexString(which, 2))
                .append(ZERO)
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForTime() {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.Time.getProtocl());
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
    public byte[] writeForTemperature() {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.Temperature.getProtocl());
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
    public byte[] writeForHeartRate() {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.HeartRate.getProtocl());
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
    public byte[] writeForStartMassager(MycjMassagerInfo info, int witch) {
        StringBuilder sb = new StringBuilder();
        String protocol = DataUtil.toHexString(Result.START.getProtocl());
        int pattern = info.getPattern();
        int power = info.getPower();
        int settingTime = info.getSettingTime();
        int temperature = info.getTemperature();
        int tempUnit = info.getTempUnit();

        sb.append(protocol)
                .append(DataUtil.toHexString(pattern))
                .append(DataUtil.toHexString(power))
                .append(toHexStringBySize(settingTime, 4))
                .append(DataUtil.toHexString(temperature))
                .append(DataUtil.toHexString(tempUnit))
                .append(toHexString(witch, 2))
                .append(ZERO)
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForStopMassager(int witch) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.STOP.getProtocl());
        sb.append(protocl)
                .append(toHexString(witch, 2))
                .append(ZERO)
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForChangePattern(int pattern, int witch) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.ChangePatternCallBack.getProtocl());
        String patternStr = DataUtil.toHexString(pattern);
        sb.append(protocl)
                .append(patternStr)
                .append(toHexString(witch, 2))
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForChangePower(int power, int witch) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.ChangePowerCallBack.getProtocl());
        String powerStr = DataUtil.toHexString(power);
        sb.append(protocl)
                .append(powerStr)
                .append(toHexString(witch, 2))
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForChangeTime(int time, int witch) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.ChangeTimeCallBack.getProtocl());
        String timeStr = toHexStringBySize(time, 4);
        sb.append(protocl)
                .append(timeStr)
                .append(toHexString(witch, 2))
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }

    @Override
    public byte[] writeForChangeTemperature(int temp, int unit) {
        StringBuilder sb = new StringBuilder();
        String protocl = DataUtil.toHexString(Result.ChangeTemperatureCallBack.getProtocl());
        String tempStr = toHexStringBySize(temp, 2);
        String tempUnitStr = toHexStringBySize(unit, 2);
        sb.append(protocl)
                .append(tempStr)
                .append(tempUnitStr)
                .append(ZERO)
                .append(ZERO)
        ;
        String result = sb.toString();
        LogUtil.log(result);
        return DataUtil.hexStringToByte(result);
    }


}
