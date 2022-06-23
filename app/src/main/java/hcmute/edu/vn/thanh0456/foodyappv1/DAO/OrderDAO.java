package hcmute.edu.vn.thanh0456.foodyappv1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain2;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.OrderDetailDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.OrderDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.ProductDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.DBHelper;

public class OrderDAO {
    DBHelper dbHelper;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public OrderDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int makeOrder(OrderDomain orderDomain, ProductDomain productDomain) throws ParseException {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("restaurant_id", orderDomain.getRestaurant_id());
        contentValues.put("customer_id", orderDomain.getCustomer_id());
        contentValues.put("order_date", simpleDateFormat.format(orderDomain.getOrder_date()));
        contentValues.put("total", orderDomain.getTotal());
        contentValues.put("order_status", orderDomain.getStatus());
        long order_id = db.insert("orders", null, contentValues);
        return (int) order_id;
    }
    public void makeOrderDetail(OrderDomain orderDomain, ProductDomain productDomain) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id", orderDomain.getOrder_id());
        contentValues.put("product_id", productDomain.getProduct_id());
        contentValues.put("quantity", productDomain.getQty());
        contentValues.put("price", productDomain.getPrice());
        contentValues.put("size", productDomain.getSize());
        contentValues.put("sub_total", productDomain.getTotal());

