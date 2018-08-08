package com.example.sunilkumar.archtut;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sunilkumar.archtut.Room.BorrowModel;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<BorrowModel> borrowModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<BorrowModel> borrowModelList, View.OnLongClickListener longClickListener) {
        this.borrowModelList = borrowModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        BorrowModel borrowModel = borrowModelList.get(position);
        holder.itemTextView.setText(borrowModel.getItemName());
        holder.nameTextView.setText(borrowModel.getFirstName());
        holder.dateTextView.setText(borrowModel.getDate().toLocaleString().substring(0, 12));
        holder.itemView.setTag(borrowModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return borrowModelList.size();
    }

    public void addItems(List<BorrowModel> borrowModelList) {
        this.borrowModelList = borrowModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.itemTextView);
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            dateTextView = (TextView) view.findViewById(R.id.dateTextView);
        }
    }
}