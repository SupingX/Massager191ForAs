package com.laputa.massager191.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

public abstract class AbstractLaputaDialog {
	protected Context context;
	protected Dialog dialog;
	protected TextView tvMsg;
	protected PopupWindow pop;
	
	protected TextView tvTitle;
	protected TextView tvCancel;
	protected TextView tvConfirm;

	public AbstractLaputaDialog(Context context) {
		this.context = context;
	}
	
	public AbstractLaputaDialog builder() {
		// 获取Dialog布局
		View view = getContentView(context);
		// 获取View控件
		getView(view);
		// 定义Dialog布局和参数
		setDialogWindow(context,view);
		/*dialog = new Dialog(context,R.style.XplAlertDialog);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.CENTER);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);*/
		return AbstractLaputaDialog.this;
	}
	
	public abstract View getContentView(Context context);
	public abstract void getView(View v);
	public abstract Dialog setDialogWindow(Context context,View view);

	public AbstractLaputaDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public AbstractLaputaDialog setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}
	
	public AbstractLaputaDialog setMsg(String msg){
		if (tvMsg != null) {
			tvMsg.setVisibility(View.VISIBLE);
			tvMsg.setText(msg);
		}
		return this;
	}
	
	public AbstractLaputaDialog setTitle(String msg){
		if (tvTitle != null) {
			tvTitle.setVisibility(View.VISIBLE);
			tvTitle.setText(msg);
		}
		return this;
	}
	
	public AbstractLaputaDialog setConfirm(String confirm,View.OnClickListener l){
		if (tvConfirm != null) {
			tvConfirm.setVisibility(View.VISIBLE);
			tvConfirm.setText(confirm);
			tvConfirm.setOnClickListener(l);
			tvConfirm.setTextColor(Color.parseColor(SheetItemColor.Red.getName()));
		}
		return this;
	}
	
	public AbstractLaputaDialog setCancel(String cancel,View.OnClickListener l){
		if (tvCancel != null) {
			tvCancel.setVisibility(View.VISIBLE);
			tvCancel.setText(cancel);
			tvCancel.setOnClickListener(l);
			tvCancel.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));
		}
		return this;
	}
	
	
	public void show() {
		dialog.show();
	}
	
	public void showAsDropDown(View v, int dx, int dy) {
		pop.showAsDropDown(v, dx, dy);
	}

	public void dismiss() {
		dialog.dismiss();
	}
	public boolean isShowing(){
		return dialog.isShowing();
	}


	public enum SheetItemColor {
		Blue("#037BFF"), Red("#FD4A2E");

		private String name;

		private SheetItemColor(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
