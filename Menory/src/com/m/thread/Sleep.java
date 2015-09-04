package com.m.thread;

import android.os.Handler;

public class Sleep {
	public void onCreate(final int time){
		new Thread(){
			public void run() {
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	public void OnCreateA(final int time,final Handler handler){
		new Thread(){
			public void run() {
				try {
					Thread.sleep(time);
					handler.sendEmptyMessage(0x123);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