        db.insert("order_detail", null, contentValues);

    }
    public OrderDomain getOrderPending(String res_id, String cus_id) throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("orders", null, "restaurant_id = ? AND customer_id = ? AND order_status = ?", new String[]{res_id, cus_id, "0"}, null, null, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            int order_id = cursor.getInt(0);
            int restaurant_id = cursor.getInt(1);
            int customer_id = cursor.getInt(2);
            Date order_date = simpleDateFormat.parse(cursor.getString(3));
            double total = cursor.getDouble(4);
            int status = cursor.getInt(5);
            OrderDomain orderDomainPending = new OrderDomain(order_id, restaurant_id, customer_id, order_date, total, status);
            return orderDomainPending;
        }
        else
            return null;
    }

    public ArrayList<OrderDomain> getAllOrderBaseOnStatus(String cus_id, String status) throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<OrderDomain> ds = new ArrayList<>();
        Cursor cursor = db.query("orders INNER JOIN restaurant ON orders.restaurant_id = restaurant.restaurant_id", null, "customer_id = ? AND order_status = ?", new String[]{cus_id, status}, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int order_id = cursor.getInt(0);
            int restaurant_id = cursor.getInt(1);
            int customer_id = cursor.getInt(2);
            Date order_date = simpleDateFormat.parse(cursor.getString(3));
            double total = cursor.getDouble(4);
            String res_name = cursor.getString(7);
            String res_address = cursor.getString(9);
            double score = cursor.getDouble(11);
            int rating = cursor.getInt(12);
            String res_pic = cursor.getString(13);
            OrderDomain orderDomainPending = new OrderDomain(order_id, restaurant_id, customer_id, order_date, total, Integer.parseInt(status), res_name, res_address, score, rating, res_pic);
            ds.add(orderDomainPending);
            cursor.moveToNext();
        }
        return ds;

    }
    // to update total cart realtime
    public double getTotalByOrderId(int order_id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("orders", new String[]{"total"}, "order_id = ? AND order_status = ?", new String[]{String.valueOf(order_id), "0"}, null, null, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            double total = cursor.getDouble(0);
            return total;
        }
        else
            return -1;
    }
    public OrderDetailDomain getOrderDetailIfExist(int order_id, int prod_id, String size) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("order_detail", null, "order_id = ?", new String[]{String.valueOf(order_id)}, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int product_id_pending = cursor.getInt(2);
            String size_pending = cursor.getString(5);
            if(product_id_pending == prod_id && size_pending.equals(size)) {
                OrderDetailDomain orderDetailDomain;
                int order_detail_id = cursor.getInt(0);
                int qty = cursor.getInt(3);
                double price = cursor.getDouble(4);
                double sub_total = cursor.getDouble(6);
                orderDetailDomain = new OrderDetailDomain(order_detail_id, order_id, prod_id, qty, price, size, sub_total);
                return orderDetailDomain;
            }
            cursor.moveToNext();
        }
        return null;
    }
    public void updateOrderPending(OrderDomain orderDomainPending, ProductDomain productDomain) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        orderDomainPending.setTotal(orderDomainPending.getTotal() + productDomain.getTotal());
        ContentValues contentValues = new ContentValues();
        contentValues.put("total", orderDomainPending.getTotal());

        db.update("orders", contentValues, "order_id = ?", new String[]{String.valueOf(orderDomainPending.getOrder_id())});

        OrderDetailDomain orderDetailDomain = getOrderDetailIfExist(orderDomainPending.getOrder_id(), productDomain.getProduct_id(), productDomain.getSize());
        // product already exist in cart
        if(orderDetailDomain != null) {
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put("quantity", orderDetailDomain.getQty() + productDomain.getQty());
            contentValues1.put("sub_total", orderDetailDomain.getSub_total() + productDomain.getTotal());

            db.update("order_detail", contentValues1, "order_id = ? AND product_id = ? AND size = ?", new String[]{String.valueOf(orderDomainPending.getOrder_id()), String.valueOf(orderDetailDomain.getProduct_id()), String.valueOf(orderDetailDomain.getSize())});
        }
        // product not exist in cart
        else {
            makeOrderDetail(orderDomainPending, productDomain);
        }
    }
    // return total to update total cart in adaptor
    public double updateOrderPendingCart(OrderDetailDomain foodCartDomain, int qty, double priceOnOne){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        double total = getTotalByOrderId(foodCartDomain.getOrder_id());
        ContentValues contentValues = new ContentValues();
        contentValues.put("total", total + priceOnOne);

        db.update("orders", contentValues, "order_id = ?", new String[]{String.valueOf(foodCartDomain.getOrder_id())});

        OrderDetailDomain orderDetailDomain = getOrderDetailIfExist(foodCartDomain.getOrder_id(), foodCartDomain.getProduct_id(), foodCartDomain.getSize());
        // product already exist in cart
        if(orderDetailDomain != null) {
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put("quantity", qty);
            contentValues1.put("sub_total", Math.abs(priceOnOne) * qty);

            db.update("order_detail", contentValues1, "order_id = ? AND product_id = ? AND size = ?", new String[]{String.valueOf(foodCartDomain.getOrder_id()), String.valueOf(orderDetailDomain.getProduct_id()), String.valueOf(orderDetailDomain.getSize())});
        }
        return total + priceOnOne;
    }
    public ArrayList<OrderDetailDomain> getOrderDetailByOrderId(OrderDomain orderDomain) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<OrderDetailDomain> orderDetailDomains = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT order_detail_id, order_id, order_detail.product_id, order_detail.quantity, order_detail.price, order_detail.size, sub_total, product_name, product_image " +
                "FROM order_detail INNER JOIN product ON order_detail.product_id = product.product_id WHERE order_id = ?", new String[]{String.valueOf(orderDomain.getOrder_id())});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int order_detail_id = cursor.getInt(0);
            int order_id = cursor.getInt(1);
            int product_id = cursor.getInt(2);
            int qty = cursor.getInt(3);
            double price = cursor.getDouble(4);
            String size = cursor.getString(5);
            double sub_total = cursor.getDouble(6);
            String name = cursor.getString(7);
            String prod_pic = cursor.getString(8);

            OrderDetailDomain orderDetailDomain;

            orderDetailDomain = new OrderDetailDomain(order_detail_id, order_id, product_id, qty, price, size, sub_total, name, prod_pic);
            orderDetailDomains.add(orderDetailDomain);
            cursor.moveToNext();
        }
        return orderDetailDomains;
    }
    public void removeOrderDetailById(int order_detail_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("order_detail", "order_detail_id = ?", new String[]{String.valueOf(order_detail_id)});
    }
    public void removeOrder(int order_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("orders", "order_id = ?", new String[]{String.valueOf(order_id)});
    }
    public double updateOrderTotalAfterRemove(int order_id, double sub_total) {
        double new_total = getTotalByOrderId(order_id) - sub_total;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("total", new_total);
        db.update("orders", contentValues, "order_id = ?", new String[]{String.valueOf(order_id)});
        return new_total;
    }
    public void updateStatusOfOrder(String order_id, String action) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (action) {
            case "checkout":
                contentValues.put("order_status", 1);
                break;
            case "delivered":
                contentValues.put("order_status", 2);
                break;
            case "cancelled":
                contentValues.put("order_status", 3);
                break;
            default:
                break;
        }
        db.update("orders", contentValues, "order_id = ?", new String[]{String.valueOf(order_id)});
    }

}
