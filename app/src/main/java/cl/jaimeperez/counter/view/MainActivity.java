package cl.jaimeperez.counter.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.jaimeperez.counter.R;
import cl.jaimeperez.counter.model.Counter;
import cl.jaimeperez.counter.presenter.CounterContract;
import cl.jaimeperez.counter.presenter.CounterPresenter;

import static cl.jaimeperez.counter.utils.CONTS.COUNTER_LIST;
import static cl.jaimeperez.counter.utils.CONTS.ONE_VALUE;

public class MainActivity extends AppCompatActivity implements CounterContract.View, OnListFragmentInteractionListener {
    //Class of MVP
    public CounterContract.Presenter mPresenter;
    //Progress Bar
    private ProgressBar progressBar;
    //List of data
    private static final String ADD_COUNTER_DIALOG_TAG = "add_counter_dialog_tag";
    //Data list
    List<Counter> receivedCounterList = null;
    //Empty String
    private TextView emptyString;
    //List
    private RecyclerView listCounter;
    private LinearLayout containerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Find Objects
        emptyString = findViewById(R.id.empty_string);
        progressBar = findViewById(R.id.progressBar);
        listCounter = findViewById(R.id.list);
        containerList = findViewById(R.id.container_list);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> openDialogAddCounter());

        //Read data from Splash
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            receivedCounterList = (List<Counter>) extras.getSerializable(COUNTER_LIST);
            loadListView();
        }
        //Set Presenter
        mPresenter = new CounterPresenter(getLoaderManager(), this, this);
    }

    private void openDialogAddCounter() {
        AddCounterDialog dialog = AddCounterDialog.newInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), ADD_COUNTER_DIALOG_TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_credits) {
            Intent intent = new Intent(getApplicationContext(), CreditActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showErrorView() {
        dismissProgress();
        Snackbar.make(findViewById(android.R.id.content), R.string.error_load_message, Snackbar.LENGTH_INDEFINITE)
                .show();
    }

    @Override
    public void showProgress() {
        emptyString.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCounters(List<Counter> counters) {
        receivedCounterList = counters;
        loadListView();
    }

    @Override
    public void showNoResultsView() {
        emptyString.setVisibility(View.VISIBLE);
        containerList.setVisibility(View.GONE);
    }

    @Override
    public void addCounter(String name) {
        mPresenter.insertCounter(name);
    }

    @Override
    public void setPresenter(CounterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    //Load the data of the view
    private void loadListView() {
        if (receivedCounterList != null && receivedCounterList.size() >= ONE_VALUE) {
            containerList.setVisibility(View.VISIBLE);
            listCounter.setLayoutManager(new LinearLayoutManager(this));
            listCounter.setAdapter(new CounterListRecyclerViewAdapter(receivedCounterList, this));
        } else {
            showNoResultsView();
        }
    }

    @Override
    public void onListFragmentInteraction(Counter item) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.delete_title_dialog)
                .setMessage(R.string.delete_confirmation)
                .setPositiveButton(R.string.yes, (dialog, which) -> mPresenter.deleteCounter(item.getId()))
                .setNegativeButton(R.string.no, null)
                .show();
    }

    @Override
    public void incrementCounter(Counter item) {
        mPresenter.updateCounter(item.getId(), true);
    }

    @Override
    public void decrementCounter(Counter item) {
        mPresenter.updateCounter(item.getId(), false);
    }
}
