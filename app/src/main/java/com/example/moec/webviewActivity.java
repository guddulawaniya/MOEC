package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class webviewActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        getSupportActionBar().hide();

        TextView toolbartitle  = findViewById(R.id.toolbar_title);
        WebView webview  = findViewById(R.id.webview);
        progressBar = findViewById(R.id.webloader);

        webview.getSettings().setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        Boolean id = intent.getBooleanExtra("id",false);
        if (id)
        {
            webview.loadUrl("https://www.meridean.org/terms-conditions");
            toolbartitle.setText("Terms of Use");

        }
        else
        {

            webview.loadUrl("https://www.meridean.org/contact");
            toolbartitle.setText("Contact Us");

        }

        TextView cleatext = findViewById(R.id.cleartext);

        cleatext.setVisibility(View.GONE);

        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);


        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                return true;
            }
            return false;

        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);


        }

    }
}