package com.example.alexe1ka.iotalexe1ka.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
    private ArrayList<String> mDescriptionList;
    private Context mContext;
    private String mIpAddr;
    private ArrayList<Integer> mImageList;

    public RvAdapter(ArrayList<String> descriptionList, ArrayList<Integer> imageList, Context context, String ipAddr) {
        this.mDescriptionList = descriptionList;
        this.mImageList = imageList;
        mContext = context;
        mIpAddr = ipAddr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mInfo.setText(mDescriptionList.get(position));
        holder.mImageView.setImageResource(mImageList.get(position));

        holder.mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                        if (holder.mToggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN0_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN0_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if (holder.mToggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN1_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN1_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        if (holder.mToggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN2_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN2_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked3", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        if (holder.mToggleButton.isChecked()) {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN3_ON));
                        } else {
                            new AsyncRequestToEsp(mContext).execute(getUrl(mIpAddr, PIN3_OFF));
                        }
                        //Toast.makeText(mContext, "Clicked4", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        if (holder.mToggleButton.isChecked()) {
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
        return mDescriptionList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mInfo;
        ToggleButton mToggleButton;
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mInfo = (TextView) itemView.findViewById(R.id.descriptionText);
            mToggleButton = (ToggleButton) itemView.findViewById(R.id.cntrlTogBut);
            mImageView = (ImageView) itemView.findViewById(R.id.deviceImage);
        }
    }
}
