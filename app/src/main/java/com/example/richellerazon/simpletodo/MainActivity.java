package com.example.richellerazon.simpletodo;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
    implements EditTodoDialogFragment.EditTodoDialogListener {
    //List<Item> items;
    //ArrayAdapter<String> itemsAdapter;
    ItemListAdapter itemListAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);
        itemListAdapter = new ItemListAdapter(this);
        lvItems.setAdapter(itemListAdapter);

        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemListAdapter.add(new Item(itemText));

        etNewItem.setText("");
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
                        itemListAdapter.remove(pos);
                        itemListAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        Fragment prev = getSupportFragmentManager().findFragmentByTag("editToDo");
                        if (prev != null) {
                            ft.remove(prev);
                        }
                        ft.addToBackStack(null);

                        DialogFragment newFragment = EditTodoDialogFragment.newInstance(pos, itemListAdapter.getItem(pos).title);
                        newFragment.show(ft, "editToDo");
                    }
                }
        );
    }

    @Override
    public void onFinishEditTodoDialog(int pos, String toDoText) {
        Item item = itemListAdapter.getItem(pos);
        item.title = toDoText;
        itemListAdapter.setItem(pos, item);
        itemListAdapter.notifyDataSetChanged();
    }
}

