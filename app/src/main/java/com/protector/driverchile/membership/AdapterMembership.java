package com.protector.driverchile.membership;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.protector.driverchile.R;
import com.protector.driverchile.utils.Convert;
import com.protector.driverchile.utils.DataModelJson.NotificationMap;
import com.protector.driverchile.utils.DataModelJson.ToursDoneMap;
import com.protector.driverchile.utils.DataModelJson.ToursDoneModel;

import java.util.List;

/**

 */
public class AdapterMembership extends RecyclerView.Adapter<AdapterMembership.ViewHolderData> {

    private List<ToursDoneModel> items;

    public AdapterMembership(List<ToursDoneModel> items) {
        this.items= items;
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView txvTotalPayFor,txvCreateDate,txvUserTourId,txvOperativeStatus,txvDriverAmount,txvDiscountRateByOwed,txvDiscountByAmountOwed,txvInvoicePaymentDate;

        public ViewHolderData(View itemView) {
            super(itemView);
            txvCreateDate = itemView.findViewById(R.id.txv_create_date);
            txvUserTourId = itemView.findViewById(R.id.txv_usertourid);
            txvOperativeStatus= itemView.findViewById(R.id.txv_operative_status);
            txvDriverAmount= itemView.findViewById(R.id.txv_driverAmount);
            txvDiscountRateByOwed = itemView.findViewById(R.id.txv_discountRateByOwed);
            txvDiscountByAmountOwed = itemView.findViewById(R.id.txv_discountByAmountOwed);
            txvInvoicePaymentDate = itemView.findViewById(R.id.txv_invoicePaymentDate);
            txvTotalPayFor= itemView.findViewById(R.id.txv_totalPayFor);
        }
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_tour_done,parent,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.txvUserTourId.setText(items.get(position).getTourNumber());
        holder.txvOperativeStatus.setText(items.get(position).getBillStatus());
        holder.txvCreateDate.setText(Convert.epochToDate(items.get(position).getTourDate()+""));
        holder.txvDriverAmount.setText(items.get(position).getDriverAmount()+"");
        holder.txvDiscountRateByOwed.setText(items.get(position).getDiscountRateByOwed()+"");
        holder.txvDiscountByAmountOwed.setText(items.get(position).getDiscountByAmountOwed()+"");
        holder.txvInvoicePaymentDate.setText(items.get(position).getInvoicePaymentDate()+"");
        holder.txvTotalPayFor.setText(items.get(position).getTotalPayFor()+"");
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
