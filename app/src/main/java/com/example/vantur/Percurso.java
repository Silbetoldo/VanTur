package com.example.vantur;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


public class Percurso extends AppCompatActivity {
Button btnVoltar, btnPasseios, btnVeiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percurso);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnPasseios = (Button) findViewById(R.id.btnPasseios);
        btnVeiculo = (Button) findViewById(R.id.btnVeiculo);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login   = new Intent(Percurso.this, Login.class);
                startActivity(login);
                Percurso.this.finish();

            }
        });
        btnPasseios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Passeio   = new Intent(Percurso.this, Passeios.class);
                startActivity(Passeio);
                Percurso.this.finish();

            }
        });
        btnVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Veiculo   = new Intent(Percurso.this, Veiculo.class);
                startActivity(Veiculo);
                Percurso.this.finish();

            }
        });
    }
        public void buscarInformacoesGPS(View v) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(Percurso.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                ActivityCompat.requestPermissions(Percurso.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                ActivityCompat.requestPermissions(Percurso.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
                return;
            }

            LocationManager  mLocManager  = (LocationManager) getSystemService(Percurso.this.LOCATION_SERVICE);
            LocationListener mLocListener = new Minhalocalizacao();

            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);

            if (mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                String texto = "Latitude.: " + Minhalocalizacao.latitude + "\n" +
                        "Longitude: " + Minhalocalizacao.longitude + "\n";
                Toast.makeText(Percurso.this, texto, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Percurso.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
            }

            this.mostrarGoogleMaps(Minhalocalizacao.latitude, Minhalocalizacao.longitude);
        }

             public void mostrarGoogleMaps(double latitude, double longitude) {
            WebView wv = findViewById(R.id.webv);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.loadUrl("https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude);
        }
    }





