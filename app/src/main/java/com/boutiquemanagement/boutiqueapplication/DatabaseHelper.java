package com.boutiquemanagement.boutiqueapplication;
import  android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.net.URI;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "BoutiqueManagement";
    private static final int DB_VERSION = 1;
    DatabaseHelper sqLiteDatabase;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("TIS IS STARTING", "1");
        String register = "CREATE TABLE registration(id INTEGER PRIMARY KEY AUTOINCREMENT, first_name VARCHAR(100), last_name VARCHAR(100), email VARCHAR(100) UNIQUE, password VARCHAR(100) UNIQUE, phone_number VARCHAR(11) UNIQUE);";
        sqLiteDatabase.execSQL(register);
        String category = "CREATE TABLE category(id INTEGER PRIMARY KEY AUTOINCREMENT , name varchar(100) ); ";
        sqLiteDatabase.execSQL(category);
        String sindh = "CREATE TABLE sindh(sid INTEGER PRIMARY KEY  AUTOINCREMENT, sname varchar(100), sprice INTEGER, scategory varchar(100), cname varchar(100),simage blob); ";
        sqLiteDatabase.execSQL(sindh);
        String punjab = "CREATE TABLE punjab(pid INTEGER PRIMARY KEY  AUTOINCREMENT, pname varchar(100), pprice INTEGER, pcategory varchar(100), cname varchar(100),pimage blob); ";
        sqLiteDatabase.execSQL(punjab);
        String balouchistan = "CREATE TABLE balouchistan(bid INTEGER PRIMARY KEY  AUTOINCREMENT, bname varchar(100), bprice INTEGER, bcategory varchar(100), cname varchar(100),bimage blob); ";
        sqLiteDatabase.execSQL(balouchistan);
        String kpk = "CREATE TABLE kpk(kid INTEGER PRIMARY KEY  AUTOINCREMENT, kname varchar(100), kprice INTEGER, kcategory varchar(100), cname varchar(100),kimage blob); ";
        sqLiteDatabase.execSQL(kpk);
        String gilgit = "CREATE TABLE gilgit(gid INTEGER PRIMARY KEY  AUTOINCREMENT, gname varchar(100), gprice INTEGER, gcategory varchar(100), cname varchar(100),gimage blob);";
        sqLiteDatabase.execSQL(gilgit);
        String orders = "CREATE TABLE orders(oid INTEGER PRIMARY KEY  AUTOINCREMENT, mid INTEGER,bill INTEGER,  details varchar(1000), address varchar(200), status varchar(200) default 'Prending' NOT NULL, FOREIGN KEY(mid) REFERENCES registration(id));";
        sqLiteDatabase.execSQL (orders);
        String feedback = "CREATE TABLE feedback(id INTEGER PRIMARY KEY AUTOINCREMENT, first_name VARCHAR(100), msgg VARCHAR(10000), email VARCHAR(1000),  feedbackType VARCHAR(100));";
        sqLiteDatabase.execSQL(feedback);
        String contact = "CREATE TABLE contact(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100), email VARCHAR(10000), phone VARCHAR(1000) , msg VARCHAR(1000));";
        sqLiteDatabase.execSQL(contact);
    }

    public boolean addCategory(String name) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        db.insert("category", null, contentValues);
        db.close();
        return true;
    }
    public boolean feedbackRegister(String first_name, String email, String msg, String feedType){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("msgg", msg);
        contentValues.put("email", email);
        contentValues.put("feedbackType", feedType);
        sqLiteDatabase.insert("feedback", null, contentValues);
        sqLiteDatabase.close();
        return true;
    }
    public boolean contact1(String name, String email, String msg, String phone){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("msg", msg);
        contentValues.put("phone", phone);
        sqLiteDatabase.insert("contact", null, contentValues);
        sqLiteDatabase.close();
        return true;
    }
