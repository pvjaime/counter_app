package cl.jaimeperez.counter.model;

import static cl.jaimeperez.counter.utils.CONTS.empty_string;

public class CounterInsertRequest {
    private String title;

    CounterInsertRequest() {
        this.title = empty_string;
    }

    String getTitle() {
        return title;
    }
}
