package com.natercio.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by natercio on 11/14/13.
 */
public class EditTaskFragment extends Fragment {

    OnConfirmClickListener confirmClickListener;

    View rootView;

    EditText titleTxt;
    EditText contentTxt;

    ToDoTask task;

    public EditTaskFragment(ToDoTask task) {
        this.task = task;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        confirmClickListener = (OnConfirmClickListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_task, container, false);

        titleTxt = (EditText) rootView.findViewById(R.id.edit_task_title);
        contentTxt = (EditText) rootView.findViewById(R.id.edit_task_content);

        if (task != null) {
            titleTxt.setText(task.getTitle());
            contentTxt.setText(task.getContent());
        }

        Button confirmBtn = (Button) rootView.findViewById(R.id.edit_task_confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDoDBHelper dbHelper = new ToDoDBHelper(getActivity());

                if (task == null) {
                    task = new ToDoTask(titleTxt.getText().toString(), contentTxt.getText().toString());

                    dbHelper.putTask(task);
                } else {
                    task.setTitle(titleTxt.getText().toString());
                    task.setContent(contentTxt.getText().toString());
                    task.setDueDate("");

                    dbHelper.updateTask(task);
                }

                dbHelper.close();

                confirmClickListener.onConfirmClick(task);
            }
        });

        return rootView;
    }

    public interface OnConfirmClickListener {
        public void onConfirmClick(ToDoTask task);
    }

}
