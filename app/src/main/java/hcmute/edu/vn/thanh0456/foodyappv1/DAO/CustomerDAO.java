package hcmute.edu.vn.thanh0456.foodyappv1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain2;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.DBHelper;

public class CustomerDAO {
    DBHelper dbHelper;
    public CustomerDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public CustomerDomain get(String usn) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.query("customer", null, "username = ?", new String[]{usn}, null, null, null);
        cursor.moveToFirst();
        int customer_id = cursor.getInt(0);
        String fname = cursor.getString(1);
        String lname = cursor.getString(2);
        String email = cursor.getString(3);
        String phone = cursor.getString(4);
        String pro_pic = cursor.getString(5);
        String username = cursor.getString(6);
        String password = cursor.getString(7);
        int status = cursor.getInt(8);
        CustomerDomain customerDomain = new CustomerDomain(customer_id, fname, lname, email, phone, pro_pic, username, password, status);
        return customerDomain;
    }
}
