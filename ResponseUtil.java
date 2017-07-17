import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-7-17
 * Time: 下午4:32
 * Describe: Response工具类
 */
public class ResponseUtil {
	/**
	 * response
	 * @param response
	 * @param o
	 * @throws Exception
	 */
	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
