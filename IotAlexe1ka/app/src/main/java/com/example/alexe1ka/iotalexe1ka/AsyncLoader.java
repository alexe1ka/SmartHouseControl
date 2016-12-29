package com.example.alexe1ka.iotalexe1ka;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by alexe1ka on 29.12.2016.
 */

public class AsyncLoader extends AsyncTaskLoader {
    private final String mRequest;

    public AsyncLoader(Context context, String mRequest) {
        super(context);
        this.mRequest = mRequest;
    }

    @Override
    public Object loadInBackground() {

        return null;
    }
}
