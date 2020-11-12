package com.galalmoustafa.madarsofttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.galalmoustafa.madarsofttask.database.AppDatabase;
import com.galalmoustafa.madarsofttask.models.User;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    TextView name_tv, age_tv, gender_tv, job_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name_tv = findViewById(R.id.name_value);
        age_tv = findViewById(R.id.age_value);
        gender_tv = findViewById(R.id.gender_value);
        job_tv = findViewById(R.id.job_title_value);
        LiveData<List<User>> usersFromDB = AppDatabase.getInstance(this).userDao().LoadAllUsers();
        usersFromDB.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.size() != 0){
                    User user = users.get(users.size() - 1);
                    setValues(user.getName(), user.getAge(), user.getGender(), user.getJob_title());
                }
                else {
                    Toast.makeText(UserActivity.this, "Add User First!", Toast.LENGTH_SHORT).show();
                    setValues("Not Available", "Not Available", "Not Available", "Not Available");
                }
            }
        });
    }

    private void setValues(String name, String age, String gender, String job){
        name_tv.setText(name);
        age_tv.setText(age);
        gender_tv.setText(gender);
        job_tv.setText(job);
    }
}