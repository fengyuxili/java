package com.fxs.designpattern.template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) {

        try {
            //1.获取连接
            Connection connection = this.getConnection();

            //2.创建语句集
            PreparedStatement preparedStatement = this.createPrepareStatement(connection, sql);

            //3.执行语句集
            ResultSet rs = this.executeQuery(preparedStatement, values);

            //4.处理结果集
            List<?> result = this.parseResult(rs, rowMapper);

            //5.关闭结果集
            this.closeResultSet(rs);

            //6.关闭语句集
            this.closeStatement(preparedStatement);

            //7.关闭连接
            this.closeConnection(connection);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void closeConnection(Connection connection) throws SQLException {
        //可以改为连接池
        connection.close();
    }

    protected void closeStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    protected void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    protected List<?> parseResult(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }

    protected ResultSet executeQuery(PreparedStatement preparedStatement, Object[] values) throws SQLException {

        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i, values[i]);
        }
        return preparedStatement.executeQuery();
    }

    protected PreparedStatement createPrepareStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    protected Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
