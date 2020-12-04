package com.icm.consumerapp;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TABLE_NAME = "user";
    public static final String AUTHORITY = "com.icm.githubusers.provider";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_HTML_URL = "html_url";
    public static final String COLUMN_AVATAR = "avatar";

    @BindView(R.id.rv_list_user)
    RecyclerView rvListUser;

    @SuppressLint("Recycle")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        rvListUser.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvListUser.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter adapter = new MainAdapter();
        adapter.notifyDataSetChanged();
        rvListUser.setAdapter(adapter);

        ContentProviderClient contentProviderClient = getContentResolver().acquireContentProviderClient(CONTENT_URI);
        Cursor cursor;
        try {
            assert contentProviderClient != null;
            cursor = contentProviderClient.query(CONTENT_URI, null, null, null, null);
            adapter.setData(cursor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
