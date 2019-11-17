package cl.jaimeperez.counter.model;

import static cl.jaimeperez.counter.utils.CONTS.empty_string;

public class CounterIdRequest {
    private String id;

    CounterIdRequest() {
        this.id = empty_string;
    }

    String getId() {
        return id;
    }
}
