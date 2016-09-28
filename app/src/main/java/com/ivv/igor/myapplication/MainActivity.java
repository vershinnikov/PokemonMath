package com.ivv.igor.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivv.igor.myapplication.data.PokDb;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv=(ImageView) findViewById(R.id.iV);

        iv.setImageBitmap(new PokBitmap(45,PokBitmap.QUALITY_ICON,this).getBitmap());
        TextView tw=(TextView) findViewById(R.id.textView);

        tw.setText("Pidgey");
    }

    @Override
    protected void onStart() {
        PokDb mDbHelper = new PokDb(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor c= db.query("CpM",new String[]{"_ID","CpM"},"_ID = ?",new String[]{"45"},null,null,null);
        c.moveToFirst();
        double cpm=c.getDouble(c.getColumnIndex("CpM"));
        TextView tw=(TextView) findViewById(R.id.textView);

        tw.setText(cpm+"");
        super.onStart();
    }
}
