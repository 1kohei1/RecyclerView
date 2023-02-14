package com.example.recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewTaskFragment extends DialogFragment {

    public interface NewTaskListener {
        void onNewTaskAdd(String newTask);
    }

    NewTaskListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_task_view, null);

        builder.setView(view)
                .setPositiveButton("Add", (dialog, id) -> {
                    TextView textView = view.findViewById(R.id.new_task_text);
                    String newTask = textView.getText().toString();
                    if (!newTask.isEmpty()) {
                        listener.onNewTaskAdd(textView.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    // Close without doing anything.
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NewTaskListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement NewTaskListener");
        }
    }
}