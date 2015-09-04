package com.m.thread;

import java.text.NumberFormat;

import com.m.redius.Circle;
import com.m.redius.Padding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class Runn{
	TextView time, loca;
	Sleep sleep = new Sleep();

	public Runn(TextView time, TextView loca) {
		this.time = time;
		this.loca = loca;
	}
	
	public void getHand() {
		Padding.num = getTimes();
		Padding.isOK = false;
		final Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0x123) {
					getTime();
				}else if(msg.what==5){
					Padding.isTou = true;
					Circle.getMain().inits();
				}
			};
		};

		new Thread() {
			public void run() {
				while (Padding.num>0.0f) {
					try {
						Thread.sleep(100);
						Padding.num -= 0.1f;
						handler.sendEmptyMessage(0x123);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				Padding.num = 0;
				Padding.isOK = true;
				handler.sendEmptyMessage(5);
				Thread.interrupted();
			};
		}.start();
	}

	public void getTime() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		time.setText("Time:" + nf.format(Padding.num));
	}

	public float getTimes() {
		return 10.10f;
	}
}
