package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;

public class Session {
    private Context mcontext;

    public Session(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void storeSession(String key, CustomerDomain customerDomain) {
        SharedPreferences pref = mcontext.getSharedPreferences("MyFile", Context.MODE_PRIVATE);


        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(customerDomain);
        editor.putString(key, json);
        editor.commit();
    }

    public CustomerDomain getSession(String key) {
//http://androidexample.com/Android_SharedPreferences_Basics/index.php?view=article_discription&aid=126&aaid=146
        SharedPreferences pref = mcontext.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = pref.getString(key, null);
        CustomerDomain customerDomain = gson.fromJson(json, CustomerDomain.class);
        return customerDomain;
    }

    public void storeSessionFilter(String key, ArrayList<String> stringArrayList) {
        Set<String> stringSet = new HashSet<>(stringArrayList);
        SharedPreferences pref = mcontext.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, stringSet);
        editor.apply();
    }
    public ArrayList<String> getSessionFilter(String key) {
        SharedPreferences pref = mcontext.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        Set<String> stringSet = pref.getStringSet(key, null);
        if (stringSet == null)
            return null;
        return  new ArrayList<>(stringSet);
    }
}