//user registeration
    public boolean userRegister(String first_name, String last_name, String email, String password, String phone_number){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("phone_number", phone_number);
        sqLiteDatabase.insert("registration", null, contentValues);
        sqLiteDatabase.close();
        return true;
    }
 //order add
 public boolean addorder(Integer id, Integer bill,String detail, String address, String status){
     SQLiteDatabase sqLiteDatabase = getWritableDatabase();
     ContentValues contentValues = new ContentValues();
     contentValues.put("mid", id);
     contentValues.put("bill", bill);
     contentValues.put("details", detail);
     contentValues.put("address", address);
     contentValues.put("status", status);
     sqLiteDatabase.insert("orders", null, contentValues);
     sqLiteDatabase.close();
     return true;
 }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String register = "DROP TABLE if EXISTS registration";
        sqLiteDatabase.execSQL(register);
        onCreate(sqLiteDatabase);
        String category = "DROP TABLE IF EXISTS category";
        sqLiteDatabase.execSQL(category);
        onCreate(sqLiteDatabase);
        String sindh = "DROP TABLE IF EXISTS sindh";
        sqLiteDatabase.execSQL(sindh);
        onCreate(sqLiteDatabase);
        String punjab = "DROP TABLE IF EXISTS punjab";
        sqLiteDatabase.execSQL(punjab);
        onCreate(sqLiteDatabase);
        String balouchistan = "DROP TABLE IF EXISTS balouchistan";
        sqLiteDatabase.execSQL(balouchistan);
        onCreate(sqLiteDatabase);
        String kpk = "DROP TABLE IF EXISTS kpk";
        sqLiteDatabase.execSQL(kpk);
        onCreate(sqLiteDatabase);
        String gilgit = "DROP TABLE IF EXISTS gilgit";
        sqLiteDatabase.execSQL(gilgit);
        onCreate(sqLiteDatabase);
        String orders = "DROP TABLE IF EXISTS orders";
        sqLiteDatabase.execSQL(orders);
        onCreate(sqLiteDatabase);
        String feedback = "DROP TABLE if EXISTS feedback";
        sqLiteDatabase.execSQL(feedback);
        onCreate(sqLiteDatabase);
        String contact = "DROP TABLE if EXISTS contact";
        sqLiteDatabase.execSQL(contact);
        onCreate(sqLiteDatabase);

    }


    public String checkuserpassword(String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("registration", null, "password=?",new String[]{password}, null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return "Not Exists";
        } else {
            cursor.moveToFirst();
            String repassword = cursor.getString(cursor.getColumnIndex("password"));
            cursor.close();
            return repassword;
        }
    }

    public String checkusername(String email){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("registration", null, "email=?",new String[]{email}, null, null, null);
        if (cursor.getCount()<1){
            cursor.close();
            return "Not Exists";
        }
        else{
            cursor.moveToFirst();
            String emailnew = cursor.getString(cursor.getColumnIndex("email"));
            cursor.close();
            return emailnew;
        }
    }

    public void updateOStatus(String status, int oid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE orders SET status = ? WHERE oid = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(5, status);
        statement.bindDouble(6, (double)oid);

        statement.execute();
        database.close();
    }
////////////////////////////////////////////////////////

    public String checkid(String email){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("registration", null, "email=?",new String[]{email}, null, null, null);
        if (cursor.getCount()<1){
            cursor.close();
            return "Not Exists";
        }
        else{
            cursor.moveToFirst();
            Integer emailnew = cursor.getInt (cursor.getColumnIndex("id"));
            String eml=emailnew.toString ();
            cursor.close();
            return eml;
        }
    }








    ////////////////////////////////////////////////////////////////////
    public boolean resetPass(String email, String pass){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",pass);
        long results = db.update("registration", contentValues, "email=?",new String[]{email});
        if(results == -1){
            return false;
        } else{
            return true;
        }
    }


    public String checkprovince(String province){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("category", null, "name=?",new String[]{province}, null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return "Not Exists";
        } else {
            cursor.moveToFirst();
            String repassword = cursor.getString(cursor.getColumnIndex("name"));
            cursor.close();
            return repassword;
        }
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

        /*Sindh Category*/
    public boolean addsindh(String name, int price, String subcat,String cate, byte[] image){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sname",name );
        cv.put("sprice",price);
        cv.put("scategory", subcat);
        cv.put("cname", cate);
        cv.put("simage", image);

        db.insert("sindh", null, cv);

        db.close();
        return  true;
    }

    public void updateData(String sname, Integer sprice,String scategory, String cname, byte[] simage, int sid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE sindh SET sname = ?, sprice = ?,scategory = ?, cname= ?, simage = ? WHERE sid = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, sname);
        statement.bindDouble (2,(double) sprice);
        statement.bindString(3,scategory );
        statement.bindString(4, cname);
        statement.bindBlob(5, simage);
        statement.bindDouble(6, (double)sid);

        statement.execute();
        database.close();
    }

    public  void deleteData(int sid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM sindh WHERE sid = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)sid);

        statement.execute();
        database.close();
    }
        /*Punjab Category*/
    public boolean addpunjab(String name, int price, String subcat,String cate, byte[] image){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("pname",name );
        cv.put("pprice",price);
        cv.put("pcategory", subcat);
        cv.put("cname", cate);
        cv.put("pimage", image);

        db.insert("punjab", null, cv);

        db.close();
        return  true;
    }

    public  void deleteDataPunjab(int pid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM punjab WHERE pid = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)pid);

        statement.execute();
        database.close();
    }

    public void updateDataPunjab(String pname, Integer pprice,String pcategory, String cname, byte[] pimage, int pid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE punjab SET pname = ?, pprice = ?,pcategory = ?, cname= ?, pimage = ? WHERE pid = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, pname);
        statement.bindDouble (2,(double) pprice);
        statement.bindString(3,pcategory );
        statement.bindString(4, cname);
        statement.bindBlob(5, pimage);
        statement.bindDouble(6, (double)pid);

        statement.execute();
        database.close();
    }
