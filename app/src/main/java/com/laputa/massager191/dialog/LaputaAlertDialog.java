package com.laputa.massager191.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Layout;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.laputa.massager191.R;

public class LaputaAlertDialog extends AbstractLaputaDialog {
	
	private int resId;
	private WindowManager windowManager;
	private Display display;
	
	public LaputaAlertDialog(Context context,int resId) {
		super(context);
		this.resId = resId;
		windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}
	

	@Override
	public View getContentView(Context context) {
		View view = LayoutInflater.from(context).inflate(resId,null, false);
		return view;
	}

	@Override
	public void getView(View v) {
		tvMsg = (TextView) v.findViewById(R.id.tv_msg);

	}

	@Override
	public Dialog setDialogWindow(Context context, View view) {
		
		dialog = new Dialog(context, R.style.LaputaDialog);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.CENTER);
		dialogWindow.setBackgroundDrawableResource(R.drawable.bg_pop_trans);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//		lp.x = 0;
//		lp.y = 0;
//		lp.width = (int) (0.45 * display.getWidth());
		dialogWindow.setAttributes(lp);
		return dialog;
	}

}
