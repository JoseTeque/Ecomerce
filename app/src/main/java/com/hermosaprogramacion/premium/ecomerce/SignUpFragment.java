package com.hermosaprogramacion.premium.ecomerce;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private TextView alreadyHaveAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;

    private ImageButton closeSignUp;
    private Button btnSignUp;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        alreadyHaveAccount = view.findViewById(R.id.already_account);
        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);

        email = view.findViewById(R.id.edtxEmailIdSignIn);
        fullName = view.findViewById(R.id.edtxName);
        password = view.findViewById(R.id.edtxPasswordSignUp);
        confirmPassword = view.findViewById(R.id.edtxConfirmPassword);

        closeSignUp = view.findViewById(R.id.imageButtonSignUp);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        progressBar = view.findViewById(R.id.signUpProgressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SigInFragment());
            }
        });

        closeSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkEmailAndPassword();

            }
        });
    }

    private void checkEmailAndPassword() {

        Drawable custom_error = getResources().getDrawable(R.drawable.custom_error_icon);
        custom_error.setBounds(0,0,custom_error.getIntrinsicWidth(),custom_error.getIntrinsicHeight());

        if (email.getText().toString().matches(emailPattern))
        {
            if (password.getText().toString().equals(confirmPassword.getText().toString()))
            {
                progressBar.setVisibility(View.VISIBLE);
                btnSignUp.setEnabled(false);
                btnSignUp.setTextColor(Color.argb(50,255,255,255));

               firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful())
                               {
                                   Map<Object,String> userData = new HashMap<>();
                                   userData.put("fullName", fullName.getText().toString());

                                   firebaseFirestore.collection("USERS")
                                           .add(userData)
                                           .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                               @Override
                                               public void onComplete(@NonNull Task<DocumentReference> task) {
                                                   if (task.isSuccessful())
                                                   {
                                                       startActivity(new Intent(getActivity(),MainActivity.class));
                                                       getActivity().finish();
                                                   }
                                                   else
                                                   {
                                                       progressBar.setVisibility(View.INVISIBLE);
                                                       btnSignUp.setEnabled(true);
                                                       btnSignUp.setTextColor(Color.rgb(255,255,255));
                                                       String error = task.getException().getMessage();
                                                       Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                                   }
                                               }
                                           });
                               }
                               else
                               {
                                   progressBar.setVisibility(View.INVISIBLE);
                                   btnSignUp.setEnabled(true);
                                   btnSignUp.setTextColor(Color.rgb(255,255,255));
                                   String error = task.getException().getMessage();
                                   Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
            }
            else
            {
                confirmPassword.setError("No coinciden los password",custom_error);

            }
        }else
        {
           email.setError("Invalite email.!",custom_error);
        }

    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText().toString()))
        {
           if (!TextUtils.isEmpty(fullName.getText().toString()))
           {
               if (!TextUtils.isEmpty(password.getText().toString()) && password.length() >= 8)
               {
                   if (!TextUtils.isEmpty(confirmPassword.getText().toString()))
                   {
                       btnSignUp.setEnabled(true);
                       btnSignUp.setTextColor(Color.rgb(255,255,255));

                   }else {
                       btnSignUp.setEnabled(false);
                       btnSignUp.setTextColor(Color.argb(50,255,255,255));
                   }

               }else {
                   btnSignUp.setEnabled(false);
                   btnSignUp.setTextColor(Color.argb(50,255,255,255));
               }
           }else {
               btnSignUp.setEnabled(false);
               btnSignUp.setTextColor(Color.argb(50,255,255,255));
           }
        }else
        {
            btnSignUp.setEnabled(false);
            btnSignUp.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        transaction.replace(parentFrameLayout.getId(),fragment);
        transaction.commit();
    }
}
