package cl.jaimeperez.counter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import cl.jaimeperez.counter.model.Counter;
import cl.jaimeperez.counter.presenter.CounterContract;
import cl.jaimeperez.counter.presenter.CounterPresenter;
import cl.jaimeperez.counter.view.MainActivity;

import static cl.jaimeperez.counter.utils.CONTS.COUNTER_LIST;

public class SplashActivity extends AppCompatActivity implements CounterContract.ViewSimple {

    //Class of MVP
    public CounterContract.Presenter mPresenter;
    //Progress Bar
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView titleSplash = findViewById(R.id.titleSplash);
        titleSplash.setVisibility(View.VISIBLE);
        progressBar = findViewById(R.id.progressBar);
        //Load the data
        mPresenter = new CounterPresenter(getLoaderManager(), this, this);
        mPresenter.loadCounters();
    }

    @Override
    public void showErrorView() {
        progressBar.setVisibility(View.GONE);
        Snackbar.make(findViewById(android.R.id.content), R.string.error_load_message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility(View.VISIBLE);
                        mPresenter.loadCounters();
                    }
                })
                .show();
    }

    @Override
    public void setCounters(List<Counter> counters) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(COUNTER_LIST, (Serializable) counters);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(CounterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
