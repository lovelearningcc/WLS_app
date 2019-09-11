package com.wls.dmr.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pathdata";
    public String DISTACE_TABLE = "distace";
    public String ERROR_TABLE = "errorTable";

    public String PAY_TABLE = "payTable1";

    //账户信息表，插入时账号时，账号名相同则替换
    public static final String TABLE_CREATE = "create table accounts (id integer primary key, account text UNIQUE ON CONFLICT REPLACE,password text,userid text,remembered text)";
    private String distaceSql = "create table " + DISTACE_TABLE + " (id integer primary key, distance text,time text)";
    private String errorSql = "create table " + ERROR_TABLE + "(id integer primary key,errorData text)";
    //zy_add
    private String paySql = "create table " + PAY_TABLE + "(id integer primary key,username text,money text,month text)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        db = new DatabaseHelper(context, "person.db", null, 1);
        db.execSQL(TABLE_CREATE);
        db.execSQL(distaceSql);
        db.execSQL(errorSql);
        //zy_add
        db.execSQL(paySql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            onCreate(db);
        }
    }







//    public void addPerson(Person person) {
//        Log.e("SqliteHelper", "插入");
//        SQLiteDatabase db = getWritableDatabase(); // 以读写的形式打开数据库
////		db.execSQL("insert into person(name,age) values("
////				+ String.format("'%s'", person.getName()) + ","
////				+ person.getAge() + ");"); // 插入数据库
//
//        // insert into person(name,age,sex) values('liudehua',50,'man')
////		db.execSQL(
////				"insert into person(name,age,sex) values("
////				+ String.format("'%s'", person.getName()) + ","
////				+ person.getAge() + ","
////				+ String.format("'%s'", person.getSex()) +
////				");"
////		); // 插入数据库
//
//        db.execSQL(
//                "insert into person(name,age,sex) values("
//                        + String.format("'%s'", person.getName()) + ","
//                        + person.getAge() + ","
//                        + String.format("'%s'", person.getSex()) +
//                        ");"
//        ); // 插入数据库
//
//        db.close(); // 关闭数据库连接
//    }


}