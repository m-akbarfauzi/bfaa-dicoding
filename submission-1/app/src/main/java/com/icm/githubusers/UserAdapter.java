package com.icm.githubusers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> users;

    public void setUser(ArrayList<User> users) {
        this.users = users;
    }

    public UserAdapter(Context context) {
        this.context = context;
        users = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        User hero = (User) getItem(i);
        viewHolder.bind(hero);
        return view;
    }

    private static class ViewHolder {
        private TextView txtUsername;
        private TextView txtFollowers;
        private TextView txtFollowing;
        private ImageView imgAvatar;

        ViewHolder(View view) {
            txtUsername = view.findViewById(R.id.txtUsername);
            txtFollowers = view.findViewById(R.id.txtFollowers);
            txtFollowing = view.findViewById(R.id.txtFollowing);
            imgAvatar = view.findViewById(R.id.img_photo);
        }

        void bind(User user) {
            txtUsername.setText((user.getUsername()));
            txtFollowers.setText(user.getFollowers());
            txtFollowing.setText(user.getFollowing());
            imgAvatar.setImageResource(user.getAvatar());
        }
    }
}