package cl.jaimeperez.counter.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cl.jaimeperez.counter.model.Counter;
import cl.jaimeperez.counter.model.CounterLoader;

public class CounterPresenter implements CounterContract.Presenter {

    private CounterContract.ViewSimple mViewSimple;
    private CounterContract.View mView;
    private CounterLoader counterLoader;
    private LoaderManager mLoaderManager;
    private Context mContext;
    private final static int COUNTER_LOAD = -1;
    private final static int COUNTER_UPDATE = -2;
    private final static int COUNTER_DELETE = -3;

    public CounterPresenter(@NonNull LoaderManager loaderManager,
                            @NonNull CounterContract.ViewSimple view, Context context) {
        mLoaderManager = loaderManager;
        mViewSimple = view;
        mViewSimple.setPresenter(this);
        mContext = context;
    }

    public CounterPresenter(@NonNull LoaderManager loaderManager,
                            @NonNull CounterContract.View view) {
        mLoaderManager = loaderManager;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void retry() {

    }

    @Override
    public void loadCounters() {
        counterLoader = new CounterLoader(mContext);
        Log.d("TEST", "Holi 1");
        mLoaderManager.restartLoader(COUNTER_LOAD, null, counterLoadListener);
        Log.d("TEST", "Holi 2");
    }

    @Override
    public List<Counter> insertCounter(String nameCounter) {
        return null;
    }

    @Override
    public List<Counter> updateCounter(String id, boolean isincrement) {
        return null;
    }

    @Override
    public List<Counter> deleteCounter(String id) {
        return null;
    }

    private LoaderManager.LoaderCallbacks<List<Counter>> counterLoadListener = new LoaderManager.LoaderCallbacks<List<Counter>>() {
        @Override
        public Loader<List<Counter>> onCreateLoader(int id, @Nullable Bundle args) {
            Log.d("TEST", "Holi 2.1");
            return counterLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<Counter>> loader, List<Counter> data) {
            if(data != null) {
                mViewSimple.setCounters(data);
            }else{
                mViewSimple.showErrorView();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<Counter>> loader) {

        }
    };
}
