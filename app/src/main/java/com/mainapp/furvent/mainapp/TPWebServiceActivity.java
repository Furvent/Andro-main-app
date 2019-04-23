package com.mainapp.furvent.mainapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mainapp.furvent.mainapp.adapter.CityAdapter;
import com.mainapp.furvent.mainapp.data.WebServiceUtils;
import com.mainapp.furvent.mainapp.data.city.CityBean;

import java.util.ArrayList;
import java.util.List;

public class TPWebServiceActivity extends AppCompatActivity {

    // Data
    private List<CityBean> cityList;

    private CityAdapter cityAdapter;

    private Button buttonGet;
    private EditText editText;
    private RecyclerView recycleViewTown;

    LoadingCityListAsyncTask loadingCityListAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpweb_service);
        initViewComponent();
        initButtonGetListener();
        initCityList();

    }

    private void initCityList() {
        cityList = new ArrayList<>();
        cityAdapter = new CityAdapter(cityList);
        recycleViewTown.setAdapter(cityAdapter);
        recycleViewTown.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initButtonGetListenerWithMoak() {
        buttonGet.setOnClickListener(v -> {
            CityBean cityTest = new CityBean();
            cityTest.setCityName("Puechabon");
            cityTest.setPostalCode("34150");
            cityList.add(0, cityTest);
            cityAdapter.notifyDataSetChanged();
        });
    }

    private void initButtonGetListener() {
        buttonGet.setOnClickListener(v -> {
            Toast.makeText(this, "Blaaablablahhh", Toast.LENGTH_SHORT).show();
            if (loadingCityListAsyncTask == null || loadingCityListAsyncTask.getStatus()
                    == AsyncTask.Status.FINISHED) {
                loadingCityListAsyncTask = new LoadingCityListAsyncTask();
                loadingCityListAsyncTask.execute();
            }
        });
    }

    private void initViewComponent() {
        buttonGet = findViewById(R.id.buttonGet);
        editText = findViewById(R.id.editText);
        recycleViewTown = findViewById(R.id.recycleViewTown);
    }

    public class LoadingCityListAsyncTask extends AsyncTask {
        private ArrayList<CityBean> cityBeanList;
        private Exception exception;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Log.w("TAG_ASYNC_TASK", "list loaded on app");
                cityBeanList = WebServiceUtils.loadCityListFromAPI(editText.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (exception != null) {
                Toast.makeText(TPWebServiceActivity.this, "ERROR: " + exception.getMessage(), Toast.LENGTH_LONG).show();
                Log.w("TAG_", "ERROR in post execute of LoadingCityListAsyncTask: " + exception.getMessage());
            } else {
                super.onPostExecute(o);
                cityList.clear();
                cityList.addAll(cityBeanList);
                cityAdapter.notifyDataSetChanged();
                // progressBarLoadEleve.setVisibility(View.GONE);
            }
        }
    }
}
