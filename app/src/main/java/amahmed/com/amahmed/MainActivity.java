package amahmed.com.amahmed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener
{
    private WebView mywebview;
    private ProgressBar progressbar;
    private ImageView logo;
    private static final int Request_User_Location_code=99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M )
        {
            if(!checkpermission())
            {
                requestpermission();
            }        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M )
        {
            Check_USer_Location_PERMSIISSION();
        }
        mywebview = findViewById(R.id.website_webview);
        mywebview.setBackgroundColor(12);
        progressbar = findViewById(R.id.progressBar);

        logo = findViewById(R.id.logo);
        WebSettings mywebsettings = mywebview.getSettings();
        mywebsettings.setJavaScriptEnabled(true);

        mywebsettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mywebsettings.setLoadWithOverviewMode(true);
        mywebsettings.setUseWideViewPort(true);
        mywebsettings.setPluginState(WebSettings.PluginState.ON);
        mywebsettings.setGeolocationEnabled(true);
        mywebsettings.setLoadsImagesAutomatically(true);
        mywebview.setWebChromeClient((new WebChromeClient()));
        mywebsettings.setDomStorageEnabled(true);
        mywebsettings.setAppCacheEnabled(true);
        mywebsettings.setBuiltInZoomControls(true);
        mywebsettings.setDatabaseEnabled(true);

        mywebview.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });


//        mywebview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });

        mywebview.loadUrl("https://www.amahmed.com/ar-SA");
        mywebview.setBackgroundColor(Color.parseColor("#FFFFFF"));
        mywebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressbar.setVisibility(View.VISIBLE);
                logo.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressbar.setVisibility(View.GONE);
                logo.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(mywebview.canGoBack())
        {
            mywebview.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

    public boolean Check_USer_Location_PERMSIISSION()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_code);
            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_code);

            }
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestpermission() {
        requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 2);
    }

    private boolean checkpermission() {
        return (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PERMISSION_GRANTED);
    }
}
