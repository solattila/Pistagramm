package com.solattila.pistagramm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnOkay;
    private EditText edtName, edtPS, edtPP, edtKS, edtKP;
    private TextView txtData;
    private Button btnAllData;
    private String allKickboxers;
    private Button btnTransition;

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
        txtData = findViewById(R.id.txtData);
        btnAllData = findViewById(R.id.btnAllData);
        btnTransition = findViewById(R.id.btnNextAct);

        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("1mODSIgpwA", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){
                            txtData.setText(object.get("name") + " - " + "Punch Power: "
                                    + object.get("punchpower"));
                        }
                    }
                });
            }
        });

        btnAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allKickboxers = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");

                queryAll.whereGreaterThan("punchpower", 100);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){

                                for (ParseObject kickBoxer : objects) {

                                    allKickboxers = allKickboxers + kickBoxer.get("name") + " " +
                                            kickBoxer.get("PunchSpeed") + "\n";

                                }

                                FancyToast.makeText(SignUp.this, allKickboxers,
                                        FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();

                            }else {
                                FancyToast.makeText(SignUp.this, e.getMessage(),
                                        FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            }
                        }
                    }
                });

            }
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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
