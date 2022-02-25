package com.example.lab08_lukyanov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationBarView;

import math.interp;

public class MainActivity extends AppCompatActivity {

    Intent i;
    MySurface s;
    int N, func = 0;
    Float Xmax, Xmin;

    AlertDialog dialogFunc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = findViewById(R.id.mySurface);

        LayoutInflater dialogLayout = LayoutInflater.from(this);
        View dialogView = dialogLayout.inflate(R.layout.dialog_function, null);
        dialogFunc = new AlertDialog.Builder(this).create();
        dialogFunc.setView(dialogView);

        ArrayAdapter <String> adp = new <String> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adp.add("y = cos(x)");
        adp.add("y = sin(x)");
        adp.add("y = tan(x)");
        adp.add("y = cosh(x)");
        adp.add("y = sinh(x)");

        Spinner l_func;

        l_func = dialogView.findViewById(R.id.spr_Func);
        l_func.setAdapter(adp);
        final boolean[] spinInit = {false};
        l_func.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!spinInit[0])
                {
                    spinInit[0] = true;
                    return;
                }
                func = position;
                if (dialogFunc.isShowing())
                dialogFunc.cancel();
                onUpdate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        i = new Intent(this, DataActivity.class);
        startActivityForResult(i, 12345);


    }


    void onUpdate()
    {
        for (int i = 0; i < s.n; i++)
        {
            s.x[i] = interp.map(i, 0, s.n-1, Xmin, Xmax);
            switch (func)
            {
                case 0: s.y[i] = (float)Math.cos(s.x[i]); break;
                case 1: s.y[i] = (float)Math.sin(s.x[i]); break;
                case 2: s.y[i] = (float)Math.tan(s.x[i]); break;
                case 3: s.y[i] = (float)Math.cosh(s.x[i]); break;
                case 4: s.y[i] = (float)Math.sinh(s.x[i]); break;
            }
        }
        s.update();
        s.invalidate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null)
        {
            N = data.getIntExtra("N", 0);
            Xmax = data.getFloatExtra("Xmax", 0);
            Xmin = data.getFloatExtra("Xmin", 0);

            s.n = N;
            s.x = new float[s.n];
            s.y = new float[s.n];

            onUpdate();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.itm_Points:
                i = new Intent(this, DataActivity.class);
                startActivityForResult(i, 12345);
            case R.id.itm_Func:
                dialogFunc.show();
        }
        return super.onOptionsItemSelected(item);
    }
}