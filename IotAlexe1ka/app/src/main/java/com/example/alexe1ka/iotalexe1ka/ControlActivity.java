package com.example.alexe1ka.iotalexe1ka;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.alexe1ka.iotalexe1ka.model.ReplyToRequest;

import static com.example.alexe1ka.iotalexe1ka.ConstRequest.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class ControlActivity extends Activity {
    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;
    private ToggleButton toggleButton3;
    private String ipAddr;
    private TextView mTemp;
    private TextView mHum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
        ipAddr = getIntent().getExtras().getString("ipAddr");
        TextView ipView = (TextView) findViewById(R.id.ipTextView);
        ipView.setText(ipAddr);

        mTemp = (TextView)findViewById(R.id.tempView);
        mHum = (TextView) findViewById(R.id.humView);

    }


    public void toggle1(View view) throws IOException {
        if (toggleButton1.isChecked()) {
            new AsyncRequestToEsp(this).execute(getUrl(ipAddr, PIN0_ON));
            Toast.makeText(ControlActivity.this, "On", Toast.LENGTH_SHORT).show();
        } else {
            new AsyncRequestToEsp(this).execute(getUrl(ipAddr,PIN0_OFF));
            Toast.makeText(ControlActivity.this, "Off", Toast.LENGTH_SHORT).show();
        }
    }

    public void toggle2(View view) {
        if (toggleButton2.isChecked()) {
            new AsyncRequestToEsp(this).execute(getUrl(ipAddr,PIN1_ON));
            Toast.makeText(ControlActivity.this, "ON", Toast.LENGTH_SHORT).show();
        } else {
            new AsyncRequestToEsp(this).execute(getUrl(ipAddr,PIN1_OFF));
            Toast.makeText(ControlActivity.this, "OFF", Toast.LENGTH_SHORT).show();
        }
    }

    public void toggle3(View view) {
        if (toggleButton3.isChecked()) {
            new AsyncRequestToEsp(this).execute(getUrl(ipAddr,PIN2_ON));
            Toast.makeText(ControlActivity.this, "ON", Toast.LENGTH_SHORT).show();
        } else {
            new AsyncRequestToEsp(this).execute(getUrl(ipAddr,PIN2_OFF));
            Toast.makeText(ControlActivity.this, "OFF", Toast.LENGTH_SHORT).show();
        }
    }


    public void getTemperature(View view) throws ExecutionException, InterruptedException {
        AsyncRequestToEsp getTemp = new AsyncRequestToEsp(this);
        ReplyToRequest reqT = getTemp.execute(getUrl(ipAddr,GET_TEMP)).get();
        mTemp.setText(reqT.getTemperature());
    }

    public void getHumidity(View view) throws ExecutionException, InterruptedException {
        //AsyncRequestToEsp getHum = new AsyncRequestToEsp(this);
        //ReplyToRequest reqH = getHum.execute(getUrl(ipAddr,GET_HUM)).get();
        //mHum.setText(reqH.getHumidity());

    }
}
