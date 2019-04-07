package cn.edu.buct.global;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TransAndSend {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat snum1 = new SimpleDateFormat("yyyyMMddHHmmss");
    public static String post(String strURL, String params) {
        System.out.println(strURL);
        System.out.println(params);
        String result = "";

        StringBuilder sb = new StringBuilder();
        BufferedReader in = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            if (params != null) {
                byte[] writebytes = params.getBytes();
                // 设置文件长度
                //   connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = connection.getOutputStream();
                outwritestream.write(params.getBytes());
                outwritestream.flush();
                outwritestream.close();
            }
            int responseCode = connection.getResponseCode();
//            InputStream inputStream = null;


            if (responseCode == 200) {
                is = new BufferedInputStream(connection.getInputStream());
            } else {
                is = new BufferedInputStream(connection.getErrorStream());
            }
            isr = new InputStreamReader(is, "UTF-8");
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (Exception e2) {
                // 异常记录
            }
        }
        return result;
    }

    //加挂远程请求json
    public static String createAccountPasswordValidateJson(String uid,String password,String cardNo){
        Map header = new HashMap();
        header.put("MESSNO","39E37FB1F62947D8AA52E88D5CF7D9B0");
//        header.put("RQ-TIME",new Timestamp(System.currentTimeMillis()));
        Date now = new Date();
        String time = sdf.format(now);
        header.put("RQ-TIME",time);//new Timestamp(System.currentTimeMillis())
        header.put("PKG","AccountPasswordCheck");

        Map body = new HashMap();
        body.put("customerID",uid);
        body.put("password",password);
        body.put("accountNo",cardNo);

        Map map = new HashMap();
        map.put("Header",header);
        map.put("Body",body);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    //查询余额json
    public static String createGetAccountBalanceJson(String cardNo){
        Map header = new HashMap();
        header.put("MESSNO","39E37FB1F62947D8AA52E88D5CF7D9B0");
        header.put("RQ-TIME",new Timestamp(System.currentTimeMillis()));
        header.put("PKG","GetBalance");

        Map body = new HashMap();
        body.put("accountNo",cardNo);

        Map map = new HashMap();
        map.put("Header",header);
        map.put("Body",body);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }
    public  static String getsnum(){
        Date now = new Date();
        String time = snum1.format(now);
        return time;
    }
}
