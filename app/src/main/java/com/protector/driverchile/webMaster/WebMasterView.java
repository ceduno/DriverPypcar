package com.protector.driverchile.webMaster;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.protector.driverchile.R;

/**
 * @author Marlon Viana on 26/05/2019
 * @email 92marlonViana@gmail.com
 */
public class WebMasterView extends AppCompatActivity {
    private String title;
    private String url;
    private WebView webView;
    private ProgressBar progressBar;
    private ImageView buttonCancel;
    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        title= getIntent().getExtras().getString("title");
        url= getIntent().getExtras().getString("url");

        addView();

    }

    private void addView() {
        progressBar= findViewById(R.id.progress_circular);

        textViewTitle= findViewById(R.id.txv_title_toolbar);
        textViewTitle.setText(title);

        buttonCancel= findViewById(R.id.btn_cancel);
        buttonCancel.setOnClickListener((view)->{ finish(); });

        webView= findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setInitialScale(1);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int progress) {

            }
        });

        this.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(view.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(view.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }


}
