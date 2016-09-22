package com.ivv.igor.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    String TAG="   ------------     ";

    public int ind=0;
    public int drw=R.drawable.icons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void doit(View view) {
        ImageView iv = (ImageView) findViewById(R.id.iV);
        iv.setImageBitmap(pokImage(++ind,drw));
    }
    public void doit1(View view) {
        ImageView iv = (ImageView) findViewById(R.id.iV);
        iv.setImageBitmap(pokImage(--ind,drw));
    }
    public void doit2(View view) {
        ImageView iv = (ImageView) findViewById(R.id.iV);
        switch (drw){
            case(R.drawable.icons):drw=R.drawable.icons_ls;break;
            case(R.drawable.icons_ls):drw=R.drawable.pkm_full;break;
            case(R.drawable.pkm_full):drw=R.drawable.icons_shuffle;break;
            case(R.drawable.icons_shuffle):drw=R.drawable.icons;break;
        }
        iv.setImageBitmap(pokImage(ind,drw));
    }

    public Bitmap pokImage(int index,int drwbl){
        if(index<=0 || index>151)return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap b= BitmapFactory.decodeResource(getResources(),drwbl,options);
        int w=b.getWidth();
        int h=b.getHeight();
        int pokInRow=0;
        int pokInCol=0;
        switch(drwbl){
            case(R.drawable.icons):
                pokInRow=12;
                pokInCol=13;
                break;
            case(R.drawable.icons_ls):
                pokInRow=7;
                pokInCol=22;
                break;
            case(R.drawable.pkm_full):
                pokInRow=10;
                pokInCol=20;
                break;
            case (R.drawable.icons_shuffle):
                pokInRow=7;
                pokInCol=22;
                break;
        }
        w=w/pokInRow;
        h=h/pokInCol;
        int x=(index-1)%pokInRow;
        x*=w;
        int y=(index-1)/pokInRow;
        y*=h;
        return Bitmap.createBitmap(b,x,y,w,h);
    }
}
