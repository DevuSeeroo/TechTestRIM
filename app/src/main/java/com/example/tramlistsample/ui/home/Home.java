package com.example.tramlistsample.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tramlistsample.R;
import com.example.tramlistsample.data.network.Responses.StopInfo;
import com.example.tramlistsample.data.network.Responses.Tram;
import com.example.tramlistsample.ui.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends BaseActivity {
    private ProgressBar loaderView;

    private TextView tvStopName, tvTitle, tvInboundTitle, tvOutboundTitle;
    private RecyclerView rvOutboundList, rvInboundList;
    private FloatingActionButton fabRefresh;
    private List<Tram> inboundTramList = new ArrayList<>();
    private List<Tram> outboundTramList = new ArrayList<>();
    private HomeViewModel homeViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeViews();
        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoaderVisibility(true);
                getTramList();
            }
        });
        initializeData();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initializeData() {
        setLoaderVisibility(true);
        getTramList();

    }

    private void initializeViews() {
        tvStopName = findViewById(R.id.tv_stop_name);
        tvTitle = findViewById(R.id.tv_title);
        tvInboundTitle = findViewById(R.id.inbound_txt);
        tvOutboundTitle = findViewById(R.id.outbound_txt);
        rvOutboundList = findViewById(R.id.rv_outbound_list);
        rvInboundList = findViewById(R.id.rv_inbound_list);
        fabRefresh = findViewById(R.id.fab_refresh);
        loaderView = findViewById(R.id.loader);
        homeViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(HomeViewModel.class);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getTramList() {
if(isNetworkAvailable()){
    homeViewModel.getTramList().observe(this, new Observer<StopInfo>() {
        @Override
        public void onChanged(StopInfo infoModel) {
            if(homeViewModel.whetherSession1()){
                tvTitle.setText("Marlborough Stop");
            }
            else
            {
                tvTitle.setText("Stillorgan Stop");

            }
            if (infoModel != null) {
                setLoaderVisibility(false);
                tvStopName.setText(infoModel.message);
                inboundTramList = infoModel.direction.get(0).tram;
                outboundTramList = infoModel.direction.get(1).tram;
                setOutboundHomeAdapter();
                setInboundHomeAdapter();
            } else {
                setLoaderVisibility(false);
                tvStopName.setText(R.string.api_failed);
            }
        }
    });
}
else{
    Toast.makeText(this, "Please connect to internet", Toast.LENGTH_SHORT).show();
}
        
    }

    private void setLoaderVisibility(boolean isLoaderVisible) {
        if (isLoaderVisible) {
            loaderView.setVisibility(View.VISIBLE);
            tvStopName.setVisibility(View.GONE);
            tvTitle.setVisibility(View.GONE);
            tvInboundTitle.setVisibility(View.GONE);
            tvOutboundTitle.setVisibility(View.GONE);
            rvInboundList.setVisibility(View.GONE);
            rvOutboundList.setVisibility(View.GONE);
            fabRefresh.setVisibility(View.GONE);
        } else {
            loaderView.setVisibility(View.GONE);
            tvTitle.setVisibility(View.VISIBLE);
            tvStopName.setVisibility(View.VISIBLE);
            tvInboundTitle.setVisibility(View.VISIBLE);
            tvOutboundTitle.setVisibility(View.VISIBLE);
            rvInboundList.setVisibility(View.VISIBLE);
            rvOutboundList.setVisibility(View.VISIBLE);
            fabRefresh.setVisibility(View.VISIBLE);
        }
    }

    private void setOutboundHomeAdapter() {
        HomeAdapter outboundHomeAdapter = new HomeAdapter((ArrayList<Tram>) outboundTramList, getApplicationContext());
        rvOutboundList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvOutboundList.setAdapter(outboundHomeAdapter);
    }

    private void setInboundHomeAdapter() {
        HomeAdapter inboundHomeAdapter = new HomeAdapter((ArrayList<Tram>) inboundTramList, getApplicationContext());
        rvInboundList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvInboundList.setAdapter(inboundHomeAdapter);
    }
}
