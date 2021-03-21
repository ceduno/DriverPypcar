package com.protector.driverchile.registerDriver;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.protector.driverchile.R;
import com.protector.driverchile.databinding.FragmentRegisterDriverBinding;

/**

 */
public class RegisterView extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register_driver,container,false);
        RegisterViewModel viewModel= new RegisterViewModel();
        //binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }
}
