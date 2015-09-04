package com.m.mains;

import com.example.menory.Paints;
import com.m.redius.Get;
import com.m.redius.Padding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("ClickableViewAccessibility")
public class DrawButton extends View {
	float x, y;
	float[] X = new float[3];
	float[] Y = new float[3];

	Get get = new Get();

	public DrawButton(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Canvass(canvas);
	}

	public void Canvass(Canvas canvas) {
		FontMetrics font = new FontMetrics();

		float size = Radius() * 0.75f;
		float size_add = Radius_Add() * 0.75f;
		float text_y = Radius_Y() / 6.4f - (font.descent + font.ascent) / 2;

		Paint paint, paint_text_add, paint_text;
		paint = new Paint();
		paint_text = new Paint();
		paint_text_add = new Paint();
		Paints.Painte(paint, Color.WHITE, 250, true, 2, 0, Style.FILL);
		Paints.Painte(paint_text, Color.BLACK, 250, true, 2, size, Style.FILL);
		;
		Paints.Painte(paint_text_add, Color.BLACK, 250, true, 2, size_add,
				Style.FILL);

		for (int i = 0; i < 3; i++) {
			canvas.drawCircle(Radius_X(), (i + 1) * Radius_Y(), Radius(), paint);
			canvas.drawText(Padding.name[i], Radius_X() -0.7f* Radius(),
					(i + 1) * Radius_Y() + text_y, paint_text);

			X[i] = Radius_X();
			Y[i] = (i + 1) * Radius_Y();
		}

		if (get.isOK() == true) {
			for (int i = 0; i < 3; i++) {
				if (X[get.getNum()] == X[i] && Y[get.getNum()] == Y[i]) {
					canvas.drawCircle(Radius_X(), (i + 1) * Radius_Y(),
							Radius_Add(), paint);
					canvas.drawText(Padding.name[i], Radius_X() -0.7f* Radius(), (i + 1) * Radius_Y() + text_y,
							paint_text_add);
				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setX(event.getX());
		setY(event.getY());

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			init();
			break;
		case MotionEvent.ACTION_MOVE:
			init();

			break;
		case MotionEvent.ACTION_UP:
			for (int i = 0; i < 3; i++) {
				if (getX() > X[i] - Radius() && getX() < X[i] + Radius()
						&& getY() > Y[i] - Radius() && getY() < Y[i] + Radius()) {

					get.setOK(false);
					get.setNum(i);

					switch (get.getNum()) {
					case 0:
						Padding.button = get.getNum();
						Mian.getMain().getFinish();
						;
						break;
					case 1:
						Padding.button = get.getNum();
						Mian.getMain().getInte();
						break;
					case 2:
						Padding.button = get.getNum();
						Mian.getMain().getInte();
						MainActivity.getMain().ClearShare();
						break;
					}
				}
			}
			break;
		}

		invalidate();
		return true;
	}

	public void init() {
		for (int i = 0; i < 3; i++) {
			if (getX() > X[i] - Radius() && getX() < X[i] + Radius()
					&& getY() > Y[i] - Radius() && getY() < Y[i] + Radius()) {

				get.setOK(true);
				get.setNum(i);
			}
		}
	}

	public float Width() {
		return getWidth();
	}

	public float Height() {
		return getHeight();
	}

	public float Radius_X() {
		return Width() / 2;
	}

	public float Radius_Y() {
		return Height() / 4;
	}

	public float Radius() {
		return Height() / 10;
	}

	public float Radius_Add() {
		return Height() / 10 + 13;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
