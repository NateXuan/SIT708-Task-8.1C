package com.example.sit708_task_8_1c;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerActivity extends AppCompatActivity {
    public static final String VIDEO_URL = "VIDEO_URL";
    private WebView webViewPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        webViewPlayer = findViewById(R.id.webViewPlayer);
        webViewPlayer.getSettings().setJavaScriptEnabled(true);
        webViewPlayer.setWebViewClient(new WebViewClient());

        String videoUrl = getIntent().getStringExtra("VIDEO_URL");
        playVideo(videoUrl);
    }

    private void playVideo(String videoUrl) {
        String frameVideo = "<html><body><iframe width=\"match_parent\" height=\"match_parent\" src=\"https://www.youtube.com/embed/" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        webViewPlayer.loadData(frameVideo, "text/html", "utf-8");
    }
}