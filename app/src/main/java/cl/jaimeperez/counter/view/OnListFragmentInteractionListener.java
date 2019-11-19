package cl.jaimeperez.counter.view;

import cl.jaimeperez.counter.model.Counter;

public interface OnListFragmentInteractionListener {
    void onListFragmentInteraction(Counter item);
    void incrementCounter(Counter item);
    void decrementCounter(Counter item);
}
