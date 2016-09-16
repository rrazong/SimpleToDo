package com.example.richellerazon.simpletodo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by richellerazon on 9/13/16.
 */
public class EditTodoDialogFragment extends DialogFragment {

    static EditTodoDialogFragment newInstance(String toDoText) {
        EditTodoDialogFragment f = new EditTodoDialogFragment();

        Bundle args = new Bundle();
        args.putString("toDoText", toDoText);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Edit ToDo Item");

        View view = inflater.inflate(R.layout.edit_item, null);
        EditText editTextView = (EditText) view.findViewById(R.id.editText);
        String mToDoText = getArguments().getString("toDoText");
        editTextView.setText(mToDoText);
        return view;
    }

    public void onClickCancel() {
        dismiss();
    }
}
