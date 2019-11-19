package cl.jaimeperez.counter.model;

import static cl.jaimeperez.counter.utils.CONTS.EMPTY_STRING;

public class CounterInsertRequest {
    private String title;

    CounterInsertRequest() {
        this.title = EMPTY_STRING;
    }

    public CounterInsertRequest(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }
}
