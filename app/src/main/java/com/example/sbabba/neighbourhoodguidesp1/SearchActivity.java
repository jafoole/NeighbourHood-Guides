package com.example.sbabba.neighbourhoodguidesp1;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;



import com.example.sbabba.neighbourhoodguidesp1.setup.DBAssetHelper;


import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView mFavoritesListView;
    private CursorAdapter mCursorAdapter;
    private NeighbourSQLiteOpenHelper mHelper;
    private int mRequestCode;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(SearchActivity.this);
        dbSetup.getReadableDatabase();



        mHelper = NeighbourSQLiteOpenHelper.getInstance(SearchActivity.this);


        mFavoritesListView = (ListView)findViewById(R.id.favoritesListView);


        Cursor cursor = null;//mHelper.getFavoritesList();



        mCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{NeighbourSQLiteOpenHelper.COL_LOCATION_NAME},new int[]{android.R.id.text1},0);
        mFavoritesListView.setAdapter(mCursorAdapter);



//        handleIntent(getIntent());

        mFavoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = mCursorAdapter.getCursor();
                Intent intent = new Intent(SearchActivity.this, DescriptionActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra("_id", cursor.getInt(cursor.getColumnIndex(NeighbourSQLiteOpenHelper.COL_ID)));
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();


        ComponentName component = new ComponentName(this, SearchActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(component));


//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Cursor cursor = mHelper.searchNeighbourHoodList(query);
                mCursorAdapter.swapCursor(cursor);
                Toast.makeText(SearchActivity.this, "You searched " + query, Toast.LENGTH_SHORT).show();


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;
    }



















//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
////        handleIntent(intent);
//    }
//
//    public void handleIntent(Intent intent){
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            Cursor cursor = mHelper.searchNeighbourHoodList(query);
//            mCursorAdapter.swapCursor(cursor);
//            Toast.makeText(SearchActivity.this, "searching test", Toast.LENGTH_SHORT).show();
//
//        }
//    }
}

