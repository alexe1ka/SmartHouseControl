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

import com.example.alexe1ka.iotalexe1ka.R;
import com.example.alexe1ka.iotalexe1ka.data.DataBaseHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


public class InformationFragment extends Fragment {
    private TextView mTemp;
    private TextView mHum;
    private String mIpAddr;
    private DataBaseHelper myDb;
    private GraphView mGraphView;
    private Button mPlotGraph;
    private ArrayList mArrayTemp;
    private ArrayList mArrayTime;
    private int mSizeArr;


    public InformationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        mPlotGraph = (Button) v.findViewById(R.id.plotButton);
        mTemp = (TextView) v.findViewById(R.id.tempViewFrag);
        mHum = (TextView) v.findViewById(R.id.humViewFrag);
        myDb = new DataBaseHelper(getActivity());
        mGraphView = (GraphView) v.findViewById(R.id.graph);

        //TODO
        mPlotGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mArrayTemp = myDb.getAllTemp();
                mArrayTime = myDb.getAllTime();
                mSizeArr = mArrayTemp.size();

                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(
                        new DataPoint[]{
                                new DataPoint(Double.parseDouble(String.valueOf(mArrayTime.get(mSizeArr - 5))), Double.parseDouble(String.valueOf(mArrayTemp.get(mSizeArr - 5)))),
                                new DataPoint(Double.parseDouble(String.valueOf(mArrayTime.get(mSizeArr - 4))), Double.parseDouble(String.valueOf(mArrayTemp.get(mSizeArr - 4)))),
                                new DataPoint(Double.parseDouble(String.valueOf(mArrayTime.get(mSizeArr - 3))), Double.parseDouble(String.valueOf(mArrayTemp.get(mSizeArr - 3)))),
                                new DataPoint(Double.parseDouble(String.valueOf(mArrayTime.get(mSizeArr - 2))), Double.parseDouble(String.valueOf(mArrayTemp.get(mSizeArr - 2)))),
                                new DataPoint(Double.parseDouble(String.valueOf(mArrayTime.get(mSizeArr - 1))), Double.parseDouble(String.valueOf(mArrayTemp.get(mSizeArr - 1))))
                        }
                );
                series.setDrawDataPoints(true);
                series.setDataPointsRadius(10);
                mGraphView.addSeries(series);
            }
        });
        return v;
    }


    private DataPoint[] generateData() {
        int count = mSizeArr;
        DataPoint[] values = new DataPoint[count];
        for (int i = mSizeArr - 6; i < mSizeArr; i++) {
            double x = Double.parseDouble(String.valueOf(mArrayTime.get(i)));
            double y = Double.parseDouble(String.valueOf(mArrayTemp.get(i)));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }
}