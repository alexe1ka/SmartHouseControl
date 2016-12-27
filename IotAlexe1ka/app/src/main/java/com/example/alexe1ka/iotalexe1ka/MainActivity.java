package com.example.alexe1ka.iotalexe1ka;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.alexe1ka.iotalexe1ka.ConstRequest.*;

public class MainActivity extends Activity {
    private String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private List<EditText> getPieceOfIP() {
        List<EditText> ipArray = new ArrayList<>(Arrays.asList((EditText) findViewById(R.id.firstPartOfIp),
                (EditText) findViewById(R.id.secondPartOfIp),
                (EditText) findViewById(R.id.thirdPartOfIp),
                (EditText) findViewById(R.id.fourthPartOfIp)));
        return ipArray;
    }

    //обработчик первой кнопки
    public void onClickSendIp(View view) {
        //TODO DUPLICATE CODE
        List<EditText> ipAddr = getPieceOfIP();
        String ipAddress = ipAddr.get(0).getText().toString() + "." + ipAddr.get(1).getText().toString() + "." + ipAddr.get(2).getText().toString() + "." + ipAddr.get(3).getText().toString();
        //интент на новое активити по кнопке
        Intent intent = new Intent(MainActivity.this, ControlActivity.class);
        intent.putExtra("ipAddr", ipAddress);
        startActivity(intent);
    }


    //обработчик второй кнопки
    public void requestIdAndValidation(View view) {
        //TODO DUPLICATE CODE
        List<EditText> ipAddr = getPieceOfIP();
        String ipAddress = ipAddr.get(0).getText().toString() + "." + ipAddr.get(1).getText().toString() + "." + ipAddr.get(2).getText().toString() + "." + ipAddr.get(3).getText().toString();
        if (validator(getPieceOfIP())) {
            //если прошел валидатор - то проверяем checkInternetConnection.
            if (isWifiConnected() || isNetworkConnected()) {
                //есть коннект к интернету
                new AsyncRequestToEsp(this).execute(getUrl(ipAddress, WEMOS_ID));
            } else {
                //нет коннекта к интернету
                //сделать одну кнопку и открытие экрана с настройками
                Toast.makeText(MainActivity.this, "check connection", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Internet is not work\nPlease switching on internet or Wi-Fi network ");
                alertDialogBuilder.setPositiveButton("Open ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //открытие настроек при выключенном интернете
                        Intent settingsIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(settingsIntent);
                        Toast.makeText(MainActivity.this, "Settings Open", Toast.LENGTH_LONG).show();
                    }
                });
/*
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
*/
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Please input valid IP address", Toast.LENGTH_LONG).show();
        }
    }




    //Вынести все это потом в отдельный класс
    //проверка IpAddr
    private boolean validator(List<EditText> ipAddress) {
        //сначала проверяется на введенность строки в EditText(!=NULL)
        for (EditText pieceOfIP : ipAddress) {

            //попробовать поменять местами
            if (pieceOfIP.getText().toString().equals("") || Integer.parseInt(pieceOfIP.getText().toString()) > 255) {
                return false;
            }
        }
        return true;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private boolean isWifiConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) && networkInfo.isConnected();
    }
}
