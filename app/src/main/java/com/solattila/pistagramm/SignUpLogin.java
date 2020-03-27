package com.solattila.pistagramm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogin extends AppCompatActivity {

    private EditText edtUpName;
    private EditText edtUpPwd;
    private EditText edtInName;
    private EditText edtInPwd;
    private Button btnUp;
    private Button btnIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_act);

        edtUpName = findViewById(R.id.edtUpName);
        edtUpPwd = findViewById(R.id.edtUpPwd);
        edtInName = findViewById(R.id.edtInName);
        edtInPwd = findViewById(R.id.edtInPwd);
        btnUp = findViewById(R.id.btnUp);
        btnIn = findViewById(R.id.btnIn);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUpName.getText().toString());
                appUser.setPassword(edtUpPwd.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){

                            FancyToast.makeText(SignUpLogin.this, "Succes",
                                    FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();

                        }else {
                            FancyToast.makeText(SignUpLogin.this, e.getMessage(),
                                    FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtInName.getText().toString(),
                        edtInPwd.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){
                            FancyToast.makeText(SignUpLogin.this, user.get("username") + " Succes",
                                    FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();


                        }else {
                            FancyToast.makeText(SignUpLogin.this, e.getMessage(),
                                    FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }

                    }
                });


            }
        });



    }
}
