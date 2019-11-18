package cl.jaimeperez.counter.model;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;

public class CounterLoader extends AsyncTaskLoader<List<Counter>> {

    private final Context mContext;

    public CounterLoader(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public List<Counter> loadInBackground() {
        ApiClient callCounter = new ApiClient(mContext);
        return callCounter.getCounters();
    }

    @Override
    public void deliverResult(List<Counter> data) {
        if (isStarted()) {
            // Deliver result if loader is currently started
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        // Start loading
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();
    }
}
