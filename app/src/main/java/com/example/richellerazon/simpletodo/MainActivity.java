package com.example.richellerazon.simpletodo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements EditTodoDialogFragment.EditTodoDialogListener {
    ArrayList<String> items;
    TodoCursorAdapter todoAdapter;
    ListView lvItems;
    TodoItemDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new TodoItemDatabase(this);
        // Get access to the underlying writeable database
        SQLiteDatabase db = handler.getWritableDatabase();
        // Query for items from the database and get a cursor back
        Cursor todoCursor = db.rawQuery("SELECT  * FROM todo_items", null);

        // Find ListView to populate
        lvItems = (ListView) findViewById(R.id.lvItems);
        // Setup cursor adapter using cursor from last step
        todoAdapter = new TodoCursorAdapter(this, todoCursor);
        // Attach cursor adapter to the ListView
        lvItems.setAdapter(todoAdapter);

        /*
        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        todoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(todoAdapter);
        */

        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        EditText etNewPriority = (EditText)findViewById(R.id.etNewPriority);
        String itemText = etNewItem.getText().toString();
        String priorityText = etNewPriority.getText().toString();
        TodoItem todoItem = new TodoItem(itemText, priorityText);
        todoAdapter.add(todoItem);
        etNewItem.setText("");
        etNewPriority.setText("");
        // writeItems();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
                        items.remove(pos);
                        todoAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                }
        );

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                        Toast.makeText(MainActivity.this, items.get(pos), Toast.LENGTH_SHORT).show();

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        Fragment prev = getSupportFragmentManager().findFragmentByTag("editToDo");
                        if (prev != null) {
                            ft.remove(prev);
                        }
                        ft.addToBackStack(null);

                        DialogFragment newFragment = EditTodoDialogFragment.newInstance(pos, items.get(pos));
                        newFragment.show(ft, "editToDo");
                    }
                }
        );
    }

    private void readItems() {

        // Query for items from the database and get a cursor back
        Cursor todoCursor = db.rawQuery("SELECT  * FROM ToDo", null);

        db.close();


        /*
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile, "UTF-8"));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
        */
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinishEditTodoDialog(int pos, TodoItem todoItem) {
        items.set(pos, todoItem);
        todoAdapter.notifyDataSetChanged();
        // writeItems();
    }
}

