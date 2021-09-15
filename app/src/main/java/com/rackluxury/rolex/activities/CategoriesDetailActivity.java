package com.rackluxury.rolex.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.rackluxury.rolex.BuildConfig;
import com.rackluxury.rolex.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import es.dmoral.toasty.Toasty;


public class CategoriesDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TextView categoriesName;
    TextView categoriesDescription;
    ImageView categoriesImage;
    private FileOutputStream outputStream;
    String shareCategoriesImageDescription;
    private Bitmap bitmap;
    private BitmapDrawable drawable;
    private static final int PERMISSION_STORAGE_CODE = 1000;
    private MotionLayout categoriesDetailLay;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_detail);
        setUpUIViewsDetailActivity();
        setTransitionDialogue();
        getInformationFromMain();
        setBitmap();
        initToolbar();
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("categoriesFirst", true);
        if (firstStart) {
            onFirst();
        }

    }

    private void setUpUIViewsDetailActivity() {
        toolbar = findViewById(R.id.toolbarCategoriesDetailActivity);
        categoriesDetailLay = findViewById(R.id.motionLayCategoriesDetail);
        categoriesName = findViewById(R.id.tvCategoriesDetailName);
        categoriesDescription = findViewById(R.id.tvCategoriesDetailDescription);
        categoriesImage = findViewById(R.id.ivCategoriesDetailImage);
        Typeface detailCategoriesDescriptionFont = Typeface.createFromAsset(CategoriesDetailActivity.this.getAssets(), "fonts/OpenSansCondensed-Light.ttf");
        categoriesDescription.setTypeface(detailCategoriesDescriptionFont);
        SlidrInterface slidr = Slidr.attach(this);

    }

    public void onFirst() {

        Snackbar snackbar = Snackbar.make(categoriesDetailLay, "Swipe Right to Dismiss", Snackbar.LENGTH_LONG)
                .setDuration(10000)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setAction("OKAY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("categoriesFirst", false);
                        editor.apply();
                    }
                })
                .setActionTextColor(Color.WHITE)
                .setTextColor(Color.WHITE);

        snackbar.show();

    }

    private void setTransitionDialogue() {
        final TransitionDialogue transitionDialogue = new TransitionDialogue(CategoriesDetailActivity.this);
        transitionDialogue.startTransitionDialogue();
        Handler handler = new Handler();
        int TRANSITION_SCREEN_TIME = 700;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                transitionDialogue.dismissDialogue();
            }
        }, TRANSITION_SCREEN_TIME);
    }

    private void getInformationFromMain() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            categoriesImage.setImageResource(mBundle.getInt("Image"));
            categoriesName.setText(mBundle.getString("Name"));
            categoriesDescription.setText(mBundle.getString("Description"));
            shareCategoriesImageDescription = categoriesDescription.getText().toString();
        }
    }

    private void setBitmap() {
        drawable = (BitmapDrawable) categoriesImage.getDrawable();
        bitmap = drawable.getBitmap();
    }


    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(categoriesName.getText().toString());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.image_menu_categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_image_categories) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permission, PERMISSION_STORAGE_CODE);

                } else {
                    downloadImage();
                }

            } else {
                downloadImage();
            }

            return true;
        } else if (item.getItemId() == R.id.share_image_categories) {

            drawable = (BitmapDrawable) categoriesImage.getDrawable();
            bitmap = drawable.getBitmap();

            try {
                File file = new File(getApplicationContext().getExternalCacheDir(), File.separator + "Watches from Rolex.png");
                FileOutputStream fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                fOut.close();

                file.setReadable(true, false);
                final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                String shareImageSub = categoriesName.getText().toString();
                intent.putExtra(Intent.EXTRA_SUBJECT, shareImageSub);
                intent.putExtra(Intent.EXTRA_TEXT, shareCategoriesImageDescription);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", file);
                intent.putExtra(Intent.EXTRA_STREAM, photoURI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setType("image/png");

                startActivity(Intent.createChooser(intent, "Share image via"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        } else if (item.getItemId() == R.id.wallpaper_image_categories) {
            setWallpaper();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                downloadImage();
            } else {
                Toasty.error(CategoriesDetailActivity.this, "Permission denied...!", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void downloadImage() {
        drawable = (BitmapDrawable)categoriesImage.getDrawable();
        bitmap = drawable.getBitmap();
        File filePath = Environment.getExternalStorageDirectory();
        File dir = new File(filePath.getAbsolutePath() + "/Watches From Rolex/");
        dir.mkdir();
        File file = new File(dir, System.currentTimeMillis() +".jpg");
        try {
            outputStream = new FileOutputStream(file);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(file));
            sendBroadcast(intent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, outputStream);
        Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setWallpaper() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setBitmap(bitmap);
            Toasty.success(CategoriesDetailActivity.this, "Wallpaper Set Successfully", Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            Toasty.error(CategoriesDetailActivity.this, "Wallpaper Not Set", Toast.LENGTH_LONG).show();


        }

    }


    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSwipeRight(CategoriesDetailActivity.this);
    }

}
