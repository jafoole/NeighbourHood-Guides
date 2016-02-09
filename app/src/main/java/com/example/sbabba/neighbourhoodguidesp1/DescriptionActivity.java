package com.example.sbabba.neighbourhoodguidesp1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {


    NeighbourSQLiteOpenHelper mHelper;
    SQLiteDatabase db;
    private boolean mFabFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        mHelper = new NeighbourSQLiteOpenHelper(DescriptionActivity.this);
        Cursor cursor = mHelper.getNeighbourHoodList();

        WebView locationImage = (WebView)findViewById(R.id.locationImage);
        locationImage.getSettings().setLoadWithOverviewMode(true);
        locationImage.getSettings().setUseWideViewPort(true);
        TextView nameTextView = (TextView)findViewById(R.id.locationName);
        TextView addressTextView = (TextView)findViewById(R.id.addressView);
        TextView descriptionTextView = (TextView)findViewById(R.id.description);
        FloatingActionButton fabButton = (FloatingActionButton)findViewById(R.id.fab);



        final int id = getIntent().getIntExtra("_id", -1);


        if (id > 0){

            String location_name = NeighbourSQLiteOpenHelper.getInstance(DescriptionActivity.this).getLocationName(id);
            String address = NeighbourSQLiteOpenHelper.getInstance(DescriptionActivity.this).getAddress(id);
            String description = NeighbourSQLiteOpenHelper.getInstance(DescriptionActivity.this).getDescription(id);
            String image = NeighbourSQLiteOpenHelper.getInstance(DescriptionActivity.this).getImage(id);


            nameTextView.setText(location_name);
            addressTextView.setText(address);
            descriptionTextView.setText(description);
            locationImage.loadUrl(image);


//            mHelper.getColFavorites(id);
            if (mHelper.getColFavorites(id).equals("1")){
                fabButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                mFabFlag = true;
            }
            else {
                fabButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                mFabFlag = false;
            };

//            if (mHelper.getColFavorites(id).equals("0")){
//                fabButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
//            }
//            else {
//                fabButton.setImageResource(R.drawable.ic_favorite_black_24dp);
//            }

        }





        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mFabFlag = !mFabFlag;
//                if (mHelper.getColFavorites(id).equals("1")) {
//                    fab.setImageResource(R.drawable.ic_favorite_border_white_24dp);
//                    Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
//                    SQLiteDatabase db = mHelper.getWritableDatabase();
//                    db.execSQL("UPDATE NeighbourHood_Table SET FAVORITES = 1 WHERE _id = " + id);
//
//                } else if (mHelper.getColFavorites(id).equals("0")) {
//                    fab.setImageResource(R.drawable.ic_favorite_black_24dp);
//                    Snackbar.make(view, "Removed from favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
//                    SQLiteDatabase db = mHelper.getWritableDatabase();
//                    db.execSQL("UPDATE NeighbourHood_Table SET FAVORITES = 0 WHERE _id = " + id);
//                } else if (mFabFlag == true) {
//                    fab.setImageResource(R.drawable.ic_favorite_black_24dp);
//                    Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
//                    SQLiteDatabase db = mHelper.getWritableDatabase();
//                    db.execSQL("UPDATE NeighbourHood_Table SET FAVORITES = 1 WHERE _id = " + id);
//                } else {
//                    SQLiteDatabase db = mHelper.getWritableDatabase();
//                    db.execSQL("UPDATE NeighbourHood_Table SET FAVORITES = 0 WHERE _id = " + id);
//                    fab.setImageResource(R.drawable.ic_favorite_border_white_24dp);
//                    Snackbar.make(view, "Removed from favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
//                }
                ;


                mFabFlag = !mFabFlag;
                if (mFabFlag == true) {
//                    Intent intent = new Intent(DescriptionActivity.this, SearchActivity.class);
                    fab.setImageResource(R.drawable.ic_favorite_black_24dp);
                    Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    SQLiteDatabase db = mHelper.getWritableDatabase();
                    db.execSQL("UPDATE NeighbourHood_Table SET FAVORITES = 1 WHERE _id = " + id);

                } else {
                    SQLiteDatabase db = mHelper.getWritableDatabase();
                    db.execSQL("UPDATE NeighbourHood_Table SET FAVORITES = 0 WHERE _id = " + id);
                    fab.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                    Snackbar.make(view, "Removed from favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }

            }
        });

            };

}


