package cl.jaimeperez.counter.presenter;

import java.util.List;

import cl.jaimeperez.counter.model.Counter;
import cl.jaimeperez.counter.model.CounterIdRequest;
import cl.jaimeperez.counter.model.CounterInsertRequest;
import cl.jaimeperez.counter.view.BaseView;

public interface CounterContract {

    interface View extends BaseView<Presenter> {

        void showErrorView();

        void showErrorView(String msg);

        void showProgress();

        void dismissProgress();

        void setCounters(List<Counter> counters);

        void showNoResultsView();

    }

    interface ViewSimple extends BaseView<Presenter> {

        void showErrorView();

        void setCounters(List<Counter> counters);

    }

    interface Presenter {

        void retry();

        void loadCounters();

        List<Counter> insertCounter(String nameCounter);

        List<Counter> updateCounter(String id, boolean isincrement);

        List<Counter> deleteCounter(String id);

    }

    interface Model {

        //Method to List Counter
        List<Counter> getCounters();

        //Method to insert Counter
        List<Counter> insertCounter(CounterInsertRequest counter);

        //Method to increment the counter
        List<Counter> incrementCounter(CounterIdRequest body);

        //Method to decrement the counter
        List<Counter> decrementCounter(CounterIdRequest body);

        //Method to delete the counter
        List<Counter> deleteCounter(CounterIdRequest body);
    }
}
