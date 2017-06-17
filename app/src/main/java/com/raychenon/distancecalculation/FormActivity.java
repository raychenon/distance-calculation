package com.raychenon.distancecalculation;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.widget.AutoCompleteTextView;

import butterknife.BindView;

public class FormActivity extends AppCompatActivity {

    @BindView(R.id.start_auto_textview)
    AutoCompleteTextView startAutoTxtView;

    @BindView(R.id.end_auto_textview)
    AutoCompleteTextView endAutoTxtView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);
    }
}
