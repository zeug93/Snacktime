package com.herprogramacion.crmleads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.io.File;

import static android.R.attr.data;


/**
 * Screen para el manejo de los leads en la app CRM
 */
public class LeadsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    static FirebaseAuth mFirebaseAuth;
    static FirebaseUser mFirebaseUser;
    static GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        }
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        LeadsFragment leadsFragment = (LeadsFragment)
                getSupportFragmentManager().findFragmentById(R.id.leads_container);

        //cosas de firebase

        //termina cosas de firebase

        if (leadsFragment == null) {
            leadsFragment = LeadsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.leads_container, leadsFragment)
                    .commit();
        }

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
