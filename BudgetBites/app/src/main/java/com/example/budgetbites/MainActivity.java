package com.example.budgetbites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.budgetbites.Intro.Intro;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.budgetbites.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.api.Advice;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth fa;
    private FirebaseUser fu;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();

        if(fu==null){
            Intent i = new Intent(MainActivity.this, Intro.class);
            startActivity(i);
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_history, R.id.navigation_dashboard, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }


    private void getQuote() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.adviceslip.com/advice")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }


            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                try {
                    JSONObject responseObject = new JSONObject(responseString);
                    JSONObject slipObject = responseObject.getJSONObject("slip");
                    int slipId = slipObject.getInt("id");
                    String advice = slipObject.getString("advice");

                    Quote quote = new Quote();
                    quote.setId(slipId);
                    quote.setAdvice(advice);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showAdvice(quote);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showAdvice(Quote adviceSlip) {
        Snackbar.make(binding.getRoot(), adviceSlip.getQuote(), Snackbar.LENGTH_LONG).show();
    }





}