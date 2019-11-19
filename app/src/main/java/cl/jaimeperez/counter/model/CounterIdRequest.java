package cl.jaimeperez.counter.model;

import static cl.jaimeperez.counter.utils.CONTS.EMPTY_STRING;

public class CounterIdRequest {
    private String id;

    CounterIdRequest() {
        this.id = EMPTY_STRING;
    }

    public CounterIdRequest(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }
}
