package com.gvtech.serviceathome.activities.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.adapters.ServiceItemAdapter;
import com.gvtech.serviceathome.data.LoadData;
import com.gvtech.serviceathome.fragments.SearchProfessionalFragment;

public class SearchProfessionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_professional);

        replaceFragment(new SearchProfessionalFragment(), false);

//        RecyclerView recyclerView = findViewById(R.id.recycler_search_list);

//        ServiceItemAdapter serviceAdapter = new ServiceItemAdapter(getApplicationContext(),LoadData.loadServiseItemData());
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(serviceAdapter);
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
