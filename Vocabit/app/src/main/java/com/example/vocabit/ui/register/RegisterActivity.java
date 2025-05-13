package com.example.vocabit.ui.register;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.vocabit.MVVMApplication;
import com.example.vocabit.R;
import com.example.vocabit.data.Repository;
import com.example.vocabit.databinding.ActivityRegisterBinding;
import com.example.vocabit.ui.login.LoginActivity;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1001;
    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ViewModel and Repository
        Repository repository = ((MVVMApplication) getApplication()).getRepository();
        RegisterViewModelFactory factory = new RegisterViewModelFactory(repository, (MVVMApplication) getApplication());
        viewModel = new ViewModelProvider(this, factory).get(RegisterViewModel.class);

        // Bind ViewModel and lifecycle to layout
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Observe the avatar selection event to open the image picker
        viewModel.chooseAvatarEvent.observe(this, unused -> openImagePicker());

        // Observe avatar URL to update UI if necessary
        viewModel.avatarUrl.observe(this, url -> {
            if (url != null && !url.isEmpty()) {
                // Optionally load the image using Glide or Picasso
                // Glide.with(this).load(url).into(binding.imgAvatar);
            }
        });

        // Observe registration success to navigate to login page
        viewModel.getRegisterSuccess().observe(this, success -> {
            if (success != null && success) {
                Toast.makeText(this, "Đăng ký thành công! Quay về trang đăng nhập...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close RegisterActivity to prevent going back
            }
        });

        // Set up Spinner for classLevel selection
        Spinner classLevelSpinner = binding.spinnerClass;
        classLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                String selectedClassLevel = parentView.getItemAtPosition(position).toString();
                viewModel.classLevel.setValue(selectedClassLevel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle if nothing is selected
            }
        });
    }

    // Method to open image picker
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    // Handle result of image picker
    @Override
    @SuppressWarnings("deprecation")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            binding.imgAvatar.setImageURI(selectedImage);

            if (selectedImage != null) {
                uploadImageToCloudinary(selectedImage);
            }
        }
    }

    // Method to upload image to Cloudinary
    private void uploadImageToCloudinary(Uri imageUri) {
        MediaManager.get().upload(imageUri)
                .option("resource_type", "image")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Toast.makeText(RegisterActivity.this, "Đang tải ảnh lên...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {}

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String url = resultData.get("secure_url").toString();
                        viewModel.avatarUrl.setValue(url);
                        Toast.makeText(RegisterActivity.this, "Tải ảnh thành công!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Toast.makeText(RegisterActivity.this, "Tải ảnh thất bại: " + error.getDescription(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {}
                })
                .dispatch();
    }
}
