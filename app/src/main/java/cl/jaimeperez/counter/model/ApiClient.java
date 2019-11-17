package cl.jaimeperez.counter.model;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cl.jaimeperez.counter.utils.CONTS.url_server;

public class ApiClient  {

    private final Context context;
    private CounterService counterService;
    private Retrofit retrofit;

    public ApiClient(Context context) {
        this.context = context;

        retrofit = new Retrofit.Builder()
                .baseUrl(url_server)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
