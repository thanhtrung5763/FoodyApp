package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import hcmute.edu.vn.thanh0456.foodyappv1.DAO.CustomerDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
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

        load();
        loginBTN.setOnClickListener(view -> {
            login();
        });
        return root;
    }

    private void load() {
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
    }
    private boolean validateUsername() {
        String val = username.getText().toString();
        if(val.trim().equalsIgnoreCase("")) {
            username.setError("Field cannot be empty");
            return false;
        }
        else if (val.length() >= 15) {
            username.setError("Username too long");
            return false;
        } else if (val.contains(" ")) {
            username.setError("White Spaces are not allowed");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String val = password.getText().toString();

        if(val.trim().equalsIgnoreCase("")) {
            password.setError("Field cannot be empty");
            return false;
        }
        else if (val.contains(" ")) {
            username.setError("White Spaces are not allowed");
            return false;
        }
        else {
            password.setError(null);
            return true;
        }
    }
    private void login() {
        if(!validateUsername() || !validatePassword()) {
            return;
        }
        else {
            DBHelper dbHelper = new DBHelper(getActivity());
            String usn = username.getText().toString();
            String pwd = password.getText().toString();
            if(dbHelper.checkUsernamePassword(usn, pwd)) {
                Toast.makeText(getActivity(), "Login successfull", Toast.LENGTH_SHORT).show();

                CustomerDAO customerDAO = new CustomerDAO(getActivity());
                CustomerDomain customerDomain = customerDAO.get(usn);
                Session session = new Session(getActivity().getApplicationContext());
                session.storeSession("obj_customer", customerDomain);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getActivity(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
