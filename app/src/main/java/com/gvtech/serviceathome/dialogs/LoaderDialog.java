package com.gvtech.serviceathome.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.professional.AddServiceActivity;
import com.gvtech.serviceathome.fragments.ProfessionalServiceFragment;

public class LoaderDialog extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btnSubmit, no;

    public LoaderDialog(Activity a) {
        super(a, R.style.Theme_loading_Dialog);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loader_dialog);

        this.setCancelable(false);
    }

    @Override
    public void onClick(View v) {

//        dismiss();
    }
}