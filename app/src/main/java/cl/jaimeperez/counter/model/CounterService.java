package cl.jaimeperez.counter.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Retrofit interface for the Counter service API.
 */
public interface CounterService {
    //Method to get counters
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("api/v1/counters")
    Call<List<Counter>> getCounters();

    //Method to insert Counter
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/v1/counter")
    Call<List<Counter>> insertCounter(@Body CounterInsertRequest body);

    //Method to increment the counter
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/v1/counter/inc")
    Call<List<Counter>> incrementCounter(@Body CounterIdRequest body);

    //Method to decrement the counter
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/v1/counter/dec")
    Call<List<Counter>> decrementCounter(@Body CounterIdRequest body);

    //Method to delete the counter
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @DELETE("api/v1/counter")
    Call<List<Counter>> deleteCounter(@Body CounterIdRequest body);
}
