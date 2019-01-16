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

public class AddServiceDialog extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btnSubmit, no;

    public AddServiceDialog(Activity a) {
        super(a, R.style.Theme_Dialog);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_service_dialog);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        no = (Button) findViewById(R.id.btn_no);
        btnSubmit.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                ((AddServiceActivity)this.c).replaceFragment(new ProfessionalServiceFragment(),false);
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}