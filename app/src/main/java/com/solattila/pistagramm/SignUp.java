package com.solattila.pistagramm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnOkay;
    private EditText edtName, edtPS, edtPP, edtKS, edtKP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOkay = findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(SignUp.this);

        edtName = findViewById(R.id.edtName);
        edtPS = findViewById(R.id.edtPS);
        edtPP = findViewById(R.id.edtPP);
        edtKS = findViewById(R.id.edtKS);
        edtKP = findViewById(R.id.edtKP);


    }


    @Override
    public void onClick(View v) {

        try {


            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("PunchSpeed", Integer.parseInt(edtPS.getText().toString()));
            kickBoxer.put("punchpower", Integer.parseInt(edtPP.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKS.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(edtKP.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " saved",
                                FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(),
                                FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(),
                    FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
        }
    }
}
