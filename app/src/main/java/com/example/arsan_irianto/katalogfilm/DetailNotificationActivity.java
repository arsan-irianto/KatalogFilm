package com.example.arsan_irianto.katalogfilm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNotificationActivity extends AppCompatActivity {

    public static String EXTRA_ID_MOVIE = "EXTRA_ID_MOVIE";

    @BindView(R.id.tv_idfilm)
    TextView tvIdFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);

        ButterKnife.bind(this);

        final String id = getIntent().getStringExtra(EXTRA_ID_MOVIE);

        tvIdFilm.setText(id);
    }
}
