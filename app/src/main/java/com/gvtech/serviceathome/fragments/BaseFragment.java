package com.gvtech.serviceathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.models.Service;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }

    public void setItem(Service.ServiceItem serviceItem){
        onServiceChoose(serviceItem);
    }


    abstract void onServiceChoose(Service.ServiceItem serviceItem);
}
