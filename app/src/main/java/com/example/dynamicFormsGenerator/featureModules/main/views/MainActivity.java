package com.example.dynamicFormsGenerator.featureModules.main.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.dynamicFormsGenerator.R;
import com.example.dynamicFormsGenerator.application.DynamicFormGeneratorApplication;
import com.example.dynamicFormsGenerator.databinding.ActivityMainBinding;
import com.example.dynamicFormsGenerator.featureModules.login.LoginActivity;
import com.example.dynamicFormsGenerator.featureModules.main.di.DaggerMainActivityComponent;
import com.example.dynamicFormsGenerator.featureModules.main.di.MainActivityComponent;
import com.example.dynamicFormsGenerator.utils.CommonPreferences;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    CommonPreferences prefs;

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(((DynamicFormGeneratorApplication) getApplication()).getApplicationComponent())
                .build();
        mainActivityComponent.inject(this);
        mainActivityComponent.inject(viewModel);

        binding.toolbar.setTitle(R.string.home);
        binding.toolbar.setTitleTextAppearance(this, R.style.WhiteToolBarTitleMedium);
        setSupportActionBar(binding.toolbar);
        prefs.setFirstTimeLogin(false);
        setSelectionFragment();
    }

    private void setSelectionFragment() {
        FormOptionsFragment fragment = new FormOptionsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut() {
        prefs.setFirstTimeLogin(true);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        startActivity(intent);
        finish();
    }
}