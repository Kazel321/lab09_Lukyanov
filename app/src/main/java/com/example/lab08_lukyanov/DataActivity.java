package com.example.lab08_lukyanov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {

    Intent i;
    int N;
    Float Xmin, Xmax;
    EditText txt_Count, txt_Xmin, txt_Xmax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        txt_Count = findViewById(R.id.txt_Count);
        txt_Xmax = findViewById(R.id.txt_Xmax);
        txt_Xmin = findViewById(R.id.txt_Xmin);
    }
    //Same as back button
    public void onConfirm(View v)
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed() //Override of back button
    {
        try { //Input control
            N = Integer.parseInt(txt_Count.getText().toString());
            Xmax = Float.parseFloat(txt_Xmax.getText().toString());
            Xmin = Float.parseFloat(txt_Xmin.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Invalid Numeric Data", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Xmax < Xmin) //Min cannot be more than max
        {
            Toast.makeText(this, "Xmin can't be more than Xmax", Toast.LENGTH_SHORT).show();
            return;
        }
        if (N <= 0) //N cannot be less than 0
        {
            Toast.makeText(this, "N should be more than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        i = new Intent();
        i.putExtra("N", N);
        i.putExtra("Xmax", Xmax);
        i.putExtra("Xmin", Xmin);

        setResult(RESULT_OK, i);
        super.onBackPressed();
    }
}