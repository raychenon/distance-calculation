package com.raychenon.distancecalculation;

import com.raychenon.distancecalculation.http.DistanceService;
import com.raychenon.distancecalculation.http.response.DistanceMatrixResponse;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
        service.request(startAutoTxtView.getText().toString(), endAutoTxtView.getText().toString(), "driving").enqueue(
            new Callback<DistanceMatrixResponse>() {
                @Override
                public void onResponse(final Call<DistanceMatrixResponse> call,
                        final Response<DistanceMatrixResponse> response) {
                    if (response.isSuccessful()) {
                        DistanceMatrixResponse dmResponse = response.body();
                        distanceTextView.setText(dmResponse.getDistance());
                    } else {
                        distanceTextView.setText(getString(R.string.error_from_query));
                    }
                }

                @Override
                public void onFailure(final Call<DistanceMatrixResponse> call, final Throwable t) {
                    t.printStackTrace();
                    distanceTextView.setText(t.getMessage());
                }
            });

    }
}
