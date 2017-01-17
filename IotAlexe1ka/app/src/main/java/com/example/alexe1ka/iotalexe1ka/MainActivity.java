package com.example.alexe1ka.iotalexe1ka;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexe1ka.iotalexe1ka.model.ReplyToRequest;

import java.util.concurrent.ExecutionException;

import static com.example.alexe1ka.iotalexe1ka.ConstRequest.WEMOS_ID;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.getUrl;

public class MainActivity extends AppCompatActivity {
    private String mIpAddress;

    private EditText mFirstEt;
    private EditText mSecondEt;
    private EditText mThirdEt;
    private EditText mFourthEt;

    private String mFirstPart;
    private String mSecondPart;
    private String mThirdPart;
    private String mFourthPart;

    private Button mValidationButton;
    private Button mControlButton;


    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstEt = (EditText) findViewById(R.id.firstPartOfIp);
        mSecondEt = (EditText) findViewById(R.id.secondPartOfIp);
        mThirdEt = (EditText) findViewById(R.id.thirdPartOfIp);
        mFourthEt = (EditText) findViewById(R.id.fourthPartOfIp);

        mValidationButton = (Button) findViewById(R.id.ValidationAndGetId);
        mControlButton = (Button) findViewById(R.id.controlActivityButton);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        mControlButton.setVisibility(View.INVISIBLE);


        mPreferences = MainActivity.this.getSharedPreferences("config_IP", Context.MODE_PRIVATE);

        mFirstEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null && charSequence.length() > 0) {
                    if (charSequence.length() > 2 || charSequence.toString().trim().contains(".")) {
                        if (charSequence.toString().trim().contains(".")) {
                            mFirstPart = charSequence.toString().substring(0, charSequence.length() - 1);
                            mFirstEt.setText(mFirstPart);
                        } else {
                            mFirstPart = charSequence.toString().trim();
                        }

                        if (Integer.parseInt(mFirstPart) > 255) {
                            Toast.makeText(MainActivity.this, R.string.RequestValidIpAddress, Toast.LENGTH_LONG).show();
                            return;
                        }
                        SharedPreferences.Editor editor = mPreferences.edit();
                        editor.putInt("IP_FIRST", mFirstPart.length());
                        editor.commit();
                        mSecondEt.setFocusable(true);
                        mSecondEt.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSecondEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null && charSequence.length() > 0) {
                    if (charSequence.length() > 2 || charSequence.toString().trim().contains(".")) {
                        if (charSequence.toString().trim().contains(".")) {
                            mSecondPart = charSequence.toString().substring(0, charSequence.length() - 1);
                            mSecondEt.setText(mSecondPart);
                        } else {
                            mSecondPart = charSequence.toString().trim();
                        }

                        if (Integer.parseInt(mSecondPart) > 255) {
                            Toast.makeText(MainActivity.this, R.string.RequestValidIpAddress, Toast.LENGTH_LONG).show();
                            return;
                        }
                        SharedPreferences.Editor editor = mPreferences.edit();
                        editor.putInt("IP_SECOND", mSecondPart.length());
                        editor.commit();
                        mThirdEt.setFocusable(true);
                        mThirdEt.requestFocus();
                    }
                }

                if (i == 0 && charSequence.length() == 0) {
                    mFirstEt.setFocusable(true);
                    mFirstEt.requestFocus();
                    mFirstEt.setSelection(mPreferences.getInt("IP_FIRST", 0));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mThirdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null && charSequence.length() > 0) {
                    if (charSequence.length() > 2 || charSequence.toString().trim().contains(".")) {
                        if (charSequence.toString().trim().contains(".")) {
                            mThirdPart = charSequence.toString().substring(0, charSequence.length() - 1);
                            mThirdEt.setText(mThirdPart);
                        } else {
                            mThirdPart = charSequence.toString().trim();
                        }

                        if (Integer.parseInt(mThirdPart) > 255) {
                            Toast.makeText(MainActivity.this, R.string.RequestValidIpAddress, Toast.LENGTH_LONG).show();
                            return;
                        }
                        SharedPreferences.Editor editor = mPreferences.edit();
                        editor.putInt("IP_THIRD", mThirdPart.length());
                        editor.commit();
                        mFourthEt.setFocusable(true);
                        mFourthEt.requestFocus();
                    }
                }

                if (i == 0 && charSequence.length() == 0) {
                    mSecondEt.setFocusable(true);
                    mSecondEt.requestFocus();
                    mSecondEt.setSelection(mPreferences.getInt("IP_SECOND", 0));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mFourthEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null && charSequence.length() > 0) {
                    mFourthPart = charSequence.toString().trim();
                    if (Integer.parseInt(mFourthPart) > 255) {
                        Toast.makeText(MainActivity.this, R.string.RequestValidIpAddress, Toast.LENGTH_LONG).show();
                        return;
                    }
                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putInt("IP_FOURTH", mFourthPart.length());
                    editor.commit();
                }

                if (i == 0 && charSequence.length() == 0) {
                    mSecondEt.setFocusable(true);
                    mSecondEt.requestFocus();
                    mSecondEt.setSelection(mPreferences.getInt("IP_THIRD", 0));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void requestIdAndValidation(View view) throws ExecutionException, InterruptedException {
        String s = ipMaker();
        if (isWifiConnected() || isNetworkConnected()) {
            AsyncRequestToEsp getId = new AsyncRequestToEsp(this);
            ReplyToRequest reqT = getId.execute(getUrl(s, WEMOS_ID)).get();
            if (reqT.getConnectedStatus() != null) {
                Toast.makeText(MainActivity.this, getString(R.string.connectedStatus) + reqT.getConnectedStatus(), Toast.LENGTH_SHORT).show();
                mValidationButton.setVisibility(View.INVISIBLE);
                mControlButton.setVisibility(View.VISIBLE);
            }
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(R.string.internetIsNotWorking);
            alertDialogBuilder.setPositiveButton(R.string.open, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent settingsIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(settingsIntent);
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    public void goToControl(View view) {
        String s = ipMaker();
        Intent intent = new Intent(MainActivity.this, TabControlActivity.class);
        intent.putExtra("ipAddr", s);
        startActivity(intent);
        finish();
    }

    private String ipMaker() {
        if (TextUtils.isEmpty(mFirstPart) || TextUtils.isEmpty(mSecondPart)
                || TextUtils.isEmpty(mThirdPart) || TextUtils.isEmpty(mFourthPart)) {
            Toast.makeText(MainActivity.this, R.string.RequestValidIpAddress, Toast.LENGTH_LONG).show();
        }
        return mIpAddress = mFirstPart + "." + mSecondPart + "." + mThirdPart + "." + mFourthPart;
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
