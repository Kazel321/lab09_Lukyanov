package com.example.lab08_lukyanov;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import math.arr;
import math.interp;

public class MySurface extends SurfaceView {

    float xmin, xmax, ymin, ymax;
    float[] x;
    float[] y;
    int n;
    Paint p;

    public void update()
    {
        xmin = arr.min(x, n);
        xmax = arr.max(x, n);
        ymin = arr.min(y, n);
        ymax = arr.max(y, n);
    }

    public MySurface(Context context, AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();
        p.setColor(Color.RED);

        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.WHITE);

        int w = canvas.getWidth();
        int h = canvas.getHeight();

        float x0 = 0.0f, y0 = 0.0f, x1, y1;

        for (int i = 0; i < n; i++)
        {
            x1 = interp.map(x[i], xmin, xmax, 0, w-1);
            y1 = interp.map(y[i], ymin, ymax, h-1, 0);

            if (i>0) canvas.drawLine(x0,y0, x1, y1, p);

            x0 = x1;
            y0 = y1;
        }
        //super.onDraw(canvas);
    }
}
