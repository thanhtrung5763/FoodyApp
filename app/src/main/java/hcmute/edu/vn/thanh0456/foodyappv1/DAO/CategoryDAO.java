package hcmute.edu.vn.thanh0456.foodyappv1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain2;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.DBHelper;

public class CategoryDAO {
    DBHelper dbHelper;
    public CategoryDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<CategoryDomain2> getAllCategoryByResId(int res_id) {
        ArrayList<CategoryDomain2> ds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT product.category_id, category_name FROM product INNER JOIN product_category ON product.category_id = product_category.category_id WHERE restaurant_id = ?", new String[]{String.valueOf(res_id)});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int category_id = cursor.getInt(0);
            String category_name = cursor.getString(1);
            CategoryDomain2 categoryDomain2 = new CategoryDomain2(category_id, category_name);
            ds.add(categoryDomain2);
            cursor.moveToNext();
        }
        return ds;
    }
}
