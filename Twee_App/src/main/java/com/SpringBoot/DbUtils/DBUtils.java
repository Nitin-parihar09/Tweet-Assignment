package com.SpringBoot.DbUtils;

import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DBUtils {
	private final DataSource dataSource;

    @Autowired
    public DBUtils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> executeQuery(String sql, ResultSetHandler<List<T>> handler, Object... params) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner.query(sql, handler, params);
    }

    public int executeUpdate(String sql, Object... params) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner.update(sql, params);
    }
    
    public void releaseConnection(Connection connection) {
        DbUtils.closeQuietly(connection);
    }
}
