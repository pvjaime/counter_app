package cl.jaimeperez.counter.model;

import static cl.jaimeperez.counter.utils.CONTS.EMPTY_STRING;

public class CounterInsertRequest {
    private String title;

    CounterInsertRequest() {
        this.title = EMPTY_STRING;
    }

    String getTitle() {
        return title;
    }
}
