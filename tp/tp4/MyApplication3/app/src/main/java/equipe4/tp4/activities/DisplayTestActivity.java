package equipe4.tp4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import equipe4.tp4.R;


public class DisplayTestActivity extends AppCompatActivity {
    private final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_test);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_MESSAGE);
        WebView displayResult = initDisplayResult();
        displayResult.loadUrl(url);
    }

    /**
     * Initialize the webView
     *
     */
    private WebView initDisplayResult() {
        WebView displayResult = findViewById(R.id.webView);
        displayResult.setInitialScale(1);
        displayResult.getSettings().setLoadWithOverviewMode(true);
        displayResult.getSettings().setUseWideViewPort(true);
        return displayResult;
    }
}