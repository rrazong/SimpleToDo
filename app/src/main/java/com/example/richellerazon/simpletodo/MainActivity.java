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
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
    implements EditTodoDialogFragment.EditTodoDialogListener {
    List<ToDo> mToDos;
    ListAdapter itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);
        itemsAdapter = new ListAdapter(this);
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();

        ToDo newToDo = new ToDo(itemText);
        newToDo.save();
        itemsAdapter.notifyDataSetChanged();
        etNewItem.setText("");
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
                        // .find() out which ToDo was clicked and then call .delete() ;
                        itemsAdapter.notifyDataSetChanged();
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

                        // ToDo toDo = new ToDo('test');
                        TextView tvTitle = (TextView) view.findViewById(R.id.rowTitle);
                        // find the id and title of the todo that was clicked
                        DialogFragment newFragment = EditTodoDialogFragment.newInstance(1, tvTitle.getText().toString());
                        newFragment.show(ft, "editToDo");
                    }
                }
        );
    }

    @Override
    public void onFinishEditTodoDialog(int pos, String toDoText) {
        // find the id (pos) of the todo that was edited, edit it, .save() it
        itemsAdapter.notifyDataSetChanged();
    }
}

