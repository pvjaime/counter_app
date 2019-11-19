package cl.jaimeperez.counter.model;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;

public class CounterAddLoader extends AsyncTaskLoader<List<Counter>> {

    private final Context mContext;
    private String nameCounter;

    public CounterAddLoader(@NonNull Context context, String name) {
        super(context);
        mContext = context;
        this.nameCounter = name;
    }

    @Override
    public List<Counter> loadInBackground() {
        ApiClient callCounter = new ApiClient(mContext);
        return callCounter.insertCounter(new CounterInsertRequest(nameCounter));
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
