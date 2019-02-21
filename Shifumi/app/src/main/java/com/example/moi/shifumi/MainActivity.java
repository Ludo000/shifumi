package com.example.moi.shifumi;

import android.Manifest;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonCreePartie;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejoindre;

public class MainActivity extends AppCompatActivity {


    Button btnCreePartie;
    Button btnRejoindre;

    EcouteurBoutonCreePartie ecouteurBoutonCreePartie;
    EcouteurBoutonRejoindre ecouteurBoutonRejoindre;

    EditText edtNamePlayer;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.checkLocationPermission();
        btnCreePartie = findViewById(R.id.btnCreePartie);
        btnRejoindre = findViewById(R.id.btnRejoindre);
        edtNamePlayer = findViewById(R.id.edtNamePlayer);



        this.ecouteurBoutonCreePartie = new EcouteurBoutonCreePartie(this,edtNamePlayer);
        btnCreePartie.setOnClickListener(this.ecouteurBoutonCreePartie);

        this.ecouteurBoutonRejoindre = new EcouteurBoutonRejoindre(this, edtNamePlayer);
        btnRejoindre.setOnClickListener(this.ecouteurBoutonRejoindre);

    }


    /*----------Request Location Permission -------------*/
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle(R.string.app_name )
                        .setMessage(R.string.app_name )
                        .setPositiveButton(R.string.app_name, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                    }

                } else {
                }
                return;
            }
        }
    }

}
