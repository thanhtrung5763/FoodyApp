package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class LoginTabFragment extends Fragment {
    EditText username, password;
    TextView forgetPass;
    Button loginBTN;
    float v = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.password);
        forgetPass = root.findViewById(R.id.forget_pass);
        loginBTN = root.findViewById(R.id.login_btn);

        username.setTranslationX(800);
        password.setTranslationX(800);
        forgetPass.setTranslationX(800);
        loginBTN.setTranslationX(800);

        username.setAlpha(v);
        password.setAlpha(v);
        forgetPass.setAlpha(v);
        loginBTN.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        loginBTN.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(700).start();

        return root;
    }
}
