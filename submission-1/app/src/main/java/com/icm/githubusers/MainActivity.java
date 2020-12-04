package com.icm.githubusers;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataUsername;
    private String[] dataName;
    private String[] dataLocation;
    private String[] dataRepository;
    private String[] dataCompany;
    private String[] dataFollower;
    private String[] dataFollowing;
    private TypedArray dataAvatar;
    private UserAdapter adapter;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new UserAdapter(this);
        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                User user = new User();
                user.setUsername(users.get(i).getUsername());
                user.setName(users.get(i).getName());
                user.setLocation(users.get(i).getLocation());
                user.setRepository(users.get(i).getRepository());
                user.setCompany(users.get(i).getCompany());
                user.setFollowers(users.get(i).getFollowers());
                user.setFollowing(users.get(i).getFollowing());
                user.setAvatar(users.get(i).getAvatar());

                Intent moveDetail = new Intent(MainActivity.this, DetailUser.class);
                moveDetail.putExtra(DetailUser.EXTRA_DATA, user);
                startActivity(moveDetail);
            }
        });
    }

    private void addItem() {
        users = new ArrayList<>();

        for (int i = 0; i < dataUsername.length; i++) {
            User user = new User();
            user.setAvatar(dataAvatar.getResourceId(i, -1));
            user.setUsername(dataUsername[i]);
            user.setName(dataName[i]);
            user.setLocation(dataLocation[i]);
            user.setRepository(dataRepository[i]);
            user.setCompany(dataCompany[i]);
            user.setFollowers(dataFollower[i]);
            user.setFollowing(dataFollowing[i]);
            users.add(user);
        }
        adapter.setUser(users);
    }

    private void prepare() {
        dataUsername = getResources().getStringArray(R.array.username);
        dataName = getResources().getStringArray(R.array.name);
        dataLocation = getResources().getStringArray(R.array.location);
        dataRepository = getResources().getStringArray(R.array.repository);
        dataCompany = getResources().getStringArray(R.array.company);
        dataFollower = getResources().getStringArray(R.array.followers);
        dataFollowing = getResources().getStringArray(R.array.following);
        dataAvatar = getResources().obtainTypedArray(R.array.avatar);
    }
}