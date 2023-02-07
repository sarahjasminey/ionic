package com.example.teddy_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Work_Histroy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Work_Histroy extends Fragment {


    ListView listView;
    ArrayList<wallet> arrayList = new ArrayList<>();
    Wallet_Adapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Work_Histroy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Work_Histroy.
     */
    // TODO: Rename and change types and number of parameters
    public static Work_Histroy newInstance(String param1, String param2) {
        Work_Histroy fragment = new Work_Histroy();
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
        View v =  inflater.inflate(R.layout.fragment_work__histroy, container, false);

        listView = v.findViewById(R.id.TIMEtablelistview1);
        arrayList.add(new wallet("Plumber", " Ambattur ,","12:30"));
        arrayList.add(new wallet("Plumber", " Ambattur ,","12:30"));
        arrayList.add(new wallet("Plumber", " Ambattur ,","12:30"));
        adapter = new Wallet_Adapter(getActivity(), arrayList);
        listView.setAdapter(adapter);

        return  v;
    }
}