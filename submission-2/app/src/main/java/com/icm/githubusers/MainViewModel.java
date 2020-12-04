package com.icm.githubusers;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<User>> listUsers = new MutableLiveData<>();

    void fetchUser(final String username) {
        final ArrayList<User> listItems = new ArrayList<>();

        String url = "https://api.github.com/search/users?q=" + username;

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", "request");
        client.addHeader("Authorization", "token 12cab5680d30867721d118378734a38da0ccb130");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("items");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        User user = new User();
                        user.setUsername(obj.getString("login"));
                        user.setHtmlUrl(obj.getString("html_url"));
                        user.setAvatar(obj.getString("avatar_url"));

                        listItems.add(user);
                    }

                    listUsers.postValue(listItems);
                } catch (Exception e) {
                    Log.d("[Exception] : ", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("[onFailure]", Objects.requireNonNull(error.getMessage()));
            }
        });
    }

    void fetchList() {
        final ArrayList<User> listItems = new ArrayList<>();

        String url = "https://api.github.com/search/users?q=repos:%3E12+followers:%3C1000&location:uk+language:python&page=1&per_page=100;";

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", "request");
        client.addHeader("Authorization", "token 12cab5680d30867721d118378734a38da0ccb130");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("items");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        User user = new User();
                        user.setUsername(obj.getString("login"));
                        user.setHtmlUrl(obj.getString("html_url"));
                        user.setAvatar(obj.getString("avatar_url"));

                        listItems.add(user);
                    }

                    listUsers.postValue(listItems);
                } catch (Exception e) {
                    Log.d("[Exception] : ", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("[onFailure]", Objects.requireNonNull(error.getMessage()));
            }
        });
    }

    LiveData<ArrayList<User>> getUsers() {
        return listUsers;
    }
}