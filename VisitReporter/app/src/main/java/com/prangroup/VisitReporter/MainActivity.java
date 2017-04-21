package com.prangroup.VisitReporter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prangroup.VisitReporter.BusinessObjects.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user=new User(MainActivity.this);
        if (user.isLogin()){
            Intent i = new Intent(getBaseContext(), VisitReporterActivity.class);
            startActivity(i);
        }else {
            splashScreen();
        }
    }
    private void splashScreen() {
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(1 * 1000);
                    Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }
}
