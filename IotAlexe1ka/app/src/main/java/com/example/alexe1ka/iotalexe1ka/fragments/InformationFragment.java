package com.example.alexe1ka.iotalexe1ka.fragments;

/**
 * Created by alexe1ka on 02.01.2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alexe1ka.iotalexe1ka.AsyncRequestToEsp;
import com.example.alexe1ka.iotalexe1ka.R;
import com.example.alexe1ka.iotalexe1ka.TabControlActivity;
import com.example.alexe1ka.iotalexe1ka.data.DataBaseHelper;
import com.example.alexe1ka.iotalexe1ka.model.ReplyToRequest;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.example.alexe1ka.iotalexe1ka.ConstRequest.GET_HUM;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.GET_TEMP;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.getUrl;


public class InformationFragment extends Fragment {
    private TextView mTemp;
    private TextView mHum;
    private String ipAddr;
    private DataBaseHelper myDb;

    private GraphView mGraphView;
    private Button mPlotGraph;


    public InformationFragment() {
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
        Button saveDb = (Button) v.findViewById(R.id.saveDbButton);
        mPlotGraph = (Button) v.findViewById(R.id.plotButton);

        mTemp = (TextView) v.findViewById(R.id.tempViewFrag);
        mHum = (TextView) v.findViewById(R.id.humViewFrag);


        myDb = new DataBaseHelper(getActivity());

        mGraphView = (GraphView) v.findViewById(R.id.graph);


        //TODO ПЕРЕДЕЛАТЬ КОГДА ЗДОРОВЬЕ БУДЕТ ПОЛУЧШЕ
        mPlotGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList arrayTemp = myDb.getAllTemp();
                ArrayList arrayTime = myDb.getAllTime();

                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(Double.parseDouble(String.valueOf(arrayTime.get(0))), Double.parseDouble(String.valueOf(arrayTemp.get(0)))),
                        new DataPoint(Double.parseDouble(String.valueOf(arrayTime.get(1))), Double.parseDouble(String.valueOf(arrayTemp.get(1)))),
                        new DataPoint(Double.parseDouble(String.valueOf(arrayTime.get(2))), Double.parseDouble(String.valueOf(arrayTemp.get(2)))),
                        new DataPoint(Double.parseDouble(String.valueOf(arrayTime.get(3))), Double.parseDouble(String.valueOf(arrayTemp.get(3)))),
                        new DataPoint(Double.parseDouble(String.valueOf(arrayTime.get(4))), Double.parseDouble(String.valueOf(arrayTemp.get(4)))),
                });
                mGraphView.addSeries(series);
            }
        });


        saveDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.insertData(mTemp.getText().toString(), getDateTime());
            }
        });


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

    private String getDateTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


}