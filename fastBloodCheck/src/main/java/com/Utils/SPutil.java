package com.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HY-IT on 2017/5/12.
 */

public class SPutil {

    private static SharedPreferences sp;

    /**
     * 存储boolean值的方法
     */
    public static void putBoolean(Context context,String key,boolean value)
    {
        //由于是静态方法，sp可能会随机存储在内存中
        if(sp==null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 从存储中获得boolean类型值的方法
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context,String key,boolean defValue)
    {
        if(sp==null)
        {
            sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defValue);
    }

    /**
     * 存储string类型的值到内存中
     * @param context
     * @param key
     * @param value
     */
    public  static void putString(Context context,String key,String value)
    {
        if(sp==null)
        {
            sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,value).commit();
    }

    /**
     * 从内存中获取string类型的值
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context,String key,String defValue)
    {
        if(sp==null)
        {
            sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getString(key,defValue);
    }

    /**
     * 根据key删除sp节点的方法
     * @param context 上下文环境
     * @param key 要删除节点的key
     */
    public static void  remove(Context context,String key)
    {
        if(sp==null)
        {
            sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }

    /**
     * 往sp存储int类型值的方法
     * @param context
     * @param key
     * @param value
     */
    public static void putInt(Context context,String key,int value)
    {
        if(sp!=null)
        {
            sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key,value).commit();
    }

    /**
     * 从sp中获得int类型存储值的方法
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context,String key)
    {
        if(sp!=null)
        {
            sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getInt(key,0);
    }
}
