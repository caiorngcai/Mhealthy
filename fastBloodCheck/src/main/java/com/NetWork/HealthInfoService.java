package com.NetWork;

import com.Bean.HealthInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by crc on 2017/8/21.
 */

public class HealthInfoService {
    public static void sendHealthInfo(HealthInfo healthInfo)
    {
        String url="http://169.254.203.224/Mhealthy/HealthInfo";
        final HttpPost httpPost=new HttpPost(url);
        try {
            JSONObject param=new JSONObject();
            param.put("spressure",healthInfo.getSpressure());
            param.put("dpressure",healthInfo.getDpressure());
            param.put("temperature",healthInfo.getTemperature());
            param.put("bsugar",healthInfo.getBsugar());
            param.put("boxygen",healthInfo.getBoxygen());
            param.put("hearterate",healthInfo.getHearterate());
            param.put("preresult",healthInfo.getPreresult());
            param.put("loginname",healthInfo.getLoginname());
            String json=param.toString();
            StringEntity stringEntity=new StringEntity(param.toString(),"UTF-8");
            httpPost.setEntity(stringEntity);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HttpResponse response= new DefaultHttpClient().execute(httpPost);
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
