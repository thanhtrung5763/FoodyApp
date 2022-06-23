package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class IntroActivity extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Session session = new Session(getApplicationContext());
                CustomerDomain customerDomain = session.getSession("obj_customer");
                if (customerDomain != null) {
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(IntroActivity.this, GetStartedActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000);
    }
}