package cl.jaimeperez.counter.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cl.jaimeperez.counter.R;
import cl.jaimeperez.counter.model.Counter;

import static cl.jaimeperez.counter.utils.CONTS.COUNTER_LIST;
import static cl.jaimeperez.counter.utils.CONTS.ONE_VALUE;

public class MainActivity extends AppCompatActivity {

    private CounterListFragment counterListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Read data from Splash
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            List<Counter> receivedCounterList = (List<Counter>) extras.getSerializable(COUNTER_LIST);
            if (receivedCounterList != null && receivedCounterList.size() > ONE_VALUE) {
                counterListFragment = CounterListFragment.newInstance(ONE_VALUE);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container, counterListFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }


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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
