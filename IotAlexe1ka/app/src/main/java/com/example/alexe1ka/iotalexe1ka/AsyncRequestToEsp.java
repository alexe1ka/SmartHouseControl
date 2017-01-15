package com.example.alexe1ka.iotalexe1ka;

import android.content.Context;
import android.os.AsyncTask;

import com.example.alexe1ka.iotalexe1ka.model.ReplyToRequest;

/**
 * Created by alexe1ka on 08.12.2016.
 */

public class AsyncRequestToEsp extends AsyncTask<Object, Object, ReplyToRequest> {
    private final Context context;

    public AsyncRequestToEsp(Context context) {
        this.context = context;
    }


    @Override
    protected ReplyToRequest doInBackground(Object... strings) {
        Object requestUrl = strings[0];
        HttpRestHandler restHandler = new HttpRestHandler();
        return restHandler.makeUrlRequest((String) requestUrl, "GET");
    }


    @Override
    protected void onPostExecute(ReplyToRequest s) {
        super.onPostExecute(s);
        //Toast.makeText(context, (CharSequence) "Connected status:"+s.getConnectedStatus(), Toast.LENGTH_SHORT).show(); //УБРАТЬ ЭТУ ХРЕНЬ КОГДА ВСЕ БУДЕТ ЗАШИБЕННО РАБОТАТЬ
    }
}
