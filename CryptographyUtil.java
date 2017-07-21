package online.tengxing.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 
 * @author by tengxing
 *
 */
public class CryptographyUtil {

	
	/**
	 * 加密工具
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="123";
		
		System.out.println("Md5加密后："+CryptographyUtil.md5(password, "spring"));
	}
}
