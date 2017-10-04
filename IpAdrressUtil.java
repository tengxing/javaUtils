package cn.yjxxclub.bgApi.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-10-4
 * Time: 下午3:02
 * Describe: Ip地址工具类
 */
public class IpAdrressUtil {

    /**
     * 获取真实IP
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtil.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtil.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtil.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;

        /*String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }*/
    }

    /**
     * 通过ip查ip地址
     * @param ip
     * @return
     */
    public static String getIpAddr(String ip) {
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        String jsonStr = HttpRequestUtil.sendGet(url);

        if (jsonStr != null) {
            // 处理返回的省市区信息
            System.out.println(jsonStr);
            String[] temp = jsonStr.split(",");
            if (temp.length < 3) {
                return "0";//无效IP，局域网测试
            }
            String country = "";
            String area = "";
            String region = "";
            String city = "";
            String county = "";
            String isp = "";
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    case 1:
                        country = (temp[i].split(":"))[2].replaceAll("\"", "");// 国家
                        break;
                    case 3:
                        area = (temp[i].split(":"))[1].replaceAll("\"", "");// 地区
                        break;
                    case 5:
                        region = (temp[i].split(":"))[1].replaceAll("\"", "");// 省份
                        break;
                    case 7:
                        city = (temp[i].split(":"))[1].replaceAll("\"", "");// 市区
                        break;
                    case 9:
                        county = (temp[i].split(":"))[1].replaceAll("\"", "");// 地区
                        break;
                    case 11:
                        isp = (temp[i].split(":"))[1].replaceAll("\"", "");// ISP公司
                        break;
                }
            }

            System.out.println(country + "=" + area + "=" + region + "=" + city + "=" + county + "=" + isp);

            return region+" "+city;
        }
        return null;
    }
}
