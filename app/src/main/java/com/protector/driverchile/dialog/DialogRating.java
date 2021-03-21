package com.protector.driverchile.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;
import com.protector.driverchile.R;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.DataModelJson.RatingModel;
import com.protector.driverchile.utils.DataModelJson.TravelInfoModel;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***/
public class DialogRating  extends DialogFragment {
    private String TAG= "DialogRating ";
    private View v;
    private Button buttonRating;
    private TravelInfoModel travelInfoModel;
    private RatingBar ratingBar;
    private EditText editTextComent;

    public DialogRating(TravelInfoModel travelInfoModel) {
        this.travelInfoModel= travelInfoModel;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.dialog_rating, null, false);

        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewModel,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView() {
        buttonRating= v.findViewById(R.id.btn_accept);
        ratingBar= v.findViewById(R.id.ratingbar);
        editTextComent= v.findViewById(R.id.edit_coment);
    }

    public void rating(){
        if (editTextComent.getText().toString().trim().isEmpty() && ratingBar.getRating()==0){
            ToastCustom.show(0,getActivity(),getString(R.string.rate_message_no_data));
        }else {
            if (Validation.isNetDisponible(getContext())){
                statusAccept(false);

                DriverPojo driverPojo= SharedPreferenceManager.getUser(getActivity());
                RatingModel ratingModel= new RatingModel();
                ratingModel.setDriverId(travelInfoModel.getDriverId());
                ratingModel.setPassengerId(travelInfoModel.getPassengerId());
                ratingModel.setTripId(travelInfoModel.getTourId());
                ratingModel.setNote(editTextComent.getText().toString());
                ratingModel.setRate(ratingBar.getRating());

                HttpConexion.getUri()
                        .create(User.class)
                        .ratingPassenger(driverPojo.getApiSessionKey(),"es",ratingModel)
                        .enqueue(new Callback<MessagePojo>() {
                            @Override
                            public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                                statusAccept(true);
                                if (response.code()>=400 && response.code()<500){
                                    if (response.code()==401){
                                        LogOut.Do(getActivity(),true);
                                    }else {
                                        dismiss();
                                        ToastCustom.show(0,
                                                getContext(),getContext().getString(R.string.expired_trip));
                                    }
                                }else {
                                    try {
                                        ToastCustom.show(1,
                                                getContext(),getString(R.string.rate_successful));

                                        dismiss();
                                    }catch (Exception e){
                                        Log.e(TAG+" rating Exception",e.toString());
                                        ToastCustom.show(0,
                                                getContext(),getContext().getString(R.string.something_has_failed));
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<MessagePojo> call, Throwable t) {
                                statusAccept(true);
                                Log.e(TAG+" rating OnFailure",t.toString());
                                ToastCustom.show(0,
                                        getContext(),getContext().getString(R.string.something_has_failed));
                            }
                        });

            }else {
                ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
            }
        }
    }

    public void closeDialog(){
        dismiss();
    }

    private void statusAccept(boolean status){
        buttonRating.setEnabled(status);
        if (status){
            buttonRating.setText(R.string.qualify);
        }else {
            buttonRating.setText(R.string.qualifying);
        }
    }
}
