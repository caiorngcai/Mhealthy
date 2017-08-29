package com.NetWork;

import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by crc on 2017/7/30.
 */

public class LoginService {
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
        }
    };
    public static LoginService getInstance()
    {
        return new LoginService();
    }
        //http://172.27.131.13/Mhealthy/servlet/LoginServlet?username=tom&&password=123
    public void login(final String username, final String password) {
         String url="http://169.254.203.224/Mhealthy/servlet/LoginServlet";
         url= new StringBuilder().append(url).append("?").append("username=").append(username)
                 .append("&&password=").append(password).toString();
        final String finalUrl = url;
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //HttpGet连接对象
                    HttpGet httpGet= new HttpGet(finalUrl);
                    //取得HttpClient对象
                    HttpClient httpClient= new DefaultHttpClient();
                    //请求HttpClient,取得HttpResponse
                    HttpResponse httpResponse=httpClient.execute(httpGet);
                    if(httpResponse.getStatusLine().getStatusCode()==HttpStatus. SC_OK){
                        //取得返回的字符串
                        String resultData= EntityUtils. toString(httpResponse.getEntity(),"utf-8");
                        Message msg= new Message();
                        msg.obj= resultData;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

