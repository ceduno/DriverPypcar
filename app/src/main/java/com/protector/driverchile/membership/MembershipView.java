package com.protector.driverchile.membership;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.protector.driverchile.ApplicationMaster;
import com.protector.driverchile.R;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.notification.AdapterNotifiaction;
import com.protector.driverchile.utils.Convert;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MembershipModel;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.DataModelJson.ToursDoneModel;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.ProgresDialogCustom;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import static io.fabric.sdk.android.Fabric.TAG;


public class MembershipView extends Fragment implements MembershipEventView {
    private View v;
    private DriverPojo driverPojo;
    private WebView webviewMembership;
    private RecyclerView recyclerView;
    private TextView txvMessageList,txvTitleToursdone;
    private ConstraintLayout layouttitle,layoutNoMembership,layoutExpCredit,layoutCreditRate;
    private ConstraintLayout linearLayoutLoad;
    private RelativeLayout dataMembership,layoutList;
   /* private MutableLiveData<String> remainingTrips,membershipValidTo,remainingDays,payable,membershipTypeName,
            operationStatus,paymentCondition,planAmount,membershipStartedDate,membershipEndedDate,creditExpirationDays,creditRate,limitMembershipTypeTrips;
*/    private List<ToursDoneModel> toursDoneModelList;
    private ImageView imgNoMembership;
    private TextView txvNoMembership, txvStatus,remainingTrips,membershipValidTo,remainingDays,payable,membershipTypeName,
            operationStatus,paymentCondition,planAmount,membershipStartedDate,membershipEndedDate,creditExpirationDays,creditRate,limitMembershipTypeTrips;
    //private Button buttonRefeir;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //refeirCode= new MutableLiveData<>();


        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_membership, null, false);
        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewmodel,this);

        v= binding.getRoot();
        addView();
        membershipRequest();

       // getMembershipUrl();

        return v;
    }

    @SuppressLint("WrongConstant")
    private void addView(){
        remainingTrips= v.findViewById(R.id.remainingTrips);
        //membershipValidTo= v.findViewById(R.id.membershipValidTo);
        remainingDays= v.findViewById(R.id.remainingDays);
        payable= v.findViewById(R.id.payable);
        membershipTypeName= v.findViewById(R.id.membershipTypeName);
        // operationStatus= v.findViewById(R.id.operationStatus);
        paymentCondition= v.findViewById(R.id.paymentCondition);
        planAmount= v.findViewById(R.id.planAmount);
        membershipStartedDate= v.findViewById(R.id.membershipStartedDate);
        membershipEndedDate= v.findViewById(R.id.membershipEndedDate);
        creditExpirationDays= v.findViewById(R.id.creditExpirationDays);
        limitMembershipTypeTrips= v.findViewById(R.id.limitMembershipTypeTrips);
        creditRate= v.findViewById(R.id.creditRate);
        txvMessageList= v.findViewById(R.id.txv_message_list);
        txvMessageList.setVisibility(View.GONE);
        recyclerView= v.findViewById(R.id.recy_toursDone);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(false);
        linearLayoutLoad= v.findViewById(R.id.vw_load_membership);
        layouttitle= v.findViewById(R.id.layoutMembershipStatus);
        dataMembership= v.findViewById(R.id.dataMembership);
        txvTitleToursdone= v.findViewById(R.id.txv_title_toursdone);
        //layoutList= v.findViewById(R.id.layout_list);
        layoutNoMembership= v.findViewById(R.id.layout_noMembership);
        imgNoMembership= v.findViewById(R.id.img_no_memership);
        txvNoMembership= v.findViewById(R.id.no_membership_text);
        txvNoMembership.setVisibility(View.GONE);
        imgNoMembership.setVisibility(View.GONE);

        layoutExpCredit= v.findViewById(R.id.layoutExpCredit);
        layoutCreditRate= v.findViewById(R.id.layoutCreditRate);


        txvStatus= v.findViewById(R.id.txv_status);
        //,
        //webviewMembership= v.findViewById(R.id.wv_membership);
        //buttonRefeir= v.findViewById(R.id.btn_refeir);
    }

    private void setValues(String Url) {
       // webviewMembership.getSettings().setJavaScriptEnabled(true);
        //webviewMembership.setWebViewClient(new MyWebViewClient(getActivity()));
        //String urlWebView = getIntent().getStringExtra("url_webView");

        //webviewMembership.loadUrl(Url);
        /*driverPojo= SharedPreferenceManager.getUser(getContext());
        if (driverPojo.getCodeToRefer()==null){
            ToastCustom.show(2,getContext(),getActivity().getString(R.string.fail_refeir_code));
            //buttonRefeir.setVisibility(View.GONE);
        }else {
           // refeirCode.setValue(driverPojo.getCodeToRefer());
        }*/
    }
    public void showLoad(boolean ban) {
        try {
            if (ban){
                linearLayoutLoad.setVisibility(View.VISIBLE);
            }else {
                linearLayoutLoad.setVisibility(View.GONE);
            }
        }catch (Exception e){
            Log.e(TAG,"Vista CERRADA");
        }
    }
    private void showNoMembership(){
        txvNoMembership.setVisibility(View.VISIBLE);
        imgNoMembership.setVisibility(View.VISIBLE);
        layoutNoMembership.setVisibility(View.VISIBLE);
        layouttitle.setVisibility(View.GONE);
        txvTitleToursdone.setVisibility(View.GONE);
        dataMembership.setVisibility(View.GONE);
       // layoutList.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

    }
    private void showMembership(){
        txvNoMembership.setVisibility(View.GONE);
        imgNoMembership.setVisibility(View.GONE);
        layoutNoMembership.setVisibility(View.GONE);
        layouttitle.setVisibility(View.VISIBLE);
        txvTitleToursdone.setVisibility(View.VISIBLE);
        dataMembership.setVisibility(View.VISIBLE);
        //.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
    private void showMessageNoTours(){
        txvMessageList.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
    private void showListTours(){
        txvMessageList.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showList(){
        Log.d("CALL LIST TOURS","------------------------------ CALLING");
        if(  !toursDoneModelList.isEmpty() || toursDoneModelList.size()!=0 ){
            Log.d("CALL LIST TOURS","------------------------------ CALLING");

            AdapterMembership adapter= new AdapterMembership(toursDoneModelList);
            recyclerView.setAdapter(adapter);

            LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation
                    (recyclerView.getContext(),R.anim.animlt_up);
            recyclerView.setLayoutAnimation(controller);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
            showListTours();
        }else{
            showMessageNoTours();
        }

    }

    @SuppressLint("LongLogTag")
    public void membershipRequest() {
        showLoad(true);
        Log.d("CALL MEMBERSHIP URL","------------------------------ CALLING");

        if (Validation.isNetDisponible(getContext())){
            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri().create(User.class)
                    .getMembershipInfo(driverPojo.getApiSessionKey(),"es",true).enqueue(new Callback<MembershipModel>() {
                @Override
                public void onResponse(Call<MembershipModel> call, Response<MembershipModel> response) {
                    showLoad(false);
                    //Log.d("MEMBERSHIP URL","------------------------------ "+response.body().getMembershipValidTo());
                    if (response.code() >= 400 && response.code() < 500) {
                        showNoMembership();
                        if (response.code() == 401) {
                            Log.d("MEMBERSHIP ERROR 401","------------------------------ CALL");
                            LogOut.Do(getActivity(), true);
                        } else {
                            Log.d("MEMBERSHIP ERROR 400 O 500","------------------------------ CODE "+response.code());
                            ToastCustom.show(0,
                                    getContext(),
                                    getContext().getString(R.string.something_has_failed));
                        }

                    } else {
                        //try {

                            if (response.code()==200) {
                                Log.d("MEMBERSHIP URL","------------------------------ "+response.body().toString());
                                membershipTypeName.setText(response.body().getMembershipTypeName());
                                //operationStatus.setValue(response.body().getOperationStatus());
                                if(response.body().getOperationStatus().equals("activated")){
                                    txvStatus.setText("A");
                                    txvStatus.setBackgroundResource(R.drawable.dw_circle_green);
                                }
                                else{
                                    txvStatus.setText("S");
                                    txvStatus.setBackgroundResource(R.drawable.dw_circle_red);
                                }
                                limitMembershipTypeTrips.setText(response.body().getLimitMembershipTypeTrips());
                                remainingTrips.setText(response.body().getRemainingTrips());
                                //membershipValidTo.setText(response.body().getMembershipEndedDate());
                                remainingDays.setText(response.body().getRemainingDays());
                                payable.setText(response.body().getPayable());
                                Log.d("MEMBERSHIP TOURS DONE","-------------- "+response.body().getToursDone());
                                toursDoneModelList= response.body().getToursDone();
                                paymentCondition.setText(response.body().getPaymentCondition());
                                planAmount.setText(response.body().getPlanAmount());
                               // SimpleDateFormat format=new SimpleDateFormat("yyyy:mm:dd hh:mm");

                               // String dateStart = format.format(response.body().getMembershipStartedDate() );
                                membershipStartedDate.setText(response.body().getMembershipStartedDate());
                               // String dateEnd = format.format(response.body().getMembershipEndedDate() );
                                membershipEndedDate.setText(response.body().getMembershipEndedDate());

                                if(response.body().getPaymentCondition().equals("Credit")){
                                    layoutExpCredit.setVisibility(View.VISIBLE);
                                    layoutCreditRate.setVisibility(View.VISIBLE);
                                }else{
                                    layoutExpCredit.setVisibility(View.GONE);
                                    layoutCreditRate.setVisibility(View.GONE);
                                }
                                creditExpirationDays.setText(response.body().getCreditExpirationDays());
                                creditRate.setText(response.body().getCreditRate());
                                Log.d("MEMBERSHIP SHOW","-------------- ");
                                showMembership();
                                Log.d("MEMBERSHIP LIST","-------------- ");
                                if(response.body().getToursDone()!=null){
                                    showList();
                                }else{
                                    showMessageNoTours();
                                }

                            } else {
                                showNoMembership();
                                ToastCustom.show(0,
                                        getContext(),
                                        getContext().getString(R.string.something_has_failed));
                            }

                        /*} catch (Exception e) {
                            showNoMembership();
                            Log.d("MEMBERSHIP ERROR CATCH","------------------------------ "+e);
                            ToastCustom.show(0,
                                    getContext(),
                                    getContext().getString(R.string.something_has_failed)+""+e);
                        }*/
                    }


                }

                @Override
                public void onFailure(Call<MembershipModel> call, Throwable t) {
                    //statusButtonReSend(true);.
                    showLoad(false);
                    showNoMembership();
                    Log.e(TAG+" MEMBERSHIP apiOnFailure",t.toString());
                    ToastCustom.show(0
                            ,getContext()
                            ,ApplicationMaster.getAppContext().getString(R.string.something_has_failed));
                }
            });

        }else {
            showLoad(false);
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }



    private class MyWebViewClient extends WebViewClient {
        private final Activity activity;
        private ProgressDialog progressDialog;// TODO: ProgresDialog change to ProgressBar

        private MyWebViewClient(Activity activity) {
            this.activity = activity;
            Log.d("", "---------------------Constructor WEBCLIENT");
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
            Log.d("", "---------------------recievedSSLerror");
            handler.proceed();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.i("Listener", "Start");
            Log.d("", String.format("Loading %s", url));
            if (progressDialog == null) {
                // in standard case YourActivity.this
                progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Cargando");
                progressDialog.show();
                Log.d("", String.format("Showing for %s", url));
            }
        }

        public void onPageFinished(WebView view, String url) {
            //view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
            super.onPageFinished(view, url);
            Log.i("Listener", "Finish");
            showFinalResults();
            Log.d("", "------------------------------------FIN DE CARGA");

        }

        private void showFinalResults() {
            try {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            } catch (Exception exception) {
                //RemoteLogger.e(LOG_TAG,getString(R.string.errorLoadingPage));
            }
        }
        // @Override public boolean shouldOverrideUrlLoading(WebView view, String url) { return false; }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            // do your handling codes here, which url is the requested url
            // probably you need to open that url rather than redirect:
            Log.d("DATA OverrideUrlLoading"," URL "+url);
            view.loadUrl(url);
            return true; // then it is not handled by default action
        }


    }

    @SuppressLint("LongLogTag")
    public void getMembershipUrl(){

        if (Validation.isNetDisponible(getActivity())){

            ProgresDialogCustom progresDialogCustom= new ProgresDialogCustom(getString(R.string.performing_operation));
            progresDialogCustom.show(getActivity().getSupportFragmentManager(),"DIALOG_PROGRESS");

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());
            HttpConexion.getUri()
                    .create(User.class)
                    .membership(driverPojo.getApiSessionKey(),"es")
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            progresDialogCustom.dismiss();

                            if (response.code() >= 400 && response.code() < 500) {
                                if (response.code() == 401) {
                                    LogOut.Do(getActivity(), true);
                                } else {
                                    ToastCustom.show(0,
                                            getContext(),
                                            getContext().getString(R.string.something_has_failed));
                                }

                            } else {
                                try {

                                    if (response.body().getType().equals("SUCCESS")) {
                                        Log.d("TAG"+"respuesta MEMBERSHIP BODY",response.body().makeJson());
                                        ToastCustom.show(0,
                                                getContext(),
                                                "respuesta MEMBERSHIP BODY "+response.body().makeJson());
                                        //setValues();
                                    } else {
                                        ToastCustom.show(0,
                                                getContext(),
                                                getContext().getString(R.string.something_has_failed));
                                    }

                                } catch (Exception e) {
                                    Log.e(TAG + " changeStatusNotification Exception", e.toString());
                                    ToastCustom.show(0,
                                            getContext(),
                                            getContext().getString(R.string.something_has_failed));
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            progresDialogCustom.dismiss();

                            Log.e(TAG+" changeStatusNotification OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext(),
                                    getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            ToastCustom.show(3,getActivity(),
                    getActivity().getString(R.string.without_internet));
        }

    }

/*
    public MutableLiveData<String> getCreditRate() {
    return creditRate;
}

    public void setCreditRate(MutableLiveData<String> creditRate) {
        this.creditRate = creditRate;
    }

    public MutableLiveData<String> getCreditExpirationDays() {
    return creditExpirationDays;
}

    public void setCreditExpirationDays(MutableLiveData<String> creditExpirationDays) {
        this.creditExpirationDays = creditExpirationDays;
    }

    public MutableLiveData<String> getMembershipEndedDate() {
    return membershipEndedDate;
}

    public void setMembershipEndedDate(MutableLiveData<String> membershipEndedDate) {
        this.membershipEndedDate = membershipEndedDate;
    }

    public MutableLiveData<String> getMembershipStartedDate() {
    return membershipStartedDate;
}

    public void setMembershipStartedDate(MutableLiveData<String> membershipStartedDate) {
        this.membershipStartedDate = membershipStartedDate;
    }

    public MutableLiveData<String> getPlanAmount() {
    return planAmount;
}

    public void setPlanAmount(MutableLiveData<String> planAmount) {
        this.planAmount = planAmount;
    }

    public MutableLiveData<String> getPaymentCondition() {
    return paymentCondition;
}

    public void setPaymentCondition(MutableLiveData<String> paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public MutableLiveData<String> getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(MutableLiveData<String> operationStatus) {
        this.operationStatus = operationStatus;
    }

    public MutableLiveData<String> getMembershipTypeName() {
        return membershipTypeName;
    }

    public void setMembershipTypeName(MutableLiveData<String> membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }

    public MutableLiveData<String> getRemainingTrips() {
        return remainingTrips;
    }

    public void setRemainingTrips(MutableLiveData<String> remainingTrips) {
        this.remainingTrips = remainingTrips;
    }

    public MutableLiveData<String> getMembershipValidTo() {
        return membershipValidTo;
    }

    public void setMembershipValidTo(MutableLiveData<String> membershipValidTo) {
        this.membershipValidTo = membershipValidTo;
    }

    public MutableLiveData<String> getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(MutableLiveData<String> remainingDays) {
        this.remainingDays = remainingDays;
    }

    public MutableLiveData<String> getPayable() {
        return payable;
    }

    public void setPayable(MutableLiveData<String> payable) {
        this.payable = payable;
    }

    public MutableLiveData<String> getLimitMembershipTypeTrips() {
        return limitMembershipTypeTrips;
    }

    public void setLimitMembershipTypeTrips(MutableLiveData<String> limitMembershipTypeTrips) {
        this.limitMembershipTypeTrips = limitMembershipTypeTrips;
    }*/
}
