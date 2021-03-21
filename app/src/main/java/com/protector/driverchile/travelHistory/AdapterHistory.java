package com.protector.driverchile.travelHistory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.protector.driverchile.R;
import com.protector.driverchile.utils.Convert;
import com.protector.driverchile.utils.DataModelJson.TourModel;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolderData> {

    private TravelHistoryEventView listener;
    private List<TourModel> items;

    public AdapterHistory( List<TourModel> items ,TravelHistoryEventView listener) {
        this.items= items;
        this.listener= listener;
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView textViewNamePassaenger,textViewNumberPassaenger,textViewTime,textViewOrigin,
        textViewDestination,textViewCharges,textViewStatus;
        Button buttonAction,buttonCancel;
        //LinearLayout linearLayout;
        LottieAnimationView lottieAnimationView;

        public ViewHolderData(View itemView) {
            super(itemView);
            textViewNamePassaenger = itemView.findViewById(R.id.txv_name_passaenger);
            textViewNumberPassaenger = itemView.findViewById(R.id.txv_number_passaenger);
            textViewTime = itemView.findViewById(R.id.txv_time);
            textViewOrigin = itemView.findViewById(R.id.txv_origin);
            textViewDestination = itemView.findViewById(R.id.txv_destination);
            textViewCharges = itemView.findViewById(R.id.txv_charges);
            textViewStatus = itemView.findViewById(R.id.txv_status_travel);
            buttonAction = itemView.findViewById(R.id.btn_action);
            buttonCancel= itemView.findViewById(R.id.btn_cancel);
            //linearLayout= itemView.findViewById(R.id.linearLayout2);
            lottieAnimationView = itemView.findViewById(R.id.lottie);
        }
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_history,parent,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        if (items.get(position).getFirstName()!=null){
            holder.textViewNamePassaenger.setText(items.get(position).getFirstName());
            if (items.get(position).getLastName()!=null){
                holder.textViewNamePassaenger.setText(items.get(position).getFirstName()+" "+items.get(position).getLastName());
            }
        }


        holder.textViewTime.setText(Convert.epochToDate(items.get(position).getDateTime()));
        holder.textViewOrigin.setText(items.get(position).getSourceAddress());
        holder.textViewDestination.setText(items.get(position).getDestinationAddress());

        if (items.get(position).getDistributionAmount()!=null &&
                items.get(position).getExchangeRate()!=null ){

            Double charges= items.get(position).getDistributionAmount()*items.get(position).getExchangeRate();
            holder.textViewCharges.setText("$"+Math.round(charges));

        }

        holder.textViewStatus.setText(items.get(position).getStatus());

        if (items.get(position).getStatus().equals("assigned")
                ||items.get(position).getStatus().equals("accepted")
                ||items.get(position).getStatus().equals("arrived & waiting")){

            holder.buttonAction.setVisibility(View.GONE);
            holder.lottieAnimationView.setVisibility(View.VISIBLE);

        }else if (items.get(position).getStatus().equals("cancelled by passenger")
                ||items.get(position).getStatus().equals("cancelled by driver")
                ||items.get(position).getStatus().equals("expired")){

            holder.buttonAction.setVisibility(View.GONE);
            holder.buttonCancel.setVisibility(View.GONE);
            //holder.linearLayout.setVisibility(View.GONE);
            holder.lottieAnimationView.setVisibility(View.GONE);

        }else {
            holder.buttonCancel.setVisibility(View.GONE);
            holder.lottieAnimationView.setVisibility(View.GONE);
        }

        holder.buttonAction.setOnClickListener((view)->{
            listener.showDetailTravel(items.get(position).getTourId());
        });

        holder.buttonCancel.setOnClickListener((view)->{
            listener.showCancelTravel(items.get(position).getTourId());
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
