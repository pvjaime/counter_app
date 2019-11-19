package cl.jaimeperez.counter.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.List;

import cl.jaimeperez.counter.presenter.CounterContract;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cl.jaimeperez.counter.utils.CONTS.URL_SERVER;

public class ApiClient implements CounterContract.Model {

    private final Context context;
    private CounterService counterService;
    private Retrofit retrofit;

    public ApiClient(Context context) {
        this.context = context;
        Log.d("TEST", "Holi 1");
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Override
    public List<Counter> getCounters() {
        CounterService service = retrofit.create(CounterService.class);
        Call<List<Counter>> call;
        call = service.getCounters();
        try {
            Response<List<Counter>> response = call.execute();
            if (response.body() != null) {
                return response.body();
            }
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Counter> insertCounter(CounterInsertRequest counter) {
        CounterService service = retrofit.create(CounterService.class);
        Call<List<Counter>> call;
        call = service.insertCounter(counter);
        try {
            Response<List<Counter>> response = call.execute();
            if (response.body() != null) {
                return response.body();
            }
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Counter> incrementCounter(CounterIdRequest body) {
        CounterService service = retrofit.create(CounterService.class);
        Call<List<Counter>> call;
        call = service.incrementCounter(body);
        try {
            Response<List<Counter>> response = call.execute();
            if (response.body() != null) {
                return response.body();
            }
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Counter> decrementCounter(CounterIdRequest body) {
        CounterService service = retrofit.create(CounterService.class);
        Call<List<Counter>> call;
        call = service.decrementCounter(body);
        try {
            Response<List<Counter>> response = call.execute();
            if (response.body() != null) {
                return response.body();
            }
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Counter> deleteCounter(CounterIdRequest body) {
        CounterService service = retrofit.create(CounterService.class);
        Call<List<Counter>> call;
        call = service.deleteCounter(body);
        try {
            Response<List<Counter>> response = call.execute();
            if (response.body() != null) {
                return response.body();
            }
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
