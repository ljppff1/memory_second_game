package com.m.mains;



import com.example.menory.Rector;
import com.m.redius.Circle;
import com.m.redius.Padding;
import com.m.thread.Runn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	String str;
	String num;
	final String name = "Name";
	final String key = "name";
	final String keys = "num";
	
	float width;
	int height;
	int lin1_height;
	int lin2_height;
	float textSize;
	
	Rector r;
	Circle c;
	
	static MainActivity main;
	
	TextView time,loca;
	LinearLayout lin1,root;
	RelativeLayout lin2;
	
	public MainActivity() {
		main = this;
	}
	
	public static MainActivity getMain(){
		return main;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		DisplayMetrics dis = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dis);

		width = dis.widthPixels;
		height = dis.heightPixels;
		textSize = (int) (height * 0.020);
		lin1_height = (int) (height * 0.05);
		lin2_height = (int) (height * 0.95);
		
		Padding.length = Length();
		Padding.gu = Gu();
		
		CreateAll(width, height, lin1_height, lin2_height, textSize);
		Runn run = new Runn(time, loca);
		run.getHand();
	}
	
	public void CreateAll(float width,int height,int lin1_height,int lin2_height,float textSize){
		r = new Rector(this);
		c = new Circle(this);
		time = new TextView(this);
		loca = new TextView(this);
		root = new LinearLayout(this);
		lin1 = new LinearLayout(this);
		lin2 = new RelativeLayout(this);
		
		CreateTime(time, textSize);
		CreateLoca(loca, textSize);
		CreateRoot(root,width,height);
		CreateLin1(lin1, root, time, loca, width, lin1_height);
		CreateLin2(lin2, root, r, c, width, lin2_height);
		setContentView(root);
	}
	
	public void CreateRoot(LinearLayout root,float width,int height){
		root.setOrientation(LinearLayout.VERTICAL);
		root.setLayoutParams(new LayoutParams(new LinearLayout.LayoutParams((int)width, height)));
		root.setBackgroundColor(Color.BLACK);
	}
	
	public void CreateLin1(LinearLayout lin1, LinearLayout root, TextView time,
			TextView loca, float width, int lin1_height) {
		lin1.setOrientation(LinearLayout.HORIZONTAL);
		lin1.setLayoutParams(new LayoutParams(new LinearLayout.LayoutParams(
				(int)width, lin1_height)));
		lin1.addView(time);
		lin1.addView(loca);
		root.addView(lin1);
	}

	public void CreateLin2(RelativeLayout lin2, LinearLayout root, Rector r,
			Circle c, float width, int lin2_height) {
		lin2.setLayoutParams(new LayoutParams(new LayoutParams((int)width,
				lin2_height)));
		lin2.addView(r);
		lin2.addView(c);
		root.addView(lin2);
	}

	public void CreateTime(TextView time, float textSize) {
		time.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
		time.setGravity(Gravity.CENTER);
		time.setTextColor(Color.WHITE);
		time.setTextSize(textSize);
		time.setText("Time:");
	}

	public void CreateLoca(TextView loca, float textSize) {
		loca.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
		loca.setGravity(Gravity.CENTER);
		loca.setTextColor(Color.WHITE);
		loca.setTextSize(textSize);
		loca.setText("loca:"+Padding.gu+"/24");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		setSare();
	}
	
	public int Length(){
		str = getShare();
		if(str.length()<=0){
			str = "6";
		}
		return Integer.parseInt(str);
	}
	
	public int Gu(){
		num = getShareNum();
		if(num.length()<=0){
			num = "1";
		}
		
		return Integer.parseInt(num);
	}
	
	public void setSare(){
		SharedPreferences share = getSharedPreferences(name,Context.MODE_PRIVATE);
		Editor edit = share.edit();
		edit.putString(keys, Padding.gu+"");
		edit.putString(key, Padding.length+"");
		edit.commit();
	}
	
	public String getShare(){
		String values = null;
		SharedPreferences share = getSharedPreferences(name, Context.MODE_PRIVATE);
		values = share.getString(key, "6");
		return values;
	}
	
	public String getShareNum(){
		String valus = null;
		SharedPreferences share = getSharedPreferences(name, Context.MODE_PRIVATE);
		valus = share.getString(keys, "1");
		return valus;
	}
	
	public void ClearShare(){
		SharedPreferences share = getSharedPreferences(name, MODE_PRIVATE);
		Editor edit = share.edit();
		edit.clear();
		edit.commit();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Padding.isTou = true;
			Padding.num = 0;
			float x = event.getX();
			float y = event.getY();
			for (int i = Padding.m; i < Padding.length; i++) {
				if (x >= Padding.X[i] - getRadius()
						&& x <= Padding.X[i] + getRadius()
						&& y >= Padding.Y[i] - getRadius()+2*lin1_height
						&& y <= Padding.Y[i] + getRadius()+2*lin1_height) {
					
					if (Padding.X[i] == Padding.list.get(0).getX()
							&& Padding.Y[i] == Padding.list.get(0).getY()) {
						Padding.m++;
						Padding.list.remove(0);
						Circle.getMain().Success(Padding.list,time,loca);
					}else{
						Circle.getMain().Default(Padding.list,time,loca);
					}
				}
			}

			Circle.getMain().inits();
			break;
		}

		return true;
	}
	
	public float getRadius(){
		return width/16;
	}
	
}
































