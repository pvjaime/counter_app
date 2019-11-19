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
import cl.jaimeperez.counter.model.CounterAddLoader;
import cl.jaimeperez.counter.model.CounterDeleteLoader;
import cl.jaimeperez.counter.model.CounterLoader;
import cl.jaimeperez.counter.model.CounterUpdateLoader;

import static cl.jaimeperez.counter.utils.CONTS.ONE_VALUE;

public class CounterPresenter implements CounterContract.Presenter {

    private CounterContract.ViewSimple mViewSimple;
    private CounterContract.View mView;
    private CounterLoader counterLoader;
    private CounterAddLoader counterAddLoader;
    private CounterUpdateLoader counterUpdateLoader;
    private CounterDeleteLoader counterDeleteLoader;
    private LoaderManager mLoaderManager;
    private Context mContext;
    private final static int COUNTER_LOAD = -1;
    private final static int COUNTER_ADD = -2;
    private final static int COUNTER_UPDATE = -3;
    private final static int COUNTER_DELETE = -4;

    public CounterPresenter(@NonNull LoaderManager loaderManager,
                            @NonNull CounterContract.ViewSimple view, Context context) {
        mLoaderManager = loaderManager;
        mViewSimple = view;
        mViewSimple.setPresenter(this);
        mContext = context;
    }

    public CounterPresenter(@NonNull LoaderManager loaderManager,
                            @NonNull CounterContract.View view, Context context) {
        mLoaderManager = loaderManager;
        mView = view;
        mView.setPresenter(this);
        mContext = context;
    }

    @Override
    public void loadCounters() {
        counterLoader = new CounterLoader(mContext);
        mLoaderManager.restartLoader(COUNTER_LOAD, null, counterLoadListener);
    }

    @Override
    public void insertCounter(String nameCounter) {
        mView.showProgress();
        counterAddLoader = new CounterAddLoader(mContext, nameCounter);
        mLoaderManager.restartLoader(COUNTER_ADD, null, counterAddListener);
    }

    @Override
    public void updateCounter(String id, boolean isincrement) {
        mView.showProgress();
        counterUpdateLoader = new CounterUpdateLoader(mContext, id, isincrement);
        mLoaderManager.restartLoader(COUNTER_UPDATE, null, counterUpdateListener);
    }

    @Override
    public void deleteCounter(String id) {
        mView.showProgress();
        counterDeleteLoader = new CounterDeleteLoader(mContext, id);
        mLoaderManager.restartLoader(COUNTER_DELETE, null, counterDeleteListener);
    }

    //List Counter
    private LoaderManager.LoaderCallbacks<List<Counter>> counterLoadListener = new LoaderManager.LoaderCallbacks<List<Counter>>() {
        @Override
        public Loader<List<Counter>> onCreateLoader(int id, @Nullable Bundle args) {
            return counterLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<Counter>> loader, List<Counter> data) {
            if (data != null) {
                mViewSimple.setCounters(data);
            } else {
                mViewSimple.showErrorView();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<Counter>> loader) {

        }
    };

    //Add Counter
    private LoaderManager.LoaderCallbacks<List<Counter>> counterAddListener = new LoaderManager.LoaderCallbacks<List<Counter>>() {
        @Override
        public Loader<List<Counter>> onCreateLoader(int id, @Nullable Bundle args) {
            return counterAddLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<Counter>> loader, List<Counter> data) {
            mView.dismissProgress();
            if (data != null) {
                if(data.size() >= ONE_VALUE) {
                    mView.setCounters(data);
                }else{
                    mView.showNoResultsView();
                }
            } else {
                mView.showErrorView();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<Counter>> loader) {

        }
    };

    //Update Counter
    private LoaderManager.LoaderCallbacks<List<Counter>> counterUpdateListener = new LoaderManager.LoaderCallbacks<List<Counter>>() {
        @Override
        public Loader<List<Counter>> onCreateLoader(int id, @Nullable Bundle args) {
            return counterUpdateLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<Counter>> loader, List<Counter> data) {
            mView.dismissProgress();
            if (data != null) {
                if(data.size() >= ONE_VALUE) {
                    mView.setCounters(data);
                }else{
                    mView.showNoResultsView();
                }
            } else {
                mView.showErrorView();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<Counter>> loader) {

        }
    };

    //Delete Counter
    private LoaderManager.LoaderCallbacks<List<Counter>> counterDeleteListener = new LoaderManager.LoaderCallbacks<List<Counter>>() {
        @Override
        public Loader<List<Counter>> onCreateLoader(int id, @Nullable Bundle args) {
            return counterDeleteLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<Counter>> loader, List<Counter> data) {
            mView.dismissProgress();
            if (data != null) {
                if(data.size() >= ONE_VALUE) {
                    mView.setCounters(data);
                }else{
                    mView.showNoResultsView();
                }
            } else {
                mView.showErrorView();
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<Counter>> loader) {

        }
    };
}
