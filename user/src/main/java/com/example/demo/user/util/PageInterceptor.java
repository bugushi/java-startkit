package com.example.demo.user.util;

import com.example.demo.user.vo.result.Paged;
import com.example.demo.user.vo.result.PagedResult;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 流程：mybatis -> RoutingStatementHandler.prepare -> Statement对象 -> 调用JDBC
 *
 * 结构: StatementHandler接口有两个实现类：
 *          RoutingStatementHandler
 *              含StatementHandler类型属性delegate，实际类型为BaseStatementHandler的三个子类之一
 *
 *          BaseStatementHandler，其有三个子类
 *              SimpleStatementHandler
 *              PreparedStatementHandler
 *              CallableStatementHandler
 *
 *              含有属性 MappedStatement mappedStatement 存储mapper.xml文件中一条sql语句配置的所有信息。
 *
 * 思路：拦截StatementHandler的prepare方法，改造sql
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // Mybatis只有在建立RoutingStatementHandler的时候是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
        // 获取拦截的对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 待执行的sql对象
        BoundSql boundSql = statementHandler.getBoundSql();
        // 判断是否查询语句
        if (!"".equals(boundSql.getSql()) && boundSql.getSql().toUpperCase().trim().startsWith("SELECT")) {
            // 获取参数
            Object params = boundSql.getParameterObject();
            // 如果查询方法中传入的参数是Paged的一个实例，则为分页查询
            if (params instanceof Paged) {
                Paged paged = (Paged) params;
                PagedResult pagedResult = new PagedResult(paged);
                Connection connection = (Connection) invocation.getArgs()[0];

                MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
                // 查询总条数
                int count = count(connection, (ParameterHandler) metaObject.getValue("delegate.parameterHandler"), boundSql);
                pagedResult.setTotal(count);

                // 拼接分页语句
                String pageSql = boundSql.getSql() + " limit " + ((paged.getPageNo() - 1) * paged.getPageSize()) + ", " + paged.getPageSize();

                metaObject.setValue("delegate.boundSql.sql", pageSql);

                return invocation.proceed();
            }
        }

        return invocation.proceed();
    }

    /**
     * 拦截器对应的封装原始对象的方法
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof RoutingStatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 查询总条数
     * @param connection
     * @param parameterHandler
     * @param boundSql
     * @return
     */
    public int count(Connection connection, ParameterHandler parameterHandler, BoundSql boundSql) {
        String countSql = "select count(0) from (" + boundSql.getSql() + ") as total";
        PreparedStatement countStmt = null;
        ResultSet rs = null;

        try {
            countStmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(countStmt);
            rs = countStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != countStmt) {
                    countStmt.close();
                }
                if (null != rs) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
