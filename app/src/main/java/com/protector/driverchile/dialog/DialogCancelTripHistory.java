package com.protector.driverchile.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;

import com.protector.driverchile.R;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.Travel;
import com.protector.driverchile.travelHistory.TravelHistoryEventView;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Marlon Viana on 27/05/2019
 * @email 92marlonViana@gmail.com
 */
public class DialogCancelTripHistory extends DialogFragment {
    private String TAG = "DIALOGCANCELTRIPHISTORY";
    private View v;
    private Button buttonNo,buttonOk;
    private String tourId;
    private TravelHistoryEventView travelHistoryEventView;


    public DialogCancelTripHistory(String tourId, TravelHistoryEventView travelHistoryEventView) {
        this.tourId= tourId;
        this.travelHistoryEventView= travelHistoryEventView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.dialog_cancel_trip, null, false);

        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewModel,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView(){
        buttonNo= v.findViewById(R.id.btn_no);
        buttonOk= v.findViewById(R.id.btn_ok);
    }

    public void doCancelTrip(){
        if (Validation.isNetDisponible(getContext())){
            statusButton(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .cancelTravel(driverPojo.getApiSessionKey(),"es",tourId)
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            statusButton(true);

                            if (response.code()>=400 && response.code()<500){
                                if (response.code()==401){
                                    LogOut.Do(getActivity(),true);
                                }else {
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.expired_trip));
                                }
                            }else {
                                try {

                                    ToastCustom.show(1,
                                            getContext(),response.body().getMessages().get(0));

                                    dismiss();
                                    travelHistoryEventView.reloadList();

                                }catch (Exception e){
                                    Log.e(TAG+" cancelTravel Exception",e.toString());
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.something_has_failed));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            statusButton(true);
                            Log.e(TAG+" cancelTravel OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }

    }

    public void close(){
        dismiss();
    }

    private void  statusButton(boolean ban){
        buttonOk.setEnabled(ban);
        if (ban){
            buttonOk.setText(R.string.cancel_trip);
            buttonNo.setVisibility(View.VISIBLE);
        }else {
            buttonOk.setText(R.string.canceling);
            buttonNo.setVisibility(View.GONE);
        }
    }
}
