package com.mio.example.expandablerv.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mio4kon on 16/9/22.
 */
public class GsonUtils {


    public static String getJson(Context context, String fileName) {
        StringBuilder sb = new StringBuilder();
        AssetManager am = context.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }


    public static <T> T getBeanFromAssetJson(Context context, String fileName, Class clazz) {
        String json = getJson(context, fileName);
        Gson gson = new Gson();
        return (T) gson.fromJson(json, clazz);
    }
}
