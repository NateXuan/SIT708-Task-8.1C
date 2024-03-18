package com.example.sit708_task_8_1c;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyPlaylistActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlist);

        listView = findViewById(R.id.listViewPlaylist);

        // Load the playlist
        SharedPreferences prefs = getSharedPreferences("MyPlaylistPref", MODE_PRIVATE);
        Set<String> playlist = prefs.getStringSet("playlist", new HashSet<String>());

        // Set up the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(playlist));
        listView.setAdapter(adapter);
    }
}
