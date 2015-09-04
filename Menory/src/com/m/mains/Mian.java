package com.m.mains;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;

public class Mian extends Activity{
	public static Mian main;
	
	public Mian() {
		// TODO Auto-generated constructor stub
		main = this;
	}
	
	public static Mian getMain(){
		return main;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		DrawButton draw = new DrawButton(this);
		LinearLayout lin = new LinearLayout(this);
		lin.setLayoutParams(new LayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)));
		lin.setBackgroundColor(Color.BLACK);
		lin.addView(draw);
		
		setContentView(lin);
	}
	
	public void getInte(){
		Intent i = new Intent(Mian.this,MainActivity.class);
		startActivity(i);
	}
	
	public void getFinish(){
		finish();
	}
}













