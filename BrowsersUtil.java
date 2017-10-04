package cn.yjxxclub.bgApi.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-10-4
 * Time: 下午5:26
 * Describe: 浏览器工具类
 */
public class BrowsersUtil {

    public static boolean isMobile(HttpServletRequest request) {
        String browser = request.getHeader("user-agent");
        return browser != null && ((browser.contains("Android") && browser.contains("Mobile")) || browser.contains("iPhone"));
    }

    /**
     * @since 5.1.1
     */
    public static String getCSSClass(HttpServletRequest request) {
        return isIPhone(request)?"iphone":"";
    }

    private static boolean isIPhone(HttpServletRequest request) {
        String browser = request.getHeader("user-agent");
        return browser != null && browser.contains("iPhone");
    }

    /**
     * 获取设备版本
     * @param request
     * @return
     */
    public static String getDeviceVersion(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String deviceStr="";
        try {
            if (userAgent.contains("Version")){
                String tmp = userAgent.substring(userAgent.lastIndexOf("Version"),
                        userAgent.lastIndexOf("Version")+12).replace("/"," ");
                return tmp;
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取设备名称
     * @param request
     * @return
     */
    public static String getDeviceName(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String deviceStr="";
        try {
            if(StringUtil.isNotEmpty(userAgent)){
                int startIndex=userAgent.indexOf("(");
                int endIndex=userAgent.indexOf(")");
                deviceStr= userAgent.substring(startIndex+1, endIndex);
                return deviceStr;
            }
        } catch (Exception e) {
        }
        return "";
    }

}