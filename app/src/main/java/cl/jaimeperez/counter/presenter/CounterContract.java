package cl.jaimeperez.counter.presenter;

import java.util.List;

import cl.jaimeperez.counter.model.Counter;
import cl.jaimeperez.counter.model.CounterIdRequest;
import cl.jaimeperez.counter.model.CounterInsertRequest;
import cl.jaimeperez.counter.view.BaseView;

public interface CounterContract {

    interface View extends BaseView<Presenter> {

        void showErrorView();

        void showProgress();

        void dismissProgress();

        void setCounters(List<Counter> counters);

        void showNoResultsView();

        void addCounter(String name);

    }

    interface ViewSimple extends BaseView<Presenter> {

        void showErrorView();

        void setCounters(List<Counter> counters);

    }

    interface Presenter {

        void loadCounters();

        void insertCounter(String nameCounter);

        void updateCounter(String id, boolean isincrement);

        void deleteCounter(String id);

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
