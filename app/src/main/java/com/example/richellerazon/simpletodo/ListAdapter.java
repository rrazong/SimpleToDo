package com.example.richellerazon.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by richellerazon on 10/4/16.
 */
public class ListAdapter extends BaseAdapter {

    private LayoutInflater myInflater;
    private List<ToDo> list;

    public ListAdapter(Context context) {
        myInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        updateList();
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View customView = myInflater.inflate(R.layout.custom_row, parent, false);
        TextView tvTitle = (TextView) customView.findViewById(R.id.rowTitle);

        updateList();
        tvTitle.setText(list.get(position).getTitle());

        return customView;
    }

    private void updateList() {
        list = ToDo.listAll(ToDo.class);
    }
}
