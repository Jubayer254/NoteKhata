package com.example.assignment4;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link page3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class page3 extends Fragment {
    private TextInputLayout title, details;
    private Button add;
    private DBHelper DB;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public page3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment page3.
     */
    // TODO: Rename and change types and number of parameters
    public static page3 newInstance(String param1, String param2) {
        page3 fragment = new page3();
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
        View view = inflater.inflate(R.layout.fragment_page3, container, false);
        title = view.findViewById(R.id.editTextTextPersonName3);
        details = view.findViewById(R.id.editTextTextPersonName4);
        add = view.findViewById(R.id.button);
        DB = new DBHelper(getActivity());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleTXT = title.getEditText().getText().toString();
                String detailsTXT = details.getEditText().getText().toString();
                System.out.println("test" + titleTXT);
                System.out.println("test" + detailsTXT);
                if (titleTXT.isEmpty() || detailsTXT.isEmpty()) {
                    Toast.makeText(getActivity(), "Please Fill Informations Correctly", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean chk = DB.insertuserdata(titleTXT, detailsTXT);
                    if (chk) {
                        Toast.makeText(getActivity(), "New Task Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Failed to Add New Task", Toast.LENGTH_SHORT).show();
                    }
                    page1 p1 = new page1();
                    getParentFragmentManager().beginTransaction().replace(R.id.frame_layout,p1).commit();
                }
            }
        });
        return view;
    }
}