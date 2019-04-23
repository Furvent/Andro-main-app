package com.mainapp.furvent.mainapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mainapp.furvent.mainapp.data.OkHttpUtils;

import okhttp3.OkHttpClient;

public class WebExempleActivity extends AppCompatActivity {

    private WebView webView;
    private TextView textViewHtml;
    private EditText editTextSearch;
    private Button buttonGoto;

    HttpRequestAsyncTask httpRequestAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_exemple);
        initWebView();

        textViewHtml = findViewById(R.id.textViewHtml);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonGoto = findViewById(R.id.buttonGoto);

        buttonGoto.setOnClickListener(v ->  {
            webView.loadUrl(editTextSearch.getText().toString());

            if (httpRequestAsyncTask == null || httpRequestAsyncTask.getStatus()
                    == AsyncTask.Status.FINISHED) {
                httpRequestAsyncTask = new HttpRequestAsyncTask();
                httpRequestAsyncTask.execute();
            }
        });
    }

    private void initWebView() {
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webViewSetting = webView.getSettings();
        webViewSetting.setJavaScriptEnabled(true);
    }

    public class HttpRequestAsyncTask extends AsyncTask {
        private Exception exception;
        private String responseStringify;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                responseStringify = OkHttpUtils.SendGetOkHttpRequest(editTextSearch.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (exception != null) {
                Toast.makeText(WebExempleActivity.this, "ERROR: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                textViewHtml.setText(responseStringify);
            }
        }
    }
}
