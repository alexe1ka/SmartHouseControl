package com.example.alexe1ka.iotalexe1ka.fragments;

/**
 * Created by alexe1ka on 02.01.2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexe1ka.iotalexe1ka.AsyncRequestToEsp;
import com.example.alexe1ka.iotalexe1ka.MainActivity;
import com.example.alexe1ka.iotalexe1ka.R;
import com.example.alexe1ka.iotalexe1ka.TabControlActivity;
import com.example.alexe1ka.iotalexe1ka.model.ReplyToRequest;

import java.util.concurrent.ExecutionException;

import static com.example.alexe1ka.iotalexe1ka.ConstRequest.GET_HUM;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.GET_TEMP;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.getUrl;


public class TwoFragment extends Fragment {
    private TextView mTemp;
    private TextView mHum;
    private String ipAddr;

    public TwoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        TabControlActivity activity = (TabControlActivity) getActivity();
        ipAddr = activity.getAddr();

        Button getTemp = (Button) v.findViewById(R.id.getTempFrag);
        Button getHum = (Button) v.findViewById(R.id.getHumFrag);
        mTemp = (TextView) v.findViewById(R.id.tempViewFrag);
        mHum = (TextView) v.findViewById(R.id.humViewFrag);

        getTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncRequestToEsp getTemp = new AsyncRequestToEsp(getActivity());
                ReplyToRequest reqT = null;
                try {
                    reqT = getTemp.execute(getUrl(ipAddr, GET_TEMP)).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                mTemp.setText(reqT.getTemperature());
            }
        });

        getHum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncRequestToEsp getHum = new AsyncRequestToEsp(getActivity());
                ReplyToRequest reqH = null;
                try {
                    reqH = getHum.execute(getUrl(ipAddr, GET_HUM)).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                mHum.setText(reqH.getHumidity());
            }
        });
        return v;
    }
}