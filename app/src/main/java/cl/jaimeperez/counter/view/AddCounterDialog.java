package cl.jaimeperez.counter.view;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import cl.jaimeperez.counter.R;

import static cl.jaimeperez.counter.utils.CONTS.EMPTY_STRING;

public class AddCounterDialog extends DialogFragment {

    private TextInputLayout nameCounterLayout;

    private TextInputEditText nameCounterEditText;

    private String nameCounter;

    private View rootView;
    private MainActivity activity;

    public static AddCounterDialog newInstance(MainActivity activity) {
        AddCounterDialog dialog = new AddCounterDialog();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle(R.string.add_counter_title)
                .setCancelable(false)
                .setPositiveButton(R.string.add_counter, null)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setOnShowListener(dialog -> {
            onDialogShow(alertDialog);
        });
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.add_counter_dialog, null, false);
        nameCounterLayout = rootView.findViewById(R.id.layout_counter);
        nameCounterEditText = rootView.findViewById(R.id.et_namecounter);
    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            onDoneClicked();
        });
    }

    private void onDoneClicked() {
        nameCounter = nameCounterEditText.getText().toString();
        if (isValidName(nameCounterLayout, nameCounter)) {
            activity.addCounter(nameCounter);
            dismiss();
        }
    }

    private boolean isValidName(TextInputLayout layout, String name) {
        if (TextUtils.isEmpty(name)) {
            layout.setErrorEnabled(true);
            layout.setError(getString(R.string.empty_string_error));
            return false;
        }
        layout.setErrorEnabled(false);
        layout.setError(EMPTY_STRING);
        return true;
    }

}
