package com.example.richellerazon.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class ItemListAdapter extends BaseAdapter {

    private LayoutInflater myInflater;

    public ItemListAdapter(Context context) {
        myInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (int) SQLite.selectCountOf().from(Item.class).count();
    }

    @Override
    public Item getItem(int position) {
        List<Item> results = SQLite.select().from(Item.class).limit(1).offset(position).queryList();
        return results.get(0);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View customView = myInflater.inflate(R.layout.item_row, parent, false);
        TextView tvTitle = (TextView) customView.findViewById(R.id.rowTitle);

        tvTitle.setText(getItem(position).title);

        return customView;
    }

    public void add(Item item) {
        item.save();
    }

    public void remove(int pos) {
        getItem(pos).delete();
    }

    public void setItem(int pos, Item item) {
        item.id = getItemId(pos);
        item.save();
    }
}