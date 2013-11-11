package com.natercio.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ArrayList<Fragment> fragments = new ArrayList<Fragment>(2);

    ArrayList<ToDoTask> tasks = new ArrayList<ToDoTask>();
    ToDoAdapter toDoAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoAdapter = new ToDoAdapter(this, tasks);

        fragmentManager = getFragmentManager();

        if (savedInstanceState == null) {
            fragments.add(new MainFragment());
            fragments.add(new EditTaskFragment());

            replaceCurrentFragment(fragments.get(0));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceCurrentFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public class MainFragment extends Fragment {

        public MainFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            Button newTaskBtn = (Button) rootView.findViewById(R.id.new_task);
            newTaskBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceCurrentFragment(fragments.get(1));
                }
            });

            ListView listView = (ListView) rootView.findViewById(R.id.to_do_list);

            if (listView.getAdapter() == null)
                listView.setAdapter(toDoAdapter);

            return rootView;
        }
    }

    public class EditTaskFragment extends Fragment {

        public EditTaskFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_edit_task, container, false);

            Button newBtn = (Button) rootView.findViewById(R.id.edit_task_confirm);
            newBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toDoAdapter.notifyDataSetChanged();

                    replaceCurrentFragment(fragments.get(0));
                }
            });

            return rootView;
        }
    }

}
