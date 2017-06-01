package com.example.guest.movietime.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.movietime.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchButton) Button searchButton;
    @Bind(R.id.titleInput) EditText titleView;
    @Bind(R.id.yearInput) EditText yearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == searchButton) {
            String title = titleView.getText().toString();
            if (title.trim().length() != 0) {
                String year = yearView.getText().toString();
                Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("year", year);
                startActivity(intent);
            } else {
                titleView.setError("Required");
                titleView.setText("");
            }
        }
    }

}
