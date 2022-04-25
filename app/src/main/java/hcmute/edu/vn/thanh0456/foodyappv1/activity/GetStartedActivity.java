package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTheme().applyStyle(R.style.DefaultTheme, true);
        setContentView(R.layout.activity_get_started);
    }
}