package com.example.sbabba.neighbourhoodguidesp1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {


    NeighbourSQLiteOpenHelper mHelper;
    private boolean mFabFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        final FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFabFlag = !mFabFlag;
                if (mFabFlag == true){
                    Intent intent = new Intent(DescriptionActivity.this, SearchActivity.class);
                    fab.setImageResource(R.drawable.ic_favorite_black_24dp);
                    Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                else {
                    fab.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                    Snackbar.make(view, "Removed from favorites", Snackbar.LENGTH_SHORT).setAction("Action",null).show();
                }

            }
        });


        mHelper = new NeighbourSQLiteOpenHelper(DescriptionActivity.this);
        Cursor cursor = mHelper.getNeighbourHoodList();

        ImageView locationImage = (ImageView)findViewById(R.id.locationImage);
        TextView nameTextView = (TextView)findViewById(R.id.locationName);
        TextView addressTextView = (TextView)findViewById(R.id.addressView);
        TextView descriptionTextView = (TextView)findViewById(R.id.description);


        int id = getIntent().getIntExtra("_id", -1);

        if (id > 0){

            String location_name = NeighbourSQLiteOpenHelper.getInstance(DescriptionActivity.this).getLocationName(id);
            String address = NeighbourSQLiteOpenHelper.getInstance(DescriptionActivity.this).getAddress(id);
            String description = NeighbourSQLiteOpenHelper.getInstance(DescriptionActivity.this).getDescription(id);


            nameTextView.setText(location_name);
            addressTextView.setText(address);
            descriptionTextView.setText(description);




        }


    }
}
