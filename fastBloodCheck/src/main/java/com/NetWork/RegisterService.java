package com.NetWork;

import com.Register.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by crc on 2017/8/18.
 */

public class RegisterService {
    public  void register(User user)
    {
        String url="http://169.254.203.224/Mhealthy/PatientRegister";
        final HttpPost httpPost=new HttpPost(url);
        try {
            JSONObject param=new JSONObject();
            param.put("loginname",user.getUsername());
            param.put("password",user.getPassword());
            param.put("realname",user.getName());
            param.put("sex",user.getSex());
            param.put("age",user.getAge());
            param.put("phone",user.getPhone());
            param.put("height",user.getHeight());
            param.put("weight",user.getWeight());
            param.put("history",user.getHistory());
            String json=param.toString();
            StringEntity stringEntity=new StringEntity(param.toString(),"UTF-8");
            httpPost.setEntity(stringEntity);

            new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                HttpResponse response= new  DefaultHttpClient().execute(httpPost);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }).start();
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    }
}
