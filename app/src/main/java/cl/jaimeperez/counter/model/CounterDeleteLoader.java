package cl.jaimeperez.counter.model;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;

public class CounterDeleteLoader extends AsyncTaskLoader<List<Counter>> {

    private final Context mContext;
    private String idCounter;

    public CounterDeleteLoader(@NonNull Context context, String idCounter) {
        super(context);
        mContext = context;
        this.idCounter = idCounter;
    }

    @Override
    public List<Counter> loadInBackground() {
        ApiClient callCounter = new ApiClient(mContext);
        return callCounter.deleteCounter(new CounterIdRequest(idCounter));

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
