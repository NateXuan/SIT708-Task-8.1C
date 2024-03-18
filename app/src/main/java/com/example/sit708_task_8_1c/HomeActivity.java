package com.example.sit708_task_8_1c;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HomeActivity extends AppCompatActivity {

    private EditText youtubeUrlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        youtubeUrlEditText = findViewById(R.id.youtubeUrl);

        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = youtubeUrlEditText.getText().toString();

                String videoId = extractYoutubeVideoId(videoUrl);
                if (videoId != null) {
                    Intent intent = new Intent(HomeActivity.this, PlayerActivity.class);
                    intent.putExtra(PlayerActivity.VIDEO_URL, videoId);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.addToPlaylistButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = youtubeUrlEditText.getText().toString();
                addToPlaylist(videoUrl);
            }
        });

        findViewById(R.id.myPlaylistButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MyPlaylistActivity.class);
                startActivity(intent);
            }
        });
    }

    private String extractYoutubeVideoId(String videoUrl) {
        Pattern pattern = Pattern.compile("^https?://www\\.youtube\\.com/.*v=([^&]*)",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(videoUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }

        pattern = Pattern.compile("^https?://youtu\\.be/([^&]*)",
                Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(videoUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private void addToPlaylist(String videoUrl) {
        SharedPreferences prefs = getSharedPreferences("MyPlaylistPref", MODE_PRIVATE);
        Set<String> playlist = prefs.getStringSet("playlist", new HashSet<String>());

        if (!videoUrl.isEmpty() && !playlist.contains(videoUrl)) {
            Set<String> newPlaylist = new HashSet<>(playlist);
            newPlaylist.add(videoUrl); // Add the new URL
            prefs.edit().putStringSet("playlist", newPlaylist).apply();
            Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "This video is already in the playlist", Toast.LENGTH_SHORT).show();
        }
    }
}