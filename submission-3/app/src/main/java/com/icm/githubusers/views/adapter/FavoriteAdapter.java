package com.icm.githubusers.views.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;
import com.icm.githubusers.R;
import com.icm.githubusers.db.DatabaseApp;
import com.icm.githubusers.db.UserDAO;
import com.icm.githubusers.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private ArrayList<User> listUser = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(ArrayList<User> items) {
        listUser.clear();
        listUser.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        holder.setItem(listUser.get(position));

        holder.itemView.setOnClickListener(view -> onItemClickCallback.onItemClicked(listUser.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public interface OnItemClickCallback {
        void onItemClicked(User data);
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fav_txt_htmlUrl)
        TextView html_url;
        @BindView(R.id.fav_txt_username)
        TextView username;
        @BindView(R.id.fav_img_avatar)
        ImageView avatar;
        @BindView(R.id.fav_btn_delete_fav)
        ImageButton btnDeleteFav;

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setItem(User item) {
            html_url.setText(item.getHtmlUrl());
            username.setText(item.getUsername());
            Picasso.get()
                    .load(item.getAvatar())
                    .resize(100, 100)
                    .centerCrop()
                    .into(avatar);

            btnDeleteFav.setOnClickListener(view -> {
                final AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                alert.setTitle(R.string.alert_delete);
                alert.setMessage(R.string.confirm_delete);
                alert.setCancelable(false);
                alert.setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    UserDAO userDAO = Room.databaseBuilder(itemView.getContext(), DatabaseApp.class, "user")
                            .allowMainThreadQueries()
                            .build()
                            .userDAO();

                    userDAO.deleteByUsername(item.getUid());

                    listUser.remove(item);
                    notifyDataSetChanged();

                    Snackbar.make(view, R.string.delete_success, Snackbar.LENGTH_SHORT).show();
                });
                alert.setNegativeButton(R.string.no, ((dialogInterface, i) -> alert.setCancelable(true)));
                alert.show();
            });
        }
    }
}
