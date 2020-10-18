package com.example.dynamicFormsGenerator.featureModules.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.databinding.ActivityLoginBinding;
import com.example.dynamicFormsGenerator.featureModules.main.views.MainActivity;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.toolbar.setTitle(R.string.login);
        binding.toolbar.setTitleTextAppearance(this, R.style.WhiteToolBarTitleMedium);

        setClickListeners();
    }

    private void setClickListeners() {
        binding.btnSignIn.setOnClickListener(v -> callNextActivity());
    }

    private void callNextActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        startActivity(intent);
        finish();
    }
}
