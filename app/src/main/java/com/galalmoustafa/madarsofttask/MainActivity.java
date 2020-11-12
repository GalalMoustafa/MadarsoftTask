package com.galalmoustafa.madarsofttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.galalmoustafa.madarsofttask.database.AppDatabase;
import com.galalmoustafa.madarsofttask.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton showUser, saveUser;
    private EditText name_et, age_et, gender_et, job_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_et = findViewById(R.id.name_input);
        age_et = findViewById(R.id.age_input);
        gender_et = findViewById(R.id.gender_input);
        job_et = findViewById(R.id.job_input);

        showUser = findViewById(R.id.show_btn);
        showUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });
        saveUser = findViewById(R.id.save_btn);
        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveUser();
            }
        });
    }

    private void SaveUser() {
        if (Validate()){
            User user = new User();
            user.setId(new Random().nextInt());
            user.setName(name_et.getText().toString());
            user.setAge(age_et.getText().toString());
            user.setGender(gender_et.getText().toString());
            user.setJob_title(job_et.getText().toString());

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    AppDatabase.getInstance(MainActivity.this).userDao().InsertUser(user);
                    Log.d("User", "Added");
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "User Added Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }
        else {
            Toast.makeText(this, "Enter All Data First!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean Validate() {
        if (name_et.getText().toString().trim().length() == 0 || job_et.getText().toString().trim().length() == 0
            || age_et.getText().toString().trim().length() == 0 || gender_et.getText().toString().trim().length() == 0){
            return false;
        }
        else return true;
    }
}