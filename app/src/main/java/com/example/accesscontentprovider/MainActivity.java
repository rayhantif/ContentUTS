package com.example.accesscontentprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {
    TextView resultView = null;
    CursorLoader cursorLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView= (TextView) findViewById(R.id.res);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1)
    {
         String AUTHORITY = "com.example.aplikasiuntukuts.provider";
        String TABLE_NAME = "cheeses";
        String COLUMN_NAME = "name";
        /**
         * The URI for the Cheese table.
         */
        Uri URI_CHEESE = Uri.parse(
                "content://" + AUTHORITY + "/" + TABLE_NAME);
        cursorLoader = new  CursorLoader(getApplicationContext(),
                URI_CHEESE,
                new String[]{COLUMN_NAME},
                null, null, null);
        return cursorLoader;
    }

    public void onClickDisplayNames(View view)
    {
        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor)
    {
        if (cursor != null)
        {
           String COLUMN_ID = BaseColumns._ID;
            String COLUMN_NAME = "name";
            cursor.moveToFirst();
            StringBuilder res = new StringBuilder();
            while (!cursor.isAfterLast()) {
                res.append("\n"+cursor.getString(cursor.getColumnIndex(COLUMN_ID))+ "-"+ cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                cursor.moveToNext();
            }
            resultView.setText(res);
        }
        else
        {
            Toast.makeText(getBaseContext(), "No records", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(Loader<Cursor> arg0)
    {
    }

    /*private static final int LOADER_CHEESES = 1;
    private RecyclerView list;
    private CursorLoader cursorLoader;
    private CheeseAdapter mCheeseAdapter;
    public static final String COLUMN_NAME = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        //mCheeseAdapter = new CheeseListAdapter(this);
        mCheeseAdapter=new CheeseAdapter();
        list.setAdapter(mCheeseAdapter);
        /*mCheeseViewModel=new ViewModelProvider(this).get(CheeseViewModel.class);
        mCheeseViewModel.getAllCheese().observe(this, new Observer<List<Cheese>>() {
            @Override
            public void onChanged(@Nullable final List<Cheese> Cheeses) {
                // Update the cached copy of the words in the adapter.
                mCheeseAdapter.setCheese(Cheeses);
            }
        });*/
        /*LoaderManager.getInstance(this).initLoader(LOADER_CHEESES, null, mLoaderCallbacks);
    }

    private final LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1)
                {
                    cursorLoader = new CursorLoader(this, Uri.parse("content://com.example.aplikasiuntukuts.provider/cheeses"), null, null, null, null);
                    return cursorLoader;
                }

                @Override
                public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
                    mCheeseAdapter.setCheeses(data);
                }

                @Override
                public void onLoaderReset(@NonNull Loader<Cursor> loader) {
                    mCheeseAdapter.setCheeses(null);
                }

            };

    private static class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.ViewHolder> {

        private Cursor mCursor;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (mCursor.moveToPosition(position)) {
                holder.mText.setText(mCursor.getString(
                        mCursor.getColumnIndexOrThrow("name")));
            }
        }

        @Override
        public int getItemCount() {
            return mCursor == null ? 0 : mCursor.getCount();
        }

        void setCheeses(Cursor cursor) {
            mCursor = cursor;
            notifyDataSetChanged();
        }


        static class ViewHolder extends RecyclerView.ViewHolder {

            TextView mText;

            ViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(
                        android.R.layout.simple_list_item_1, parent, false));
                mText = (TextView) itemView.findViewById(android.R.id.text1);
            }

        }

    }*/

}