package com.example.statemanagementextended;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private TextView counterTextView, optionTextView;
    private EditText editText;
    private CheckBox checkBox;
    private Switch themeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterTextView);
        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);
        themeSwitch = findViewById(R.id.themeSwitch);
        optionTextView = findViewById(R.id.optionTextView);
        Button incrementButton = findViewById(R.id.incrementButton);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter");
            counterTextView.setText(String.valueOf(counter));
            editText.setText(savedInstanceState.getString("editText"));
            checkBox.setChecked(savedInstanceState.getBoolean("checkBox"));
            optionTextView.setVisibility(checkBox.isChecked() ? TextView.VISIBLE : TextView.GONE);
            themeSwitch.setChecked(savedInstanceState.getBoolean("themeSwitch"));
            setTheme(themeSwitch.isChecked());
        }

        incrementButton.setOnClickListener(v -> {
            counter++;
            counterTextView.setText(String.valueOf(counter));
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            optionTextView.setVisibility(isChecked ? TextView.VISIBLE : TextView.GONE);
        });

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> setTheme(isChecked));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);
        outState.putString("editText", editText.getText().toString());
        outState.putBoolean("checkBox", checkBox.isChecked());
        outState.putBoolean("themeSwitch", themeSwitch.isChecked());
    }


    private void setTheme(boolean darkMode) {
        if (darkMode) {
            getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
