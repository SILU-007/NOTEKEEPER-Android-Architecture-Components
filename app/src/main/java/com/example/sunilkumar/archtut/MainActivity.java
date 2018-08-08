package com.example.sunilkumar.archtut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.sunilkumar.archtut.Room.BorrowModel;
import com.example.sunilkumar.archtut.ViewModel.BorrowedListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private BorrowedListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BorrowModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(BorrowedListViewModel.class);

        viewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<BorrowModel>>() {
            @Override
            public void onChanged(@Nullable List<BorrowModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });

    }

    @Override
    public boolean onLongClick(View v) {
        BorrowModel borrowModel = (BorrowModel) v.getTag();
        viewModel.deleteItem(borrowModel);
        return true;
    }
}