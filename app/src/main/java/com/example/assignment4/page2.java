package com.example.assignment4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link page2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class page2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<String> T1,T2, T3;
    DBHelper DB;
    MyAdapter2 adapter;
    Cursor c;


    public page2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment page2.
     */
    // TODO: Rename and change types and number of parameters
    public static page2 newInstance(String param1, String param2) {
        page2 fragment = new page2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DB = new DBHelper(getActivity());
        T1 = new ArrayList<>();
        T2 = new ArrayList<>();
        T3 = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_page2, container, false);
        recyclerView = view.findViewById(R.id.rec2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyAdapter2(getActivity(), T1, T2, T3);
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                long id=-1;
                int count = viewHolder.getAdapterPosition();
                int flag = 0;
                c = DB.getdata2();
                while(c.moveToNext()){
                    if(flag==count) {
                        id = c.getInt(0);
                        page2 p2 = new page2();
                        getParentFragmentManager().beginTransaction().replace(R.id.frame_layout, p2).commit();
                    }
                    flag++;
                }
                Toast.makeText(getActivity(), "Task completed successfully", Toast.LENGTH_SHORT).show();
                DB.update2("notekhata",id);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }


                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),"My Notification");
                builder.setContentTitle("Congratulation");
                builder.setContentText("A New Task is Completed");
                builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
                managerCompat.notify(1, builder.build());

            }
        }).attachToRecyclerView(recyclerView);
        displayData();
        return view;
    }
    private void displayData(){
        c = DB.getdata2();
        while(c.moveToNext()){
            T1.add(c.getString(1));
            T2.add(c.getString(2));
            T3.add(c.getString(0));
        }
    }
}