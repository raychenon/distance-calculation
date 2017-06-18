package com.raychenon.distancecalculation.adapter;

import java.text.MessageFormat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.raychenon.distancecalculation.R;
import com.raychenon.distancecalculation.model.CalculationResultModel;

import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculationResultAdapter extends RecyclerView.Adapter<CalculationResultAdapter.ResultViewHolder> {

    private Context context;
    private List<CalculationResultModel> list;
    private Set<CalculationResultModel> uniqueResults;

    public CalculationResultAdapter(final Context context) {

        this.list = new LinkedList<CalculationResultModel>();
        this.uniqueResults = new HashSet<CalculationResultModel>();

        this.context = context;
    }

    public void appendModel(final CalculationResultModel model) {
        if (!uniqueResults.contains(model)) {
            uniqueResults.add(model);
            this.list.add(model);
        }
    }

    @Override
    public ResultViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        ResultViewHolder vh = new ResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.route_viewholder, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(final ResultViewHolder holder, final int position) {
        CalculationResultModel model = list.get(position);

        holder.startTextView.setText(MessageFormat.format(context.getString(R.string.vh_start_point), model.pointA));
        holder.endTextView.setText(MessageFormat.format(context.getString(R.string.vh_end_point), model.pointB));
        holder.modeTextView.setText(MessageFormat.format(context.getString(R.string.vh_mode),
                model.transportationMode));
        holder.distanceTextView.setText(MessageFormat.format(context.getString(R.string.vh_distance), model.distance));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vh_start_point)
        TextView startTextView;

        @BindView(R.id.vh_end_point)
        TextView endTextView;

        @BindView(R.id.vh_mode)
        TextView modeTextView;

        @BindView(R.id.vh_distance)
        TextView distanceTextView;

        public ResultViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
