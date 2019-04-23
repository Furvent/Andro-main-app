package com.mainapp.furvent.mainapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mainapp.furvent.mainapp.adapter.EleveAdapter;
import com.mainapp.furvent.mainapp.data.EleveBean;
import com.mainapp.furvent.mainapp.data.WebServiceUtils;
import com.mainapp.furvent.mainapp.interfaces.OnEleveClickListener;

import java.util.ArrayList;
import java.util.List;

public class TPElevesListActivity extends AppCompatActivity implements OnEleveClickListener {

    private List<EleveBean> eleveList;
    private EleveAdapter eleveAdapter;

    private Button btAddEleve;
    private Button btLoadEleve;

    LoadingEleveAsyncTask loadingEleveAsyncTask;

    private ProgressBar progressBarLoadEleve;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    private RecyclerView recycleViewEleveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpeleves_list);
        btAddEleve = findViewById(R.id.btAddEleve);
        btLoadEleve = findViewById(R.id.btnLoadEleve);
        progressBarLoadEleve = findViewById(R.id.progressBarLoadEleve);
        progressBarLoadEleve.setVisibility(View.GONE);
        recycleViewEleveList = findViewById(R.id.recycleViewEleveList);

        initListEleve();

        btAddEleve.setOnClickListener(v -> {
            EleveBean newEleve = new EleveBean("Name" + eleveList.size(), "FirstName" + eleveList.size());
            eleveList.add(0, newEleve); // Méthode de l'interface Java List, qui permet de rajouter à une certaine position un nouvel élément.
            eleveAdapter.notifyDataSetChanged();
        });

        btLoadEleve.setOnClickListener(v -> {
            if (loadingEleveAsyncTask == null || loadingEleveAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                loadingEleveAsyncTask = new LoadingEleveAsyncTask();
                loadingEleveAsyncTask.execute();
                progressBarLoadEleve.setVisibility(View.VISIBLE);
            }


        });
    }

    private void initListEleve() {
        eleveList = new ArrayList<>();
        eleveAdapter = new EleveAdapter(eleveList);
        eleveAdapter.setOnEleveClickListener(this);
        recycleViewEleveList.setAdapter(eleveAdapter);
        recycleViewEleveList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnEleveClick(EleveBean eleve) {
        Log.w("TAG_", "Callback simple click worked");
        final int indexEleveMoved = eleveList.indexOf(eleve);
        eleveList.remove(eleve);
        eleveList.add(0, eleve);
        //eleveAdapter.notifyDataSetChanged();
        eleveAdapter.notifyItemMoved(indexEleveMoved,0);
    }

    @Override
    public void OnEleveLongClick(EleveBean eleve) {
        Log.w("TAG_", "Callback long click worked");
        final int indexEleveDelete = eleveList.indexOf(eleve);
        eleveList.remove(eleve);
        // eleveAdapter.notifyDataSetChanged();
        eleveAdapter.notifyItemRangeRemoved(indexEleveDelete, 1);
    }

    public class LoadingEleveAsyncTask extends AsyncTask {
        private EleveBean eleveLoaded;
        private Exception exception;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                eleveLoaded = WebServiceUtils.loadEleveFromWeb();
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (exception != null) {
                Toast.makeText(TPElevesListActivity.this, "ERROR: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                super.onPostExecute(o);
                //EleveBean newEleve = new EleveBean("Name" + eleveList.size(), "FirstName" + eleveList.size());
                eleveList.add(0, eleveLoaded); // Méthode de l'interface Java List, qui permet de rajouter à une certaine position un nouvel élément.
                eleveAdapter.notifyDataSetChanged();
                progressBarLoadEleve.setVisibility(View.GONE);
            }
        }
    }
}
