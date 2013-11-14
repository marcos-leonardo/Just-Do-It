package com.natercio.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by natercio on 11/14/13.
 */

public final class MainFragment extends Fragment {

    OnTaskSelectedListener taskSelectedListener;

    ToDoAdapter tasksAdapter;

    View rootView;

    public ToDoAdapter getTasksAdapter() {
        return tasksAdapter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        taskSelectedListener = (OnTaskSelectedListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ToDoDBHelper dbHelper = new ToDoDBHelper(getActivity());
        this.tasksAdapter = new ToDoAdapter(getActivity(), dbHelper.getAllTasks());
        dbHelper.close();

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button newTaskBtn = (Button) rootView.findViewById(R.id.new_task);
        newTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //newTaskIssuedListener.onNewTaskIssued();
                taskSelectedListener.onTaskSelected(null);
            }
        });

        ListView listView = (ListView) rootView.findViewById(R.id.to_do_list);

        if (listView.getAdapter() == null)
            listView.setAdapter(tasksAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                taskSelectedListener.onTaskSelected(tasksAdapter.getItem(i));
            }
        });

        return rootView;
    }

    // INTERFACES //

    public interface OnTaskSelectedListener {
        public void onTaskSelected(ToDoTask task);
    }

}