package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class FoodDetailActivity extends AppCompatActivity {
    AppCompatButton bS, bM, bL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details);

        bS = findViewById(R.id.sizeS);
        bM = findViewById(R.id.sizeM);
        bL = findViewById(R.id.sizeL);
        bS.setOnClickListener(view -> {
            bS.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_bg));
            bM.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            bL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));

        });
        bM.setOnClickListener(view -> {
            bS.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            bM.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_bg));
            bL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
        });
        bL.setOnClickListener(view -> {
            bS.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            bM.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            bL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_bg));
        });
    }
}
