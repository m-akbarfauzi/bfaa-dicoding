package com.icm.githubusers.views;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icm.githubusers.R;
import com.icm.githubusers.viewmodels.MainViewModel;
import com.icm.githubusers.views.adapter.UserAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.linearNotFound)
    LinearLayout linearNotFound;
    @BindView(R.id.main_search_username)
    SearchView searchUsername;
    private UserAdapter adapter;
    private MainViewModel mainViewModel;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            searchUsername.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchUsername.onActionViewExpanded();
            searchUsername.setQueryHint(getResources().getString(R.string.search_hint));
            searchUsername.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    linearNotFound.setVisibility(View.GONE);
                    showLoading(true);
                    mainViewModel.fetchUser(s);

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });

        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.getUsers().observe(this, users -> {
            adapter.setData(users);

            if (users.size() == 0) linearNotFound.setVisibility(View.VISIBLE);
            else linearNotFound.setVisibility(View.GONE);

            showLoading(false);
        });
        mainViewModel.fetchList();
        showLoading(true);

        adapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(MainActivity.this, DetailUser.class);
            intent.putExtra(DetailUser.EXTRA_DATA, data);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_language:
                Intent changeLanguage = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(changeLanguage);
                break;

            case R.id.menu_favorite:
                Intent fav = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(fav);
                break;

            case R.id.menu_notification:
                Intent notif = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(notif);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLoading(Boolean state) {
        if (state) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }
}