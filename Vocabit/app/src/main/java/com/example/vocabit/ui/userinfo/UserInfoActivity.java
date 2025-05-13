package com.example.vocabit.ui.userinfo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.vocabit.R;
import com.example.vocabit.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {

    private UserInfoViewModel viewModel;
    private ActivityUserInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sử dụng Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        viewModel = new UserInfoViewModel(this);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Observe avatar URL và load bằng Glide
        viewModel.avatarUrl.addOnPropertyChangedCallback(new androidx.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(androidx.databinding.Observable sender, int propertyId) {
                String url = viewModel.avatarUrl.get();
                if (url != null && !url.isEmpty()) {
                    ImageView avatarImageView = findViewById(R.id.imgAvatar);
                    Glide.with(UserInfoActivity.this)
                            .load(url)
                            .placeholder(R.drawable.ic_profile) // ảnh mặc định
                            .error(R.drawable.ic_profile)       // ảnh khi lỗi
                            .into(avatarImageView);
                }
            }
        });
    }
}
