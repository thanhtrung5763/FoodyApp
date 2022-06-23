package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class GetStartedActivity extends AppCompatActivity {
    TextView getStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTheme().applyStyle(R.style.DefaultTheme, true);
        setContentView(R.layout.activity_get_started);
        getStarted = findViewById(R.id.getStarted);
        getStarted.setOnClickListener(view -> {
            Intent intent = new Intent(GetStartedActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}