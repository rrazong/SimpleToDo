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
    private static class ViewHolder {
        TextView title;
    }

    public ItemListAdapter(Context context) {
        super();
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

        ViewHolder viewHolder;
        Item item = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = myInflater.inflate(R.layout.item_row, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.rowTitle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(item.title);

        return convertView;
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