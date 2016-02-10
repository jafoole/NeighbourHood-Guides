package com.example.sbabba.neighbourhoodguidesp1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.Rating;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionActivity extends AppCompatActivity {


    NeighbourSQLiteOpenHelper mHelper;
    SQLiteDatabase db;
    private boolean mFabFlag;
    RatingBar mRatingBar;
//    TextView mValueOfRating;

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
        mRatingBar = (RatingBar)findViewById(R.id.ratingBar);
//        mValueOfRating = (TextView)findViewById(R.id.valueOfRating);




        //This is getting the intent that was sent over from the previous page which was the SearchActivity.
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



            //Here I am checking to see if the database is a favorite or not.
            //If it is a favorite, it will add the image resource
            if (mHelper.getColFavorites(id).equals("1")){
                fabButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                mFabFlag = true;
            }
            //If it is NOT a favorite, it will NOT add the image resource
            else {
                fabButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                mFabFlag = false;
            };
            //End of getting the Favorites and setting the image resource.



            //If it has a Rating and that rating equals a certain number, the set the rating to that number.
            if (mHelper.getRating(id).equals("0.5")){
//                mRatingBar.setNumStars(5);
                mRatingBar.setRating(Float.parseFloat("0.5"));
            }
            if (mHelper.getRating(id).equals("1.0")){

                mRatingBar.setRating(Float.parseFloat("1.0"));
            }
            if (mHelper.getRating(id).equals("1.5")){

                mRatingBar.setRating(Float.parseFloat("1.5"));
            }
            if (mHelper.getRating(id).equals("2.0")){

                mRatingBar.setRating(Float.parseFloat("2.0"));
            }
            if (mHelper.getRating(id).equals("2.5")){

                mRatingBar.setRating(Float.parseFloat("2.5"));
            }
            if (mHelper.getRating(id).equals("3.0")){

                mRatingBar.setRating(Float.parseFloat("3.0"));
            }
            if (mHelper.getRating(id).equals("3.5")){

                mRatingBar.setRating(Float.parseFloat("3.5"));
            }
            if (mHelper.getRating(id).equals("4.0")){

                mRatingBar.setRating(Float.parseFloat("4.0"));
            }
            if (mHelper.getRating(id).equals("4.5")){

                mRatingBar.setRating(Float.parseFloat("4.5"));
            }
            if (mHelper.getRating(id).equals("5.0")){

                mRatingBar.setRating(Float.parseFloat("5.0"));
            }
            //End of getting the rating.


        }

        //Floating acting button which updates the database every time that a user press the heart button.
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFabFlag = !mFabFlag;
                if (mFabFlag == true) {
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


        //The rating button that updates the database every time somebody chooses a certain rating.
        //The rating is a Float number.
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                Toast.makeText(DescriptionActivity.this, "Rating Added", Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = mHelper.getWritableDatabase();
                db.execSQL("UPDATE NeighbourHood_Table SET RATING = " + rating + " WHERE _id = " + id);
//                mValueOfRating.setText(String.valueOf(rating));
            }
        });


    };

}


