package com.laputa.massager191.protocol.bean;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/**
 * 按摩信息类
 * 
 * @author zeej
 *
 */
public class MycjMassagerInfo implements Parcelable{
	/**
	 * 是否打开-0：关闭；1：打开
	 */
	private int open;
	/**
	 * 模式
	 */
	private int pattern;
	/**
	 * 力度
	 */
	private int power;
	/**
	 * 剩余时间-单位:秒
	 */
	private int leftTime;
	/**
	 * 设置时间-单位：秒
	 */
	private int settingTime;
	/**
	 * 温度
	 */
	private int temperature;
	/**
	 * 温度单位
	 */
	private int tempUnit;
	/**
	 * 负载-0：没有负载；1：有负载
	 */
	private int loader;
	
	private int hr;
	
	public MycjMassagerInfo() {
		super();
	}
	public MycjMassagerInfo(int open, int pattern, int power, int leftTime,
			int settingTime, int temperature, int tempUnit, int loader,int hr) {
		super();
		this.open = open;
		this.pattern = pattern;
		this.power = power;
		this.leftTime = leftTime;
		this.settingTime = settingTime;
		this.temperature = temperature;
		this.tempUnit = tempUnit;
		this.loader = loader;
		this.hr = hr;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public int getPattern() {
		return pattern;
	}
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}
	public int getSettingTime() {
		return settingTime;
	}
	public void setSettingTime(int settingTime) {
		this.settingTime = settingTime;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getTempUnit() {
		return tempUnit;
	}
	public void setTempUnit(int tempUnit) {
		this.tempUnit = tempUnit;
	}
	public int getLoader() {
		return loader;
	}
	public void setLoader(int loader) {
		this.loader = loader;
	}
	@Override
	public String toString() {
		return "MycjMassagerInfo [open=" + open + ", pattern=" + pattern
				+ ", power=" + power + ", leftTime=" + leftTime
				+ ", settingTime=" + settingTime + ", temperature="
				+ temperature + ", tempUnit=" + tempUnit + ", loader=" + loader
				+ "]";
	}
	public int getHr() {
		return hr;
	}
	public void setHr(int hr) {
		this.hr = hr;
	}
	
	
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(open);
		dest.writeInt(pattern);
		dest.writeInt(power);
		dest.writeInt(leftTime);
		dest.writeInt(settingTime);
		dest.writeInt(temperature);
		dest.writeInt(tempUnit);
		dest.writeInt(loader);
		dest.writeInt(hr);
	}
	
	public static final Creator<MycjMassagerInfo> CREATOR = new Creator<MycjMassagerInfo>() {
		
		@Override
		public MycjMassagerInfo[] newArray(int size) {
			return new MycjMassagerInfo[size];
		}
		
		@Override
		public MycjMassagerInfo createFromParcel(Parcel source) {
			int open = source.readInt();
			int pattern = source.readInt();
			int power = source.readInt();
			int leftTime = source.readInt();
			int settingTime = source.readInt();
			int temperature = source.readInt();
			int tempUnit = source.readInt();
			int loader = source.readInt();
			int hr = source.readInt();
			return new MycjMassagerInfo(open, pattern, power, leftTime, settingTime, temperature, tempUnit, loader, hr);
		}
	};
	
	
}
