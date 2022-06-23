package hcmute.edu.vn.thanh0456.foodyappv1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain2;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CuisineDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.DBHelper;

public class RestaurantDAO {
    DBHelper dbHelper;
    public RestaurantDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<CuisineDomain> getAllType() {
        ArrayList<CuisineDomain> ds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT type FROM restaurant", null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String type = cursor.getString(0);
            CuisineDomain cuisineDomain = new CuisineDomain(type);
            ds.add(cuisineDomain);
            cursor.moveToNext();
        }
        cursor.close();
        return ds;
    }
}
