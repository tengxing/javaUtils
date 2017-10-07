package cn.yjxxclub.bgApi.common.util;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-7-29
 * Time: 下午2:11
 * Describe: 自定义Typehandler处理 空字符串的情况
 */
public class StringTypeHandler implements TypeHandler<String> {
    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return (rs.getString(columnName) == null) ? "" : rs.getString(columnName);
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {

        return (rs.getString(columnIndex) == null) ? "" : rs.getString(columnIndex);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex)
            throws SQLException {

        return (cs.getString(columnIndex) == null) ? "" : cs.getString(columnIndex);
    }

    @Override
    public void setParameter(PreparedStatement arg0, int arg1, String arg2,
                             JdbcType arg3) throws SQLException {

    }
}
