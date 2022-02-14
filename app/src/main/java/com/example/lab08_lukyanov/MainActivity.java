package com.example.lab08_lukyanov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import math.interp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySurface s = findViewById(R.id.mySurface);

        s.n = 100;

        s.x = new float[s.n];
        s.y = new float[s.n];

        for (int i = 0; i < s.n; i++)
        {
            s.x[i] = interp.map(i, 0, s.n-1, 0.0f, (float)Math.PI * 4.0f);
            s.y[i] = (float)Math.cos(s.x[i]);
        }
        s.update();
        s.invalidate();
    }
}