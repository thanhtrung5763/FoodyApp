package hcmute.edu.vn.thanh0456.foodyappv1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.ProductDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.SearchFoodDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.DBHelper;

public class ProductDAO {
    DBHelper dbHelper;
    public ProductDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<ProductDomain> getAllProductOfResByCategory(int res_id, int category_id) {
        ArrayList<ProductDomain> ds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM product INNER JOIN restaurant ON product.restaurant_id = restaurant.restaurant_id LEFT JOIN favorite ON favorite.product_id = product.product_id WHERE restaurant.restaurant_id = ? AND category_id = ?", new String[]{String.valueOf(res_id), String.valueOf(category_id)});
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            int product_id = cursor.getInt(0);
            String name = cursor.getString(3);
            double price = cursor.getDouble(4);
            String size = cursor.getString(5);
            int qty = cursor.getInt(6);
            String img = cursor.getString(7);
            String ingredients = cursor.getString(8);
            int status = cursor.getInt(9);
            String desc = cursor.getString(10);
            int time = cursor.getInt(11);
            ProductDomain productDomain = new ProductDomain(product_id, category_id, res_id, name, price, size, qty, img, ingredients, status, desc, time);
            if (!cursor.isNull(21)) {
                productDomain.setIs_favorited(!productDomain.getIs_favorited());
            }
            ds.add(productDomain);
            cursor.moveToNext();
        }
        cursor.close();
        return ds;
    }
    public ArrayList<SearchFoodDomain> getAllProductOnSearch(String search_query, ArrayList<String> session_listTypeSelected, String filter_query) {
        ArrayList<SearchFoodDomain> ds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        if (session_listTypeSelected == null) {
            cursor = db.query("product INNER JOIN restaurant ON product.restaurant_id = restaurant.restaurant_id LEFT JOIN favorite ON favorite.product_id = product.product_id", null, "(restaurant_name LIKE ? OR product_name LIKE ?)", new String[]{"%"+search_query+"%", "%"+search_query+"%"}, null, null, filter_query);
        }
        else {
            ArrayList<String> selectionArgs = new ArrayList<>(Arrays.asList("%"+search_query+"%", "%"+search_query+"%"));
            selectionArgs.addAll(session_listTypeSelected);
            cursor = db.query("product INNER JOIN restaurant ON product.restaurant_id = restaurant.restaurant_id LEFT JOIN favorite ON favorite.product_id = product.product_id", null, "(restaurant_name LIKE ? OR product_name LIKE ?) AND restaurant.type IN (?, ?, ?, ?)", selectionArgs.toArray(new String[0]), null, null, filter_query);
        }
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int product_id = cursor.getInt(0);
            int category_id = cursor.getInt(1);
            int restaurant_id = cursor.getInt(2);
            String prod_name = cursor.getString(3);
            double price = cursor.getDouble(4);
            String size = cursor.getString(5);
//            int qty = cursor.getInt(6);
            String img = cursor.getString(7);
//            String ingredients = cursor.getString(8);
//            int status = cursor.getInt(9);
            String desc = cursor.getString(10);
            int time = cursor.getInt(11);
            String res_name = cursor.getString(13);
            String res_type = cursor.getString(14);
            double res_score = cursor.getDouble(17);
            double res_rating = cursor.getDouble(18);
            SearchFoodDomain searchFoodDomain = new SearchFoodDomain(product_id, category_id, restaurant_id, prod_name, price, size, img, desc, time, res_name, res_type, res_score, res_rating);
            if (!cursor.isNull(21)) {
                searchFoodDomain.setIs_favorited(!searchFoodDomain.getIs_favorited());
            }
            ds.add(searchFoodDomain);
            cursor.moveToNext();
        }
        cursor.close();
        return ds;
    }

    public ArrayList<SearchFoodDomain> getAllProductFavorite() {
        ArrayList<SearchFoodDomain> ds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("product INNER JOIN restaurant ON product.restaurant_id = restaurant.restaurant_id INNER JOIN favorite ON favorite.product_id = product.product_id", null, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int product_id = cursor.getInt(0);
            int category_id = cursor.getInt(1);
            int restaurant_id = cursor.getInt(2);
            String prod_name = cursor.getString(3);
            double price = cursor.getDouble(4);
            String size = cursor.getString(5);
//            int qty = cursor.getInt(6);
            String img = cursor.getString(7);
//            String ingredients = cursor.getString(8);
//            int status = cursor.getInt(9);
            String desc = cursor.getString(10);
            int time = cursor.getInt(11);
            String res_name = cursor.getString(13);
            String res_type = cursor.getString(14);
            double res_score = cursor.getDouble(17);
            double res_rating = cursor.getDouble(18);
            SearchFoodDomain searchFoodDomain = new SearchFoodDomain(product_id, category_id, restaurant_id, prod_name, price, size, img, desc, time, res_name, res_type, res_score, res_rating);
            if (!cursor.isNull(21)) {
                searchFoodDomain.setIs_favorited(!searchFoodDomain.getIs_favorited());
            }
            ds.add(searchFoodDomain);
            cursor.moveToNext();
        }
        cursor.close();
        return ds;
    }
}
