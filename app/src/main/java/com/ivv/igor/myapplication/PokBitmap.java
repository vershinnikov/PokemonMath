package com.ivv.igor.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class PokBitmap  {
    private Bitmap bitmap;
    private int ind=0;
    private int quality=0;
    private int drw=R.drawable.icons;
    public static final int QUALITY_HIGH=0;
    public static final int QUALITY_MEDIUM=1;
    public static final int QUALITY_LOW=2;
    public static final int QUALITY_ICON=3;



    public PokBitmap(int ind, int quality,Context context) {
        switch (quality) {
            case QUALITY_HIGH: drw=R.drawable.pkm_full;break;
            case QUALITY_MEDIUM: drw=R.drawable.icons_ls;break;
            case QUALITY_LOW: drw=R.drawable.icons;break;
            case QUALITY_ICON: drw=R.drawable.icons_shuffle;break;
        }
        this.ind = ind;
        this.quality = quality;
        bitmap=pokImage(ind,drw,context);
    }

    public Bitmap pokImage(int index,int drwbl,Context context){
        if(index<=0 || index>151)return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap b= BitmapFactory.decodeResource(context.getResources(),drwbl,options);
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getInd() {
        return ind;
    }

    public int getQuality() {
        return quality;
    }
}
