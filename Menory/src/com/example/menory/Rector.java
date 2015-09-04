package com.example.menory;

import com.m.redius.Padding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

public class Rector extends View{

	public Rector(Context context) {
		super(context);
	}
	
	@SuppressLint("DrawAllocation") @Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		float width = getWidth();
		float height = getHeight();
		float left = Padding.padd;
		float right = width - Padding.padd;
		float top = 0.0f;
		float bottom = height-(height*0.05f)-10.0f;
		
		Paint paint = new Paint();
		Paints.Painte(paint, Color.WHITE, 250, true, 1, 0.0f,Style.STROKE);
		canvas.drawRect(left, top, right, bottom, paint);
	}
}




















