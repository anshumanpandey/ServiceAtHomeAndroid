package com.gvtech.serviceathome.activities.professional;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.adapters.BookingHistoryAdapter;
import com.gvtech.serviceathome.adapters.ProfessionalServiceAdapter;
import com.gvtech.serviceathome.data.LoadData;
import com.gvtech.serviceathome.fragments.ProfessionalServiceFragment;

public class AddServiceActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        replaceFragment(new ProfessionalServiceFragment(), false);

    }


    public void replaceFragment(Fragment fragment, boolean stack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.container_id, fragment, fragment.toString());
        if (stack){
            fragmentTransaction.addToBackStack(fragment.toString());

        }else {
//            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
