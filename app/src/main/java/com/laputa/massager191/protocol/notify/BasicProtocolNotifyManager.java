package com.laputa.massager191.protocol.notify;


import android.content.Context;

import com.laputa.massager191.protocol.bean.MycjFirmwareVersion;
import com.laputa.massager191.protocol.bean.Result;
import com.laputa.massager191.protocol.core.ProtocolBroadcast;
import com.laputa.massager191.protocol.util.DataUtil;

public class BasicProtocolNotifyManager implements IProtocolNotifyManager {
	protected OnProtocolNotifyListener mOnProtocolNotifyListener;
	protected Context context;
	protected ProtocolBroadcast broadcast;
	
	public void setProtocolBroadcast(ProtocolBroadcast mProtocolBroadcast){
		this.broadcast = mProtocolBroadcast;
	}

	/**
	 * 默认构造方法
	 * @param l 回调
	 */
	public BasicProtocolNotifyManager(OnProtocolNotifyListener l) {
		this.mOnProtocolNotifyListener = l;
	}
	
	/**
	 * 默认发广播的构造方法
	 * @param context
	 * @param l 回调
	 */
	public BasicProtocolNotifyManager(Context context, OnProtocolNotifyListener l) {
		this(l);
		this.context = context;
		broadcast = ProtocolBroadcast.newInstance(context);
	}
	
	/**
	 * 自定义广播的构造方法
	 * @param broadcast 广播
	 * @param l 回调
	 */
	public BasicProtocolNotifyManager(ProtocolBroadcast broadcast, OnProtocolNotifyListener l) {
		this(l);
		this.broadcast = broadcast;
	}
	
	@Override
	public synchronized void parse(byte[] data) {
		if (data == null || data.length <= 0) {
			String desc = getDesc()+"data为空";
			if (mOnProtocolNotifyListener != null) {
				mOnProtocolNotifyListener.onError(desc);
			}
			if (broadcast != null) {
				broadcast.sendBroadcastForError(desc);
			}
			return;
		}
		String result = DataUtil.byteToHexString(data);
		String head = result.substring(0, 2);
		int size = data.length;
		try {
			int headInt = Integer.valueOf(head, 16);
			if (headInt == Result.FirmwareVersion.getProtocl() && size == 16) {
				String data_code = result.substring(2, 4);
				String data_name_1 = result.substring(4, 6);
				String data_name_2 = result.substring(6, 8);
				String data_name_3 = result.substring(8, 10);
				String data_name_4 = result.substring(10, 12);
				String data_type = result.substring(12, 14);
				String data_year = result.substring(14, 16);
				String data_month = result.substring(16, 18);
				String data_day = result.substring(18, 20);
				String data_num = result.substring(20, 22);
				String data_index = result.substring(22, 24);
				StringBuffer sb = new StringBuffer();
				int code = Integer.valueOf(data_code, 16);
				String name = sb
						.append("")
						.append((char) (Integer.valueOf(data_name_1, 16)
								.intValue()))
						.append((char) (Integer.valueOf(data_name_2, 16)
								.intValue()))
						.append((char) (Integer.valueOf(data_name_3, 16)
								.intValue()))
						.append((char) (Integer.valueOf(data_name_4, 16)
								.intValue()))
						.append((char) (Integer.valueOf(data_type, 16)
								.intValue()))
						.append(Integer.valueOf(data_year, 16))
						.append(Integer.valueOf(data_month, 16))
						.append(Integer.valueOf(data_day, 16))
						.append(Integer.valueOf(data_num, 16))
						.append(Integer.valueOf(data_index, 16))
						.toString();
				MycjFirmwareVersion mfv = new MycjFirmwareVersion(code, name);
				String desc = getDesc()+"版本:"+name+",版本号:"+code;
				if (mOnProtocolNotifyListener != null) {
					mOnProtocolNotifyListener.onParseMycjFirmwareVersion(desc,mfv);
				}
				if (broadcast != null) {
					broadcast.sendBroadcastForFirmwareVersion(desc,code,name);
				}
				return;
			} else if(headInt == Result.OpenorCloseCallBack.getProtocl() && size == 4){
				String data_ok = result.substring(2, 4);
				int success = Integer.valueOf(data_ok, 16);
				String desc = getDesc()+"开关机成功";
				if (mOnProtocolNotifyListener != null) {
					mOnProtocolNotifyListener.onParseOpenorCloseCallBack(desc,success);
				}
				if (broadcast != null) {
					broadcast.sendBroadcastForOpenOrCloseStatus(desc,success);;
				}
				return;
			} else if(headInt == Result.Electricity.getProtocl() && size == 5){
				String data_all = result.substring(2, 4);
				String data_left = result.substring(4, 6);
				int allElect = Integer.valueOf(data_all, 16);
				int leftElect = Integer.valueOf(data_left, 16);
				String desc = getDesc()+"电池总电量："+allElect+",当前电量："+leftElect;
				if (mOnProtocolNotifyListener != null) {
					mOnProtocolNotifyListener.onParseElectricity(desc,allElect,leftElect);
				}
				if (broadcast != null) {
					broadcast.sendBroadcastForElectricity(desc,allElect, leftElect);
				}
				return;
			} else if(headInt == Result.Test.getProtocl() && size == 4){
				String desc = getDesc()+"测试："+result;
				if (mOnProtocolNotifyListener != null) {
					mOnProtocolNotifyListener.onParseTest(desc);
				}
				if (broadcast != null) {
				}
				return;
			} else if(headInt == Result.FactoryResetStatus.getProtocl() && size == 5){
				String data_ok = result.substring(2, 4);
				int success = Integer.valueOf(data_ok, 16);
				String desc = getDesc()+"恢复出厂设置："+success;
				if (mOnProtocolNotifyListener != null) {
					mOnProtocolNotifyListener.onParseFactoryResetStatus(desc,success);
				}
				if (broadcast != null) {
					broadcast.sendBroadcastForFactoryResetStatus(desc,success);
				}
				return ;
			} 
			
		} catch (Exception e) {
			String desc = getDesc()+"异常:"+e.toString();
			if (mOnProtocolNotifyListener != null) {
				mOnProtocolNotifyListener.onError(desc);
			}
			if (broadcast != null) {
				broadcast.sendBroadcastForError(desc);
			}
		}

	}

	protected String getDesc(){
		return "--ProtocolNotifyManager解析数据 --> ";
	}
	

}
