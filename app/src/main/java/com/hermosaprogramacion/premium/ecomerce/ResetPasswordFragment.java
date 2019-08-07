package com.hermosaprogramacion.premium.ecomerce;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {

    private TextView goBack;
    private FrameLayout parentFrameLayout;
    private EditText email;
    private Button btnReset;

    private ViewGroup emailcontainer;
    private ImageView iconEmail;
    private TextView emailIconText;
    private ProgressBar progressBar;


    private FirebaseAuth firebaseAuth;

    private  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        goBack = view.findViewById(R.id.forgot_password_txt_go_back);
        email = view.findViewById(R.id.forgot_email_icon);
        btnReset = view.findViewById(R.id.forgot_password_button);
        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);
        emailIconText = view.findViewById(R.id.forgot_password_icon_email_text);
        emailcontainer = view.findViewById(R.id.forgot_password_icon_mail_container);
        iconEmail = view.findViewById(R.id.forgot_password_email);
        progressBar = view.findViewById(R.id.forgot_password_progressbar);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              setFragment(new SigInFragment());
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

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition(emailcontainer);
                emailIconText.setVisibility(View.GONE);

                TransitionManager.beginDelayedTransition(emailcontainer);
                iconEmail.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                btnReset.setEnabled(false);
                btnReset.setTextColor(Color.argb(50,255,255,255));

              firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                      .addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                              if (task.isSuccessful())
                              {
                                  ScaleAnimation animation = new ScaleAnimation(1,0,1,0,iconEmail.getWidth()/2,iconEmail.getHeight()/2);
                                  animation.setDuration(100);
                                  animation.setInterpolator(new AccelerateInterpolator());
                                  animation.setRepeatMode(Animation.REVERSE);
                                  animation.setRepeatCount(1);
                                  animation.setAnimationListener(new Animation.AnimationListener() {
                                      @Override
                                      public void onAnimationStart(Animation animation) {

                                      }

                                      @Override
                                      public void onAnimationEnd(Animation animation) {
                                          emailIconText.setText("Recovery email sent successfully ! check your inbox");
                                          emailIconText.setTextColor(getResources().getColor(R.color.successGreen));

                                          TransitionManager.beginDelayedTransition(emailcontainer);
                                          emailIconText.setVisibility(View.VISIBLE);
                                      }

                                      @Override
                                      public void onAnimationRepeat(Animation animation) {
                                          iconEmail.setImageResource(R.drawable.mail_outline_24px);
                                      }
                                  });

                                  iconEmail.startAnimation(animation);
                              }
                              else
                              {
                                  String error = task.getException().getMessage();
                                  progressBar.setVisibility(View.GONE);

                                  TransitionManager.beginDelayedTransition(emailcontainer);
                                  emailIconText.setText(error);
                                  emailIconText.setTextColor(getResources().getColor(R.color.colorPrimary));
                                  emailIconText.setVisibility(View.VISIBLE);
                                  iconEmail.setImageResource(R.drawable.ic_mail_outline_24px);
                                  btnReset.setEnabled(true);
                                  btnReset.setTextColor(Color.rgb(255,255,255));
                              }
                              progressBar.setVisibility(View.GONE);

                          }
                      });
            }
        });
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText().toString()))
        {
            btnReset.setEnabled(true);
            btnReset.setTextColor(Color.rgb(255,255,255));
        }else
        {
            btnReset.setEnabled(false);
            btnReset.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        transaction.replace(parentFrameLayout.getId(),fragment);
        transaction.commit();
    }
}
