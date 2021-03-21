package com.protector.driverchile.notification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.protector.driverchile.R;
import com.protector.driverchile.utils.Convert;
import com.protector.driverchile.utils.DataModelJson.NotificationMap;


import java.util.List;

/**
 *
 */
public class AdapterNotifiaction  extends RecyclerView.Adapter<AdapterNotifiaction.ViewHolderData> {

    private List<NotificationMap> items;

    public AdapterNotifiaction(List<NotificationMap> items) {
        this.items= items;
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView textViewMessage,textViewTime;
        ImageView iv_notify_type;
        public ViewHolderData(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.txv_message);
            textViewTime = itemView.findViewById(R.id.txv_time_message);
            iv_notify_type = itemView.findViewById(R.id.iv_notify_type);

        }
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_notification,parent,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.textViewMessage.setText(items.get(position).getMessage());
        holder.textViewTime.setText(Convert.epochToDate(items.get(position).getCreatedAt()+""));
        if(items.get(position).getMessageType()!=null){
            if(items.get(position).getMessageType().equalsIgnoreCase("canceledTrip") ||
                    items.get(position).getMessageType().equalsIgnoreCase("tripCanceled") ){
                holder.iv_notify_type.setImageResource(R.drawable.ic_iconos_notificaciones_04);
            }else  if(items.get(position).getMessageType().equalsIgnoreCase("notification")){
                holder.iv_notify_type.setImageResource(R.drawable.ic_iconos_notificaciones_05);
            }else {
                holder.iv_notify_type.setImageResource(R.drawable.ic_iconos_notificaciones_03);
            }
        }
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
