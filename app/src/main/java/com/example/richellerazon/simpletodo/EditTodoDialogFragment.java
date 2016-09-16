package com.example.richellerazon.simpletodo;

import android.content.Context;
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
    private View view;
    private EditText editTextView;

    static EditTodoDialogFragment newInstance(String toDoText) {
        EditTodoDialogFragment f = new EditTodoDialogFragment();

        Bundle args = new Bundle();
        args.putString("toDoText", toDoText);
        f.setArguments(args);

        return f;
    }

    public interface EditTodoDialogListener {
        void onFinishEditTodoDialog(String toDoText);
    }
    private EditTodoDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the EditNameDialogListener so we can send events to the host
            listener = (EditTodoDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement EditTodoDialogListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Edit ToDo Item");

        view = inflater.inflate(R.layout.edit_item, null);
        editTextView = (EditText) view.findViewById(R.id.editText);
        String mToDoText = getArguments().getString("toDoText");
        editTextView.setText(mToDoText);
        return view;
    }

    public void onClickSave(View v) {
        String itemText = editTextView.getText().toString();

        listener.onFinishEditTodoDialog(itemText);
        this.dismiss();
    }

    public void onClickCancel() {
        this.dismiss();
    }
}