///////////////////////////////////////////////////////Balouchistan
public boolean addbalouchistan(String name, int price, String subcat,String cate, byte[] image){
    SQLiteDatabase db=getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("bname",name );
    cv.put("bprice",price);
    cv.put("bcategory", subcat);
    cv.put("cname", cate);
    cv.put("bimage", image);

    db.insert("balouchistan", null, cv);

    db.close();
    return  true;
}

    public  void deleteDataBal(int bid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM  balouchistan WHERE bid = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)bid);

        statement.execute();
        database.close();
    }


    public void updateDataBal(String bname, Integer bprice,String bcategory, String cname, byte[] bimage, int bid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE  balouchistan SET bname = ?, bprice = ?,bcategory = ?, cname= ?, bimage = ? WHERE bid = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, bname);
        statement.bindDouble (2,(double) bprice);
        statement.bindString(3,bcategory );
        statement.bindString(4, cname);
        statement.bindBlob(5, bimage);
        statement.bindDouble(6, (double)bid);

        statement.execute();
        database.close();
    }

    /*Khaiber PakhtunKhwa Category*/
    public boolean addKPK(String name, int price, String subcat,String cate, byte[] image){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("kname",name );
        cv.put("kprice",price);
        cv.put("kcategory", subcat);
        cv.put("cname", cate);
        cv.put("kimage", image);

        db.insert("kpk", null, cv);

        db.close();
        return  true;
    }

    public  void deleteDataKPK(int kid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM kpk WHERE kid = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)kid);

        statement.execute();
        database.close();
    }


    public void updateDataKPK(String kname, Integer kprice,String kcategory, String cname, byte[] kimage, int kid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE kpk SET kname = ?, kprice = ?,kcategory = ?, cname= ?, kimage = ? WHERE kid = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, kname);
        statement.bindDouble (2,(double) kprice);
        statement.bindString(3,kcategory );
        statement.bindString(4, cname);
        statement.bindBlob(5, kimage);
        statement.bindDouble(6, (double)kid);

        statement.execute();
        database.close();
    }

    ///////////////////////////////////////////////////////Gilgit
    public boolean addGilgit(String name, int price, String subcat,String cate, byte[] image){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("gname",name );
        cv.put("gprice",price);
        cv.put("gcategory", subcat);
        cv.put("cname", cate);
        cv.put("gimage", image);

        db.insert("gilgit", null, cv);
        db.close();
        return  true;
    }

    public  void deleteDataGilgit(int gid) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM gilgit WHERE gid = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)gid);

        statement.execute();
        database.close();
    }


    public void updateDataGilgit(String gname, Integer gprice,String gcategory, String cname, byte[] gimage, int gid) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE gilgit SET gname = ?, gprice = ?,gcategory = ?, cname= ?, gimage = ? WHERE gid = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, gname);
        statement.bindDouble (2,(double) gprice);
        statement.bindString(3,gcategory );
        statement.bindString(4, cname);
        statement.bindBlob(5, gimage);
        statement.bindDouble(6, (double)gid);

        statement.execute();
        database.close();
    }
}



