package com.m.redius;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.menory.Paints;
import com.m.mains.MainActivity;
import com.m.thread.Runn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

@SuppressLint({ "DrawAllocation", "ClickableViewAccessibility" })
public class Circle extends View {
	public Circle(Context context) {
		super(context);
		circle = this;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		
		drawCircle(canvas);
	}
	
	public static Circle getMain(){
		return circle;
	}
	
	public void drawCircle(Canvas canvas) {
		float[] x = new float[24];
		float[] y = new float[24];
		float width = getWidth();
		float height = getHeight();
		float redius = getRedius();
		float textSize = 2 * redius * 0.75f;

		Paint paint = new Paint();
		Paints.Painte(paint, Color.WHITE, 250, true, 2, textSize, Style.STROKE);

		FontMetrics font = new FontMetrics();
		font = paint.getFontMetrics();
		float text_y = redius + (font.descent + font.ascent) / 2;

		for (int i = Padding.m; i < Padding.length; i++) {
			float paddX = X(width, height, redius);
			float paddY = Y(width, height, redius);

			x[i] = paddX;
			y[i] = paddY;
		}

		for (int i = Padding.m; i < Padding.length; i++) {
			if (i < Padding.length - 1) {
				for (int j = (i + 1); j < Padding.length; j++) {
					while (Math.pow(Math.abs(x[i] - x[j]), 2)
							+ Math.pow(Math.abs(y[i] - y[j]), 2) <= Math.pow(
							2 * redius, 2)) {
						int m = 0;
						float paddX = X(width, height, redius);
						float paddY = Y(width, height, redius);

						for (int k = 0; k < j; k++) {
							if (Math.pow(Math.abs(paddX - x[k]), 2)
									+ Math.pow(Math.abs(paddY - y[k]), 2) > Math
										.pow(2 * redius, 2)) {
								m++;
								if (m >= j) {
									x[j] = paddX;
									y[j] = paddY;
								}
							}
						}
					}
				}
			}

			if (!Padding.isTou) {
				if ((i + 1) > 9) {
					canvas.drawText((i + 1) + "", x[i] - redius * 4 / 5, y[i]
							+ text_y, paint);
				} else {
					canvas.drawText((i + 1) + "", x[i] - redius * 1 / 3, y[i]
							+ text_y, paint);
				}

				Padding.X[i] = x[i];
				Padding.Y[i] = y[i];

				Get get = new Get();
				get.setX(Padding.X[i]);
				get.setY(Padding.Y[i]);
				get.setRadius(redius);
				get.setI(i);
				Padding.list.add(get);
				
			}
			
			canvas.drawCircle(Padding.X[i], Padding.Y[i], redius,
					paint);
		}
	}
	
	public void inits(){
		invalidate();
	}

	public void getHan(TextView time,TextView loca){
		Runn run = new Runn(time, loca);
		run.getHand();
	}

	public void Success(List<Get> list,TextView time,TextView loca){
		if(list.size()==0){
			Padding.m = 0;
			Padding.length++;
			Padding.isTou = !Padding.isTou;
			Padding.gu++;
			loca.setText("Loca:"+Padding.gu+"/24");
			
			getHan(time, loca);
		}
	}
	
	public void Default(List<Get> list,TextView time,TextView loca){
		if(list.size()>0){
			Padding.m=0;
			Padding.isTou = !Padding.isTou;
			list.removeAll(list);
			
			getHan(time, loca);
		}
	}
	
	public float X(float width, float height, float redius) {
		float paddX = (float) (Math.random() * width);

		if (paddX >= width - Padding.padd - redius) {
			paddX = width - Padding.padd - redius;
		} else if (paddX <= Padding.padd + redius) {
			paddX = Padding.padd + redius;
		}

		return paddX;
	}

	public float Y(float width, float height, float redius) {
		float paddY = (float) (Math.random() * height);

		if (paddY <= height * 0.05f + redius) {
			paddY = height * 0.05f + redius;
		} else if (paddY >= height - (height * 0.05f) - 10.0f - redius) {
			paddY = height - (height * 0.05f) - 10.0f - redius;
		}
		return paddY;
	}

	public float getWidths() {
		return getWidth();
	}

	public float getHeights() {
		return getHeight();
	}

	public float getRedius() {
		return getWidth() / 16;
	}
	
	
	public static Circle circle;
}




