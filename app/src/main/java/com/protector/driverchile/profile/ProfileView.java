package com.protector.driverchile.profile;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.protector.driverchile.R;
import com.protector.driverchile.databinding.FragmentProfileBinding;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.SharedPreferenceManager;

public class ProfileView extends Fragment {
    private View v;
    private DriverPojo driverPojo;
    private ImageView imageViewPhoto;
    private MutableLiveData<String> fullName,idUser,phone,email,addres,bankName,bankAcountNo
            ,bankTipe,carMake,carModel,carYear,carPlate,carColor;
    private ConstraintLayout constraintLayoutSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fullName= new MutableLiveData<>();
        idUser= new MutableLiveData<>();
        phone= new MutableLiveData<>();
        email= new MutableLiveData<>();
        addres= new MutableLiveData<>();
        bankName= new MutableLiveData<>();
        bankAcountNo= new MutableLiveData<>();
        bankTipe= new MutableLiveData<>();
        carMake= new MutableLiveData<>();
        carModel= new MutableLiveData<>();
        carYear= new MutableLiveData<>();
        carPlate= new MutableLiveData<>();
        carColor= new MutableLiveData<>();

        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_profile, null, false);
        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewmodel,this);

        v= binding.getRoot();
        addView();
        setValues();
        return v;
    }

    //region SETER - GETER

    public DriverPojo getDriverPojo() {
        return driverPojo;
    }

    public void setDriverPojo(DriverPojo driverPojo) {
        this.driverPojo = driverPojo;
    }

    public ImageView getImageViewPhoto() {
        return imageViewPhoto;
    }

    public void setImageViewPhoto(ImageView imageViewPhoto) {
        this.imageViewPhoto = imageViewPhoto;
    }

    public MutableLiveData<String> getFullName() {
        return fullName;
    }

    public void setFullName(MutableLiveData<String> fullName) {
        this.fullName = fullName;
    }

    public MutableLiveData<String> getIdUser() {
        return idUser;
    }

    public void setIdUser(MutableLiveData<String> idUser) {
        this.idUser = idUser;
    }

    public MutableLiveData<String> getPhone() {
        return phone;
    }

    public void setPhone(MutableLiveData<String> phone) {
        this.phone = phone;
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(MutableLiveData<String> email) {
        this.email = email;
    }

    public MutableLiveData<String> getAddres() {
        return addres;
    }

    public void setAddres(MutableLiveData<String> addres) {
        this.addres = addres;
    }

    public MutableLiveData<String> getBankName() {
        return bankName;
    }

    public void setBankName(MutableLiveData<String> bankName) {
        this.bankName = bankName;
    }

    public MutableLiveData<String> getBankAcountNo() {
        return bankAcountNo;
    }

    public void setBankAcountNo(MutableLiveData<String> bankAcountNo) {
        this.bankAcountNo = bankAcountNo;
    }

    public MutableLiveData<String> getBankTipe() {
        return bankTipe;
    }

    public void setBankTipe(MutableLiveData<String> bankTipe) {
        this.bankTipe = bankTipe;
    }

    public MutableLiveData<String> getCarMake() {
        return carMake;
    }

    public void setCarMake(MutableLiveData<String> carMake) {
        this.carMake = carMake;
    }

    public MutableLiveData<String> getCarModel() {
        return carModel;
    }

    public void setCarModel(MutableLiveData<String> carModel) {
        this.carModel = carModel;
    }

    public MutableLiveData<String> getCarYear() {
        return carYear;
    }

    public void setCarYear(MutableLiveData<String> carYear) {
        this.carYear = carYear;
    }

    public MutableLiveData<String> getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(MutableLiveData<String> carPlate) {
        this.carPlate = carPlate;
    }

    public MutableLiveData<String> getCarColor() {
        return carColor;
    }

    public void setCarColor(MutableLiveData<String> carColor) {
        this.carColor = carColor;
    }

    //region

    private void addView() {
        driverPojo= SharedPreferenceManager.getUser(getContext());
        imageViewPhoto= v.findViewById(R.id.img_profil_photo);

        constraintLayoutSetting= v.findViewById(R.id.constraintLayout_setting);
        LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation
                (constraintLayoutSetting.getContext(),R.anim.animlt_up);
        constraintLayoutSetting.setLayoutAnimation(controller);
        constraintLayoutSetting.scheduleLayoutAnimation();
    }

    private void setValues() {
        if (driverPojo.getPhotoUrl()!=null){
            Glide.with(this)
                    .load(driverPojo.getPhotoUrl())
                    .circleCrop()
                    .placeholder(R.drawable.ic_person)
                    .into(imageViewPhoto);
        }

        if (driverPojo.getFullName()!=null){
            fullName.setValue(driverPojo.getFullName());
        }else {
            fullName.setValue("--");
        }

        if (driverPojo.getUserId()!=null){
            idUser.setValue(driverPojo.getUserId());
        }else {
            idUser.setValue("--");
        }

        if (driverPojo.getPhoneNoCode()!=null && driverPojo.getPhoneNo()!=null){
            phone.setValue(driverPojo.getPhoneNoCode()+" "+driverPojo.getPhoneNo());
        }else {
           phone.setValue("--");
        }

        if (driverPojo.getEmail()!=null){
            email.setValue(driverPojo.getEmail());
        }else {
            email.setValue("--");
        }

        if (driverPojo.getMailCountryId()!=null){
            addres.setValue(driverPojo.getMailCountryId());
            if (driverPojo.getMailCityId()!=null){
                addres.setValue(driverPojo.getMailCountryId()+" - "+driverPojo.getMailCityId());
            }
        }else {
            addres.setValue("--");
        }

        if (driverPojo.getDriverBankDetails().getBankName()!=null){
            bankName.setValue(driverPojo.getDriverBankDetails().getBankName());
        }else {
            bankName.setValue("--");
        }

        if (driverPojo.getDriverBankDetails().getAccountNumber()!=null){
            bankAcountNo.setValue(driverPojo.getDriverBankDetails().getAccountNumber());
        }else {
            bankAcountNo.setValue("--");
        }

        if (driverPojo.getDriverBankDetails().getType()!=null){
            bankTipe.setValue(driverPojo.getDriverBankDetails().getType());
        }else {
            bankTipe.setValue("--");
        }

        if (driverPojo.getCarModel().getMake()!=null){
            carMake.setValue(driverPojo.getCarModel().getMake());
        }else {
            carMake.setValue("--");
        }

        if (driverPojo.getCarModel().getModelName()!=null){
            carModel.setValue(driverPojo.getCarModel().getModelName());
        }else {
            carModel.setValue("--");
        }

        if (driverPojo.getCarModel().getCarYear()!=null){
            carYear.setValue(String.valueOf(driverPojo.getCarModel().getCarYear()));
        }else {
            carYear.setValue("--");
        }

        if (driverPojo.getCarModel().getCarPlateNo()!=null){
            carPlate.setValue(driverPojo.getCarModel().getCarPlateNo());
        }else {
            carPlate.setValue("--");
        }

        if (driverPojo.getCarModel().getCarColor()!=null){
            carColor.setValue(driverPojo.getCarModel().getCarColor());
        }else {
            carColor.setValue("--");
        }

    }
}
