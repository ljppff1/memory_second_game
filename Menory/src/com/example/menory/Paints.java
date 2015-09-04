package com.example.menory;

import android.graphics.Paint;

public class Paints {
	public static void Painte(Paint paint,int color,int alpha,boolean alias,float stroke,float textSize,Paint.Style style){
		paint.setColor(color);
		paint.setAlpha(alpha);
		paint.setAntiAlias(alias);
		paint.setStrokeWidth(stroke);
		paint.setStyle(style);
		paint.setTextSize(textSize);
	}
}
