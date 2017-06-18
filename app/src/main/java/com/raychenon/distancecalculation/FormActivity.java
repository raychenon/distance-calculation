package com.raychenon.distancecalculation;

import java.util.HashSet;
import java.util.Set;

import com.raychenon.distancecalculation.http.DistanceService;
import com.raychenon.distancecalculation.http.response.DistanceMatrixResponse;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
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
    AutoCompleteTextView endAutoTxtView;

    @BindView(R.id.distance_text)
    TextView distanceTextView;

    @BindView(R.id.calculate_button)
    Button button;

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.transportation_mode)
    TextView transportationTxtView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Set<String> startAddressSet = new HashSet<String>();
    private Set<String> endAddressSet = new HashSet<String>();

    ArrayAdapter<String> startAdapter;
    ArrayAdapter<String> endAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);

        ButterKnife.bind(this);

        startAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        endAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        startAutoTxtView.setAdapter(startAdapter);
        endAutoTxtView.setAdapter(endAdapter);
    }

    @OnClick(R.id.calculate_button)
    public void calculate(final Button button) {
        String startPoint = startAutoTxtView.getText().toString();
        String endPoint = endAutoTxtView.getText().toString();

        calculateDistance(startPoint, endPoint, getRadioGroupChoice());
        saveAdressesInHistory(startPoint, endPoint);
    }

    private void calculateDistance(final String pointA, final String pointB, final String transportationMode) {
        progressBar.setVisibility(View.VISIBLE);

        DistanceService service = new DistanceService();
        service.request(pointA, pointB, transportationMode).enqueue(new Callback<DistanceMatrixResponse>() {
                @Override
                public void onResponse(final Call<DistanceMatrixResponse> call,
                        final Response<DistanceMatrixResponse> response) {
                    if (response.isSuccessful()) {
                        DistanceMatrixResponse dmResponse = response.body();
                        distanceTextView.setText(dmResponse.getDistance());
                    } else {
                        distanceTextView.setText(getString(R.string.error_from_query));
                    }

                    progressBar.setVisibility(View.GONE);
                    transportationTxtView.setText(getRadioGroupChoice());
                }

                @Override
                public void onFailure(final Call<DistanceMatrixResponse> call, final Throwable t) {
                    t.printStackTrace();
                    distanceTextView.setText(t.getMessage());
                    progressBar.setVisibility(View.GONE);
                }
            });

    }

    private void saveAdressesInHistory(final String startPoint, final String endPoint) {
        modifyAutoCompleteTextViewAdapter(startPoint, startAddressSet, startAdapter);
        modifyAutoCompleteTextViewAdapter(endPoint, endAddressSet, endAdapter);
    }

    // put unique addresses in the autoCompleTextView
    // maybe the AutoCompleTextView handles this
    private void modifyAutoCompleteTextViewAdapter(final String point, final Set<String> addressSet,
            final ArrayAdapter<String> adapter) {
        if (!addressSet.contains(point)) {
            addressSet.add(point);
            adapter.add(point);
            adapter.notifyDataSetChanged();
        }
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
