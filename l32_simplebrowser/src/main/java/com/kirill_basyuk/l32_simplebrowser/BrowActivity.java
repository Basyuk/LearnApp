package com.kirill_basyuk.l32_simplebrowser;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class BrowActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    EditText enterSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brow);

        webView = findViewById(R.id.webView);
        enterSite = findViewById(R.id.enterSite);
        Button btnGo = findViewById(R.id.goButton);

        webView.setWebViewClient(new WebViewClient());
        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());

        btnGo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goButton:
                webView.loadUrl("http://" + enterSite.getText().toString());
        }
    }

}
