package cn.sunny.house.util;

import cn.sunny.house.model.Device;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-9-16
 * Time: 下午3:03
 * Describe: HttpRequest工具类
 */
public class HttpRequestUtil {

    public static String sendGet(String url) {
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("Exception occur when send http post request!" + e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String jsonPost(String strURL, String params) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDefaultUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.connect();
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();

            int code = conn.getResponseCode();
            InputStream inStr = null;
            System.out.println("状态码:" + code);
            if (code == 200) {
                inStr = conn.getInputStream();
            } else {
                inStr = conn.getErrorStream();
            }

            // 读取响应
            int length = conn.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = inStr.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                return result;
            }
        } catch (Exception e) {
            System.out.println("Exception occur when send http post request!" + e);
        }
        return null;
    }

    private static String map2json(Map<String, Object> map) {
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();
    }

    public static void main(String[] args) {
     /*   String url = "http://ngpool.crland.com.cn:6008/mbiAdmin/user/getUserPasswordById.do?userid=tengxing&sessionId=117AD3B8C1113DFD28C2DD85BC997026&callback=successCallback";
        Map<String,Object> map = new HashedMap();
        map.put("password","d41d8cd98f00b204e9800998ecf8427e");
        System.out.println(jsonPost(url,map2json(map)));*/

        String url = "http://www.teoform.com/com.obim.mq.service/message/send/json";
        Device device = new Device();
        device.setDeviceId("1000500259bb87fc00158d000193c18a");
        device.setStatus1("1");
        device.setStatus2("1");
        device.setStatus3("1");
        device.setStatus4("1");
        device.setOnline("1");

        String send = PushUtil.dataDevicePush(device,"烟感123","烟感类");
        System.out.println(send);
        String result = jsonPost(url, send);
        System.out.println(result);
    }
}

