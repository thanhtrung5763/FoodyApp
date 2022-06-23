package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class SignupTabFragment extends Fragment {
    EditText username, passwordreg, passwordreg2, email;
    TextView alreadyHaveAccount;
    Button signupBTN;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        username = root.findViewById(R.id.username);
        passwordreg = root.findViewById(R.id.passwordreg);
        passwordreg2 = root.findViewById(R.id.passwordreg2);
        email = root.findViewById(R.id.email);

        alreadyHaveAccount = root.findViewById(R.id.have_account);
        alreadyHaveAccount.setOnClickListener(view -> {
            getFragmentManager().popBackStack("LoginActivity", 0);
        });
        signupBTN = root.findViewById(R.id.signup_btn);
        signupBTN.setOnClickListener(view -> {
            insertCustomer();
        });
        return root;
    }
    private void insertCustomer() {
        if(!validateUsername() || !validateEmail() || !validatePassword()) {
            return;
        }
        else {
            DBHelper dbHelper = new DBHelper(getActivity());
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username.getText().toString().trim());
            contentValues.put("email", email.getText().toString().trim());
            contentValues.put("password", passwordreg.getText().toString().trim());

            if(dbHelper.checkUsername(contentValues.getAsString("username"))) {
                if(dbHelper.insert("customer", contentValues)) {
                    Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                username.setError("Username already exists");
            }
        }

    }
    private boolean validateUsername() {
        String whiteSpace = " ";
        String val = username.getText().toString();
        if(val.trim().equalsIgnoreCase("")) {
            username.setError("Field cannot be empty");
            return false;
        }
        else if (val.length() >= 15) {
            username.setError("Username too long");
            return false;
        } else if (val.contains(whiteSpace)) {
            username.setError("White Spaces are not allowed");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String val = email.getText().toString();
        if(val.trim().equalsIgnoreCase("")) {
            email.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String val = passwordreg.getText().toString();
        String val2 = passwordreg2.getText().toString();

        if(val.trim().equalsIgnoreCase("")) {
            passwordreg.setError("Field cannot be empty");
            return false;
        }
        else if(val2.trim().equalsIgnoreCase("")) {
            passwordreg2.setError("Field cannot be empty");
            return false;
        }
        else if(!val.equals(val2)) {
            passwordreg.setError("Password do not match");
            passwordreg2.setError("Password do not match");
            return false;
        }
        else {
            passwordreg.setError(null);
            passwordreg2.setError(null);
            return true;
        }
    }
}
