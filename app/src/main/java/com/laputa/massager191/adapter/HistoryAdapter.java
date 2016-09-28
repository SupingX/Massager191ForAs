package com.laputa.massager191.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.laputa.massager191.R;
import com.laputa.massager191.bean.History;
import com.laputa.massager191.bean.Pattern;
import com.laputa.massager191.util.Laputa;
import com.laputa.massager191.util.PatternUtil;
import com.laputa.massager191.view.PowerView;


public class HistoryAdapter extends BaseAdapter {
	private final Resources resources;
	private List<History> historys = new ArrayList<History>();
	private Context context;
	private String VIB;
	private String VIB_EMS;
	private String EMS1;
	private String EMS2;
	private String EMS3;
	private String unknown="";

	public HistoryAdapter(List<History> historys,Context context) {
		super();
		this.historys = historys;
		this.context  = context;
		VIB = "";
		VIB_EMS = "";
		EMS1 = "";
		EMS2 ="";
		EMS3 = "";
		unknown ="";
		resources = context.getResources();
	}

	@Override
	public int getCount() {
		return historys.size();
	}

	@Override
	public Object getItem(int position) {
		return historys.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
			vh = new ViewHolder();
			vh.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
			vh.tvPower = (TextView) convertView.findViewById(R.id.tv_power);
			vh.tvModel =  (TextView) convertView.findViewById(R.id.tv_model);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		
		History history = historys.get(position);
		String date = history.getDate();
		Date d = Laputa.stringToDate(date, "yyyyMMdd hh:mm:ss");
		String text = Laputa.dateToString(d, "hh:mm:ss");
		vh.tvDate.setText(text);
		vh.tvPower.setText(String.valueOf(history.getPower()));
		String model = unknown;
		
		
		
		switch (history.getModel()) {
		case 0:
//			model = parent.getContext().getString(Ems.VIB.getText());//这个会导致卡顿
//			model = "VIB";
			model = VIB;
			break;
		case 1:
//			model = parent.getContext().getString(Ems.VIB_EMS.getText());
//			model = "VIB/EMS";
			model = VIB_EMS;
			break;
		case 2:
//			model = parent.getContext().getString(Ems.EMS1.getText());
			model = "EMS1";
			model = EMS1;
			break;
		case 3:
//			model = parent.getContext().getString(Ems.EMS2.getText());
			model = EMS2;
			break;
		case 4:
//			model = parent.getContext().getString(Ems.EMS3.getText());
			model = EMS3;
			break;
		default:
			break;
		}
		vh.tvModel.setText(resources.getString(PatternUtil.getPatternName(history.getModel())));
		return convertView;
	}

	class ViewHolder {
		TextView tvDate;
		TextView tvPower;
		TextView tvModel;
//		ProgressBar pbHistory;
		PowerView pvPower;
	}

}

