package com.ivv.igor.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Igor on 27-Sep-16.
 */

public class PokDb extends SQLiteOpenHelper implements PokData{

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "poks.db";

    public PokDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableFromArray(db,CpM,"real","CpM");
        createTableFromArray(db,exp_req,"integer","exp_req");
        createTableFromArray(db,candy,"integer","candy");
        createTableFromArray(db,stardust,"integer","startdust");
        createTableFromArray(db,types_dict,"text","types_dict");
        createTablefromMap(db,types_lookup,"type","text","pokedex","integer","types_lookup");
        createTablefromJson(db,poks_data,"poks");
    }

    public void createTableFromArray(SQLiteDatabase db,Object[] arr,String type,String name){
        String SQL_CREATE_="create table "+name+" ( _ID integer primary key, "+name+" "+type+")";
        db.execSQL(SQL_CREATE_);
        StringBuffer sql_add=new StringBuffer();
        sql_add.append("insert into "+name+" values ");
        String comm="";
        if(type=="text")comm="\"";
        for (int i = 0; i < arr.length; i++) sql_add.append("("+(i+1)+","+comm+arr[i]+comm+"),");
        sql_add.deleteCharAt(sql_add.length()-1);
        db.execSQL(sql_add.toString());
    }

    public void createTablefromMap(SQLiteDatabase db,String arr,String colName1,String colType1,String colName2,String colType2,String name){
        String SQL_CREATE_="create table "+name+" ( "+colName1+" "+colType1+" primary key, "+colName2+" "+colType2+")";
        db.execSQL(SQL_CREATE_);
        StringBuffer sql_add=new StringBuffer();
        sql_add.append("insert into "+name+" values ");
        JSONObject jsonObject;
        try{
            jsonObject=new JSONObject(arr);
            Iterator<String> iter=jsonObject.keys();
            while(iter.hasNext()){
                String key=iter.next();
                String val=jsonObject.get(key)+"";
                String tp=jsonObject.get(key).getClass().getSimpleName();
                sql_add.append("('"+key+"',"+val+"),");
            }
            sql_add.deleteCharAt(sql_add.length()-1);

        }catch (Exception e){
//            Toast.makeText(,"createTablefromMap error",Toast.LENGTH_SHORT);
            Log.e("   ....  ","createTablefromMap error");

        }
        Log.e("   ....  ",sql_add.toString());
        db.execSQL(sql_add.toString());
    }
    public void createTablefromJson(SQLiteDatabase db,String json, String name){
        try{
            StringBuffer SQL_CREATE=new StringBuffer();
            SQL_CREATE.append("create table ").append(name).append(" (");
            JSONArray jsonArr=new JSONArray(json);
            JSONObject json1=jsonArr.getJSONObject(0);
            Iterator<String> iter=json1.keys();

            while(iter.hasNext()){
                String key=iter.next();
                String val="";
                double vald=0;
                String type="";
                if(json1.get(key).getClass().getSimpleName().equals("String")){
                    val="'"+(String)json1.get(key)+"'";
                    type="text";
                }else{
                    vald= json1.get(key)+0d;
//                    val=(String)json1.get(key);
                    type="real";
                }
                Log.e("   ......   ",key+" : "+val);
                SQL_CREATE.append(key+" "+type+",");
            }
            SQL_CREATE.deleteCharAt(SQL_CREATE.length()-1).append(")");
            db.execSQL(SQL_CREATE.toString());
        }catch (Exception e){
            Log.e("   ....  ","createTablefromJson error");

        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
