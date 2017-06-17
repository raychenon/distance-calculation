package com.raychenon.distancecalculation;

import com.raychenon.distancecalculation.http.DistanceService;
import com.raychenon.distancecalculation.http.response.DistanceMatrixResponse;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {

    @BindView(R.id.start_auto_textview)
    AutoCompleteTextView startAutoTxtView;

    @BindView(R.id.end_auto_textview)
    EditText endAutoTxtView;

    @BindView(R.id.distance_text)
    TextView distanceTextView;

    @BindView(R.id.calculate_button)
    Button button;

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.transportation_mode)
    TextView transportationTxtView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.calculate_button)
    public void calculate(final Button button) {
        trigger();
    }

    private void trigger() {
        Log.d("FormActivity", "calculate: start" + startAutoTxtView.getText());

        DistanceService service = new DistanceService();
        service.request(startAutoTxtView.getText().toString(), endAutoTxtView.getText().toString(),
                   getRadioGroupChoice()).enqueue(new Callback<DistanceMatrixResponse>() {
                       @Override
                       public void onResponse(final Call<DistanceMatrixResponse> call,
                               final Response<DistanceMatrixResponse> response) {
                           if (response.isSuccessful()) {
                               DistanceMatrixResponse dmResponse = response.body();
                               distanceTextView.setText(dmResponse.getDistance());
                           } else {
                               distanceTextView.setText(getString(R.string.error_from_query));
                           }

                           transportationTxtView.setText(getRadioGroupChoice());
                       }

                       @Override
                       public void onFailure(final Call<DistanceMatrixResponse> call, final Throwable t) {
                           t.printStackTrace();
                           distanceTextView.setText(t.getMessage());
                       }
                   });

    }

    private String getRadioGroupChoice() {
        switch (radioGroup.getCheckedRadioButtonId()) {

            case R.id.radio_bike :
                return getString(R.string.tm_bike);

            case R.id.radio_driving :
                return getString(R.string.tm_drive);

            case R.id.radio_walking :
                return getString(R.string.tm_walk);

            case R.id.radio_transit :
                return getString(R.string.tm_transit);

            default :
                return getString(R.string.tm_drive);
        }
    }
}
