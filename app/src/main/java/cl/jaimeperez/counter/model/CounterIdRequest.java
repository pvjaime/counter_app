package cl.jaimeperez.counter.model;

import static cl.jaimeperez.counter.utils.CONTS.EMPTY_STRING;

public class CounterIdRequest {
    private String id;

    CounterIdRequest() {
        this.id = EMPTY_STRING;
    }

    String getId() {
        return id;
    }
}
