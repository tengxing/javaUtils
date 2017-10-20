package cn.yjxxclub.dubbo.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-10-19
 * Time: 下午5:44
 * Describe: Bean获取 工具类
 */
@Configuration
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    public  void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public  static Object getBean(Class classname) {
        try{
            Object _restTemplate = applicationContext.getBean(classname);
            return _restTemplate;
        }catch(Exception e){
            return "";
        }
    }


    public static void setApplicationContext1(ApplicationContext context) {
        applicationContext = context;
    }
}
