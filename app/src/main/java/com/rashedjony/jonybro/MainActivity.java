package com.rashedjony.jonybro;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rashedjony.jonybro.R;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {




    // init variable here
    WebView webView;
    LinearLayout layNonet;
    LinearLayout banner_container;
    private AdView adview;
    ProgressBar progressBar;
    RelativeLayout layRoot;
    SwipeRefreshLayout swipeRefreshLayout;
    BottomNavigationView bottom_Navigation;
    String USER_AGENT_ = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        banner_container = findViewById(R.id.banner_container);


        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        progressBar = findViewById(R.id.progressBar);
        layRoot = findViewById(R.id.layRoot);
        layNonet = findViewById(R.id.layNonet);
        bottom_Navigation = findViewById(R.id.bottom_Navigation);




        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                webView.reload();



            }
        });







        //Creating webView programitcally which supports media player and js automatically like a browser
        webView = new WebView(MainActivity.this);
        webView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        webView.setFitsSystemWindows(false); // your preferences
        webView.setVerticalScrollBarEnabled(false); // your preferences
        webView.setPadding(15,15,15,15); // your preferences
        layRoot.addView(webView);

        // Enabling some setting so that browser can work properly
        webView.getSettings().setUserAgentString(USER_AGENT_);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new HelloWebViewClient());
        webView.setWebChromeClient(new ChromeClient());
        webView.getSettings().setDomStorageEnabled(true);


        webView.loadUrl(getString(R.string.website_link));


        bottom_Navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){

                    case R.id.home:
                  Toast.makeText(MainActivity.this,"HOME",Toast.LENGTH_SHORT).show();
                  break;

                    case R.id.contact:
                        Toast.makeText(MainActivity.this,"ORDER",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.faqs:
                        Toast.makeText(MainActivity.this,"POS",Toast.LENGTH_SHORT).show();
                        break;


                }


                if (item.getItemId()== R.id.home){
                    webView.getSettings().setUserAgentString(USER_AGENT_);
                    webView.getSettings().setLoadsImagesAutomatically(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setAllowFileAccess(true);
                    webView.getSettings().setLoadWithOverviewMode(true);
                    webView.getSettings().setUseWideViewPort(true);
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                    webView.setWebViewClient(new HelloWebViewClient());
                    webView.setWebChromeClient(new ChromeClient());
                    webView.getSettings().setDomStorageEnabled(true);



                    webView.loadUrl(getString(R.string.home_link));



                }

                else if (item.getItemId()==R.id.contact){

                    webView.getSettings().setUserAgentString(USER_AGENT_);
                    webView.getSettings().setLoadsImagesAutomatically(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setAllowFileAccess(true);
                    webView.getSettings().setLoadWithOverviewMode(true);
                    webView.getSettings().setUseWideViewPort(true);
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                    webView.setWebViewClient(new HelloWebViewClient());
                    webView.setWebChromeClient(new ChromeClient());
                    webView.getSettings().setDomStorageEnabled(true);


                    webView.loadUrl(getString(R.string.contact_link));

                }


                else if (item.getItemId()==R.id.faqs){
                    webView.getSettings().setUserAgentString(USER_AGENT_);
                    webView.getSettings().setLoadsImagesAutomatically(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setAllowFileAccess(true);
                    webView.getSettings().setLoadWithOverviewMode(true);
                    webView.getSettings().setUseWideViewPort(true);
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                    webView.setWebViewClient(new HelloWebViewClient());
                    webView.setWebChromeClient(new ChromeClient());
                    webView.getSettings().setDomStorageEnabled(true);

                    webView.loadUrl(getString(R.string.faqs_link));


                }




                return true;
            }
        });




        if(!isNetworkAvailable(MainActivity.this)){
            webView.setVisibility(View.GONE);
            layNonet.setVisibility(View.VISIBLE);
        }else{
            webView.setVisibility(View.VISIBLE);
            layNonet.setVisibility(View.GONE);
        }



        adview = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);

        banner_container.addView(adview);
        adview.loadAd();


        //image Download
        //#############################################################################################

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                Log.d("permission","permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,1);
            }


        }




        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setMimeType(mimeType);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie",cookies);
                request.addRequestHeader("User-Agent",userAgent);
                request.setDescription("Downloading file....");
                request.setTitle(URLUtil.guessFileName(url,contentDisposition,mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,URLUtil.guessFileName(url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(),"Downloading File",Toast.LENGTH_SHORT).show();


            }
        });


        //#############################################################################################












    } // onCreate Ends here  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>





    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }

    // =================================================



    //================================================= defining custom class (HelloWebViewClient)
    private class HelloWebViewClient extends WebViewClient {



        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            swipeRefreshLayout.setRefreshing(false);

            webView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {

                    if(
                            webView.getScrollY()==0){
                        swipeRefreshLayout.setEnabled(true);

                    }else {
                        swipeRefreshLayout.setEnabled(false);


                    }
                }
            });



        }



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }
    //=================================================



    //================================================= defining custom class (ChromeClient)
    private class ChromeClient extends WebChromeClient {
        // Defining some variables


        private View mCustomView;
        private CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;
        ChromeClient() {}

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

            if(newProgress >= 100){
                // Page loading finish
                progressBar.setVisibility(View.GONE);

            }else{
                progressBar.setVisibility(View.VISIBLE);
            }
        }


        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    // Custom class ends here #############################








    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired
    private long mBackPressed;

    // When user click bakpress button this method is called
    @Override
    public void onBackPressed() {

        // When landing in home screen
        if (!webView.canGoBack()) {

            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            } else {

                Toast.makeText(getBaseContext(), "Press again to exit",
                        Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();

        } else{
            webView.goBack();
        }


    } // end of onBackpressed method

    //#############################################################################################













    //#############################################################################################


    public void downloadDialog(final String url, final String userAgent, String contentDisposition, String mimetype) {
        //getting filename from url.
        final String filename = URLUtil.guessFileName(url, contentDisposition, mimetype);
        //alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //title of alertdialog
        builder.setTitle("Downloading");
        //message of alertdialog
        builder.setMessage("We are trying to download: "+ ' ' + filename);
        //if Yes button clicks.

        builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //DownloadManager.Request created with url.

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                //cookie
                String cookie = CookieManager.getInstance().getCookie(url);
                //Add cookie and User-Agent to request
                request.addRequestHeader("Cookie", cookie);
                request.addRequestHeader("User-Agent", userAgent);
                //file scanned by MediaScannar
                request.allowScanningByMediaScanner();
                //Download is visible and its progress, after completion too.
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //DownloadManager created
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                //Saving files in Download folder
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
                //download enqued
                downloadManager.enqueue(request);


                BroadcastReceiver onComplete=new BroadcastReceiver() {
                    public void onReceive(Context ctxt, Intent intent) {
                        Toast.makeText(getApplicationContext(), "Download Complete", Toast.LENGTH_SHORT).show();
                    }
                };

                registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

            }
        });
        builder.setNegativeButton("NOT NOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cancel the dialog if Cancel clicks
                dialog.cancel();
                webView.goBack();
            }

        });
        //alertdialog shows.
        builder.show();







    }










// MainActivity ends here -------------------------------------#
}