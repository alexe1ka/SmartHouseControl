package com.example.alexe1ka.iotalexe1ka.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.alexe1ka.iotalexe1ka.AsyncRequestToEsp;
import com.example.alexe1ka.iotalexe1ka.R;

import java.util.ArrayList;

import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN0_OFF;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN0_ON;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN1_OFF;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN1_ON;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN2_OFF;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN2_ON;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN3_OFF;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN3_ON;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN4_OFF;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.PIN4_ON;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.getUrl;


/**
 * Created by alexe1ka on 07.01.2017.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    ArrayList<String> descriptionList;
    private Context mContext;
    private String mIpAddr;

    public RvAdapter(ArrayList<String> descriptionList, Context context, String ipAddr) {
        this.descriptionList = descriptionList;
        mContext = context;
        mIpAddr = ipAddr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.info.setText(descriptionList.get(position));
        holder.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        if (holder.toggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN0_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN0_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if (holder.toggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN1_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN1_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        if (holder.toggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN2_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN2_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked3", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        if (holder.toggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN3_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN3_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked4", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        if (holder.toggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN4_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN4_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked5", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return descriptionList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView info;
        ToggleButton toggleButton;

        public ViewHolder(View itemView) {
            super(itemView);
            info = (TextView) itemView.findViewById(R.id.descriptionText);
            toggleButton = (ToggleButton) itemView.findViewById(R.id.cntrlTogBut);
        }
    }
}
