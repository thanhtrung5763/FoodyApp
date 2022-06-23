package hcmute.edu.vn.thanh0456.foodyappv1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.DBHelper;

public class DiscoverFoodPlaceDAO {
    DBHelper dbHelper;

    public DiscoverFoodPlaceDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<DiscoverFoodPlaceDomain> getAll() {
        ArrayList<DiscoverFoodPlaceDomain> ds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM restaurant LEFT JOIN discount ON restaurant.discount_id = discount.discount_id", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String res_name = cursor.getString(1);
            String type = cursor.getString(2);
            String address = cursor.getString(3);
            int serve_time = cursor.getInt(4);
            double score = cursor.getDouble(5);
            int rating = cursor.getInt(6);
            String res_img = cursor.getString(7);
            String promo = "";
            if(!cursor.isNull(10))
                promo = cursor.getString(10);
            DiscoverFoodPlaceDomain discoverFoodPlaceDomain = new DiscoverFoodPlaceDomain(id, res_name, type, address, serve_time, score, rating, res_img, promo);
            ds.add(discoverFoodPlaceDomain);
            cursor.moveToNext();
        }
        return ds;
    }
}
