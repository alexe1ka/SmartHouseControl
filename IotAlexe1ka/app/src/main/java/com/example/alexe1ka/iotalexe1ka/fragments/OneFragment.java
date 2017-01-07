package com.example.alexe1ka.iotalexe1ka.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.example.alexe1ka.iotalexe1ka.R;
import com.example.alexe1ka.iotalexe1ka.TabControlActivity;

import java.util.ArrayList;

/**
 * Created by alexe1ka on 02.01.2017.
 */

public class OneFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private String ipAddr;


    public OneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        TabControlActivity activity = (TabControlActivity) getActivity();
        ipAddr = activity.getAddr();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvId);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> descriptionList = new ArrayList<>();
        descriptionList.add("Control signal 1");
        descriptionList.add("Control signal 2");
        descriptionList.add("Control signal 3");
        descriptionList.add("Control signal 4");
        descriptionList.add("Control signal 5");

        mRecyclerView.setAdapter(new RvAdapter(descriptionList, getContext(), ipAddr));

        return view;
    }
}
