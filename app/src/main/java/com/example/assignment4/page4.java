package com.example.assignment4;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
 * Use the {@link page4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class page4 extends Fragment {

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
    MyAdapter3 adapter;
    Cursor c;



    public page4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment page4.
     */
    // TODO: Rename and change types and number of parameters
    public static page4 newInstance(String param1, String param2) {
        page4 fragment = new page4();
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

        View view = inflater.inflate(R.layout.fragment_page4, container, false);
        recyclerView = view.findViewById(R.id.rec3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyAdapter3(getActivity(), T1, T2, T3);
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
                c = DB.getdata3();
                while(c.moveToNext()){
                    if(flag==count) {
                        id = c.getInt(0);
                        page4 p4 = new page4();
                        getParentFragmentManager().beginTransaction().replace(R.id.frame_layout, p4).commit();
                    }
                    flag++;
                }
                Toast.makeText(getActivity(), "Completed task removed", Toast.LENGTH_SHORT).show();
                DB.delete("notekhata",id);

            }
        }).attachToRecyclerView(recyclerView);
        displayData();

        return view;
    }
    private void displayData(){
        c = DB.getdata3();
        while(c.moveToNext()){
            T1.add(c.getString(1));
            T2.add(c.getString(2));
            T3.add(c.getString(0));
        }
    }
}