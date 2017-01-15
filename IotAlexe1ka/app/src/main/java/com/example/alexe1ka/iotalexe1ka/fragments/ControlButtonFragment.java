package com.example.alexe1ka.iotalexe1ka.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexe1ka.iotalexe1ka.R;
import com.example.alexe1ka.iotalexe1ka.TabControlActivity;

import java.util.ArrayList;

/**
 * Created by alexe1ka on 02.01.2017.
 */

public class ControlButtonFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private String ipAddr;


    public ControlButtonFragment() {
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
        descriptionList.add(getString(R.string.description1));
        descriptionList.add(getString(R.string.description2));
        descriptionList.add(getString(R.string.description3));
        descriptionList.add(getString(R.string.description4));
        descriptionList.add(getString(R.string.description5));

        ArrayList<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.ic_looks_one_blue_a400_48dp);
        imageList.add(R.drawable.ic_looks_two_blue_a400_48dp);
        imageList.add(R.drawable.ic_looks_3_blue_a400_48dp);
        imageList.add(R.drawable.ic_looks_4_blue_a400_48dp);
        imageList.add(R.drawable.ic_looks_5_blue_a400_48dp);


        mRecyclerView.setAdapter(new RvAdapter(descriptionList, imageList, getContext(), ipAddr));

        return view;
    }
}
