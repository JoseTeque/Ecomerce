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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.hermosaprogramacion.premium.ecomerce.RegisterActivity.onResetPassword;


/**
 * A simple {@link Fragment} subclass.
 */
public class SigInFragment extends Fragment {

    private TextView dontHaveAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText password;

    private ImageButton btnClose;
    private ProgressBar progressBar;
    private Button btnSigIn;

    private TextView forgotPassword;

    private FirebaseAuth firebaseAuth;

    private  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    public SigInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sig_in, container, false);
        dontHaveAccount = view.findViewById(R.id.dontHaveAcoount);
        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);

        email = view.findViewById(R.id.edtxEmailIdSignIn);
        password = view.findViewById(R.id.edtxpasswordSignIn);

        btnClose = view.findViewById(R.id.imageButton);
        progressBar = view.findViewById(R.id.signInProgressBar);
        btnSigIn = view.findViewById(R.id.btnSignIn);

        forgotPassword = view.findViewById(R.id.txtForgotPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
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
              checkInput();
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
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPassword = true;
                setFragment(new ResetPasswordFragment());
            }
        });
    }

    private void checkEmailAndPassword() {

           if (email.getText().toString().matches(emailPattern))
           {
               if (password.length() >= 8)
               {
                   progressBar.setVisibility(View.VISIBLE);
                   btnSigIn.setEnabled(false);
                   btnSigIn.setTextColor(Color.argb(50,255,255,255));

                 firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                         .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if (task.isSuccessful())
                                 {
                                     startActivity(new Intent(getActivity(),MainActivity.class));
                                     getActivity().finish();

                                 }else
                                 {
                                     progressBar.setVisibility(View.INVISIBLE);
                                     String error = task.getException().getMessage();
                                     Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                     btnSigIn.setEnabled(true);
                                     btnSigIn.setTextColor(Color.rgb(255,255,255));
                                 }
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         btnSigIn.setEnabled(true);
                         btnSigIn.setTextColor(Color.rgb(255,255,255));
                     }
                 });
               }
               else
               {
                   Toast.makeText(getActivity(), "Incorrect email or password..", Toast.LENGTH_SHORT).show();
               }
           }else {
               Toast.makeText(getActivity(), "Incorrect email or password..", Toast.LENGTH_SHORT).show();

           }
    }

    private void checkInput() {

        if (!TextUtils.isEmpty(email.getText().toString()))
        {
            if (!TextUtils.isEmpty(password.getText().toString())) {

                btnSigIn.setEnabled(true);
                btnSigIn.setTextColor(Color.rgb(255,255,255));

            }else{

                btnSigIn.setEnabled(false);
                btnSigIn.setTextColor(Color.argb(50,255,255,255));
            }
        }else
        {
            btnSigIn.setEnabled(false);
            btnSigIn.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        transaction.replace(parentFrameLayout.getId(),fragment);
        transaction.commit();
    }
}
