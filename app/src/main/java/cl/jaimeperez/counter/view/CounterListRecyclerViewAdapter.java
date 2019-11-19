package cl.jaimeperez.counter.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import cl.jaimeperez.counter.R;
import cl.jaimeperez.counter.model.Counter;
import cl.jaimeperez.counter.utils.CONTS;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class CounterListRecyclerViewAdapter extends RecyclerView.Adapter<CounterListRecyclerViewAdapter.ViewHolder> {

    private final List<Counter> mValues;
    private final OnListFragmentInteractionListener mListener;

    public CounterListRecyclerViewAdapter(List<Counter> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_counterlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameCounter.setText(mValues.get(position).getTitle());
        holder.mCounter.setText(mValues.get(position).getCount() + CONTS.EMPTY_STRING);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });

        holder.mIncCounter.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.incrementCounter(holder.mItem);
            }
        });

        holder.mDecCounter.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.decrementCounter(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mNameCounter, mCounter;
        private final ImageButton mDecCounter, mIncCounter;
        private Counter mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mNameCounter = view.findViewById(R.id.content);
            mCounter = view.findViewById(R.id.count);
            mDecCounter = view.findViewById(R.id.dec_counter);
            mIncCounter = view.findViewById(R.id.inc_counter);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameCounter.getText() + "'";
        }
    }
}
