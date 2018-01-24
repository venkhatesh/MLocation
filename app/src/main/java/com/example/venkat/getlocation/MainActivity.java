package com.example.venkat.getlocation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button BuGps;
    TextView Cord;
 //   final private int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BuGps = (Button) findViewById(R.id.BuGps);
        Cord = (TextView) findViewById(R.id.Cord);

    }

    public void BuGClick(View view) {
        CheckUserPermsions();
 //       CheckPermission();
        Toast.makeText(this, "After Click", Toast.LENGTH_SHORT).show();

    }
    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
               //return ;
            }
        }

        getlocation();// init the contact list

    }
   // get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getlocation();// init the contact list
                } else {
                    // Permission Denied
                    Toast.makeText( this,"your message" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


//    void CheckPermission() {
//        if (ContextCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_CODE_ASK_PERMISSIONS);
//            Toast.makeText(this, "Checking Permission", Toast.LENGTH_SHORT).show();
//
//        } else {
//            getlocation();
//            Toast.makeText(this, "Else statement", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_CODE_ASK_PERMISSIONS: {
//                // If request is cancelled, the result arrays are empty.
//                Toast.makeText(this, "Entering Onrequestpermission", Toast.LENGTH_SHORT).show();
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "Permission on Process", Toast.LENGTH_SHORT).show();
//                    getlocation();
//
//                } else {
//
//                    Toast.makeText(this, "Denial location", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
////            break;
////            default:
////                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//
//         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    void getlocation() {
        Toast.makeText(this, "Before Getting Location", Toast.LENGTH_LONG).show();
        LocationManager loc = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = loc.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double lat = location.getLatitude();
        double log = location.getLongitude();
        Cord.setText("Lat: "+ Double.toString(lat)+ "Log"+Double.toString(log));
        Toast.makeText(this, "Getting Location", Toast.LENGTH_LONG).show();
        //Cord.setText("lat:"+String.valueOf(location.getLatitude())+"log:"+String.valueOf(location.getLongitude()));

    }
}

