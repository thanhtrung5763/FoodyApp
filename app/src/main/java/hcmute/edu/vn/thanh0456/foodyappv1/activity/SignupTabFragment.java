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

public class SignupTabFragment extends Fragment {
    EditText username, passwordreg, passwordreg2, phoneno;
    TextView alreadyHaveAccount;
    Button signupBTN;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        username = root.findViewById(R.id.username);
        passwordreg = root.findViewById(R.id.passwordreg);
        passwordreg2 = root.findViewById(R.id.passwordreg2);
        phoneno = root.findViewById(R.id.phoneno);
        alreadyHaveAccount = root.findViewById(R.id.have_account);
        signupBTN = root.findViewById(R.id.signup_btn);
        return root;
    }
}
