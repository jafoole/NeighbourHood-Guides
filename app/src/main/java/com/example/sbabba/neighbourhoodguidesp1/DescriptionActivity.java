package com.example.sbabba.neighbourhoodguidesp1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    NeighbourSQLiteOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


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
