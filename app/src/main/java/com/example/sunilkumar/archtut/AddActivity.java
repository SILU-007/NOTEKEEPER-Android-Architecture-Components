package com.example.sunilkumar.archtut;


import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.sunilkumar.archtut.Room.BorrowModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private EditText itemEditText;
    private EditText nameEditText;
    private TextView dateTextView;

    private AddBorrowViewModel addBorrowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemEditText = findViewById(R.id.getItemName);
        nameEditText = findViewById(R.id.getPersonName);
        dateTextView=findViewById(R.id.showDate);

        calendar = Calendar.getInstance();
        addBorrowViewModel = ViewModelProviders.of(this).get(AddBorrowViewModel.class);

        datePickerDialog = new DatePickerDialog(this, AddActivity.this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemEditText.getText() == null || nameEditText.getText() == null || date == null)
                    Toast.makeText(AddActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    addBorrowViewModel.addBorrow(new BorrowModel(
                            itemEditText.getText().toString(),
                            nameEditText.getText().toString(),
                            date
                    ));
                    finish();
                }
            }
        });

        findViewById(R.id.floatingActionButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                Toast.makeText(AddActivity.this, "Item Canclled", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });

        findViewById(R.id.setDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();

            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();
        dateTextView.setText(date.toString());

    }


}