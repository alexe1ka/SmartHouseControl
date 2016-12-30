package com.example.alexe1ka.iotalexe1ka;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import java.util.Objects;

/**
 * Created by alexe1ka on 29.12.2016.
 */

public class AsyncLoader extends AsyncTaskLoader{
    private String mRequest;

    public AsyncLoader(Context context, Bundle args) {
        super(context);
        mRequest = args.toString();
    }



    @Override
    public String loadInBackground() {
        String result = "";
        HttpRestHandler restHandler = new HttpRestHandler();
        result = restHandler.makeUrlRequest(mRequest,"GET");
        return result;
    }


}
