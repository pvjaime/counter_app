package cl.jaimeperez.counter.model;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;

public class CounterUpdateLoader extends AsyncTaskLoader<List<Counter>> {

    private final Context mContext;
    private String idCounter;
    private boolean increment;

    public CounterUpdateLoader(@NonNull Context context, String idCounter, boolean isIncrement) {
        super(context);
        mContext = context;
        this.idCounter = idCounter;
        this.increment = isIncrement;
    }

    @Override
    public List<Counter> loadInBackground() {
        ApiClient callCounter = new ApiClient(mContext);
        if(increment) {
            return callCounter.incrementCounter(new CounterIdRequest(idCounter));
        }else{
            return callCounter.decrementCounter(new CounterIdRequest(idCounter));
        }
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
