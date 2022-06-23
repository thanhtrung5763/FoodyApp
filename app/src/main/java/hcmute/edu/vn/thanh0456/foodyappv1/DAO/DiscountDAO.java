package hcmute.edu.vn.thanh0456.foodyappv1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscountDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.DBHelper;

public class DiscountDAO {
    DBHelper dbHelper;
    public DiscountDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public DiscountDomain getDiscountByResId(int res_id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT discount.discount_id, discount_name FROM restaurant LEFT JOIN discount ON restaurant.discount_id = discount.discount_id WHERE restaurant_id = ?", new String[]{String.valueOf(res_id)});

        cursor.moveToFirst();
        int discount_id = cursor.getInt(0);
        String name = cursor.getString(1);
        if(name == null)
            return null;
        DiscountDomain discountDomain = new DiscountDomain(discount_id, name);
        cursor.moveToNext();
        return discountDomain;
    }
}
