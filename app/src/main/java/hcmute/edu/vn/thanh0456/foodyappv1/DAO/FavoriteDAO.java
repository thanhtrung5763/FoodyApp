package hcmute.edu.vn.thanh0456.foodyappv1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CuisineDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.FavoriteDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.ProductDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.DBHelper;

public class FavoriteDAO {
    DBHelper dbHelper;
    public FavoriteDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(String cus_id, String prod_id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_id", cus_id);
        contentValues.put("product_id", prod_id);
        long fav_id = db.insert("favorite", null, contentValues);

        return (int) fav_id;
    }
    public void delete(String cus_id, String prod_id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete("favorite", "customer_id = ? AND product_id = ?", new String[]{cus_id, prod_id});
    }
}
