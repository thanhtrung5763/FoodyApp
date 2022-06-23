package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteAssetHelper {
    public static final String DBNAME = "foody_db.db"; //<<<< must be same as file name
    String DBPATH = ""; //<<<< must be same as file name
    public static final int DBVERSION = 3;
    private final Context mContext;
    public DBHelper(Context context) {
        super(context,DBNAME,null,DBVERSION);
        this.mContext = context;
        this.DBPATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }
    public void QueryData(String query) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
    }

    public Cursor GetData(String query) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query, null);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

    }
    private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DBNAME);
        String outFileName = DBPATH + DBNAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    public Boolean insert(String table, ContentValues contentValues) {
        SQLiteDatabase mDatabase = this.getWritableDatabase();
        long result = mDatabase.insert(table, null, contentValues);
        return result != -1;
    }
    public Boolean checkUsername(String username) {
        SQLiteDatabase mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM customer WHERE username = ?", new String[]{username});
        if(cursor.getCount() > 0) {
            return false;
        }
        else
            return true;
    }
    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM customer WHERE username = ?  and password = ?", new String[]{username, password});
        if(cursor.getCount() > 0) {
            return true;
        }
        else
            return false;
    }
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase mDatabase = this.getReadableDatabase();
        return mDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}
