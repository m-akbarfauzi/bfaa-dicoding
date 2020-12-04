package com.icm.githubusers;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailUser extends AppCompatActivity {

    TextView txtUsernameDetail, txtNameDetail, txtLocationDetail, txtRepositoryDetail, txtCompanyDetail, txtFollowersDetail, txtFollowingDetail;
    CircleImageView imgPhoto;
    public static final String EXTRA_DATA = "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        ActionBar menu = getSupportActionBar();
        if (menu != null) {
            menu.setDisplayShowHomeEnabled(true);
            menu.setDisplayHomeAsUpEnabled(true);
        }

        txtUsernameDetail = findViewById(R.id.txtUsername);
        txtNameDetail = findViewById(R.id.txtName);
        txtLocationDetail = findViewById(R.id.txtLocation);
        txtRepositoryDetail = findViewById(R.id.txtRepository);
        txtCompanyDetail = findViewById(R.id.txtCompany);
        txtFollowersDetail = findViewById(R.id.txtFollowers);
        txtFollowingDetail = findViewById(R.id.txtFollowing);
        imgPhoto = findViewById(R.id.imgPhoto);

        User detail = getIntent().getParcelableExtra(EXTRA_DATA);

        if (detail != null) {
            txtUsernameDetail.setText(detail.getUsername());
            txtNameDetail.setText(detail.getName());
            txtLocationDetail.setText(detail.getLocation());
            txtRepositoryDetail.setText(detail.getRepository());
            txtCompanyDetail.setText(detail.getCompany());
            txtFollowersDetail.setText(detail.getFollowers());
            txtFollowingDetail.setText(detail.getFollowing());
            imgPhoto.setImageResource(detail.getAvatar());
        }
    }
}