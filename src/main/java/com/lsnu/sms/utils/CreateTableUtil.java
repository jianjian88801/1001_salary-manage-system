package com.lsnu.sms.utils;

import com.lsnu.sms.model.*;

import java.lang.reflect.Field;

/**
 * 创建数据库表工具类
 *
 * @author xiao.xl 2023/2/16 22:37
 */
public class CreateTableUtil {
    
    /**
     * Java实体转mysql建表
     *
     * @param clazz 需要转的实体对象
     * @param isCreateTime 是否需要生成创建人，创建时间等信息
     * @return
     * @author xiao.xl 2023/2/16 22:38
     */
    public static <T> String createTableSql(Class<T> clazz, boolean isCreateTime) {
        //得到APIModel注解的value
        String tableName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
        Field[] fields = clazz.getDeclaredFields();
        String param = null;
        String cameCaseColumn = null;
        String underScoreCaseColumn = null;
        StringBuilder sql = new StringBuilder();
        //以下生成建表Sql
        sql.append("create table ").append(tableName.toLowerCase()).append("(\n");
        // sql.append("id VARCHAR(32) not NULL COMMENT '主键id',\n");
        String idKey = null;
        int index = 0;
        for (Field f : fields) {
            cameCaseColumn = f.getName();
            underScoreCaseColumn = cameCaseColumn;
            for (int i = 0; i < cameCaseColumn.length(); i++) {
                if (Character.isUpperCase(cameCaseColumn.charAt(i))) {
                    // 将javabean中小驼峰命名变量的“大写字母”转换为“_小写字母”
                    underScoreCaseColumn = cameCaseColumn.substring(0, i) + '_' + cameCaseColumn.substring(i, i + 1).toLowerCase() + cameCaseColumn.substring(i + 1, cameCaseColumn.length());
                }
            }
            param = f.getType().getTypeName();
            //得到ApiModelProperty 注解的value
            switch (param) {
                case "java.lang.Integer":
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("int(8) "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '" + "',\n");
                    break;
                case "java.lang.Boolean":
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("tinyint(1) "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '"  + "',\n");
                    break;
                case "java.math.BigDecimal":
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("decimal(18,2) "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '"  + "',\n");
                    break;
                case "java.util.Date":
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("datetime "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '"  + "',\n");
                    break;
                default:
                    sql.append(underScoreCaseColumn).append(" ");
                    sql.append("VARCHAR(32) "+(index!=0?"DEFAULT NULL":"NOT NULL")+ " COMMENT '" + "',\n");
            }
            if (index == 0) {
                idKey = underScoreCaseColumn;
            }
            index = index+1;
        }
        // 是否包含缺省字段
        if (isCreateTime) {
            sql.append("deleted tinyint(1) DEFAULT '0' COMMENT '删除状态，1删除 0正常',\n");
            sql.append("create_time datetime NOT NULL COMMENT '创建时间',\n");
            sql.append("creater varchar(32) NOT NULL COMMENT '创建人',\n");
            sql.append("update_time datetime NOT NULL COMMENT '更新时间',\n");
            sql.append("updater varchar(32) NOT NULL COMMENT '更新人',\n");
        }
        sql.append("PRIMARY KEY ("+idKey+")");
        //sql.delete(sql.lastIndexOf(","), sql.length());
        sql.append("\n)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='" + "';");
    
        System.out.println(sql);
        return sql.toString();
    }

    /**
     * main方法执行
     *
     * @param args
     * @return
     * @author xiao.xl 2023/2/16 22:40
     */
    public static void main(String[] args) {
        createTableSql(Attendance.class, false);
        createTableSql(AttendanceSet.class, false);
        createTableSql(Dept.class, false);
        createTableSql(Insurance.class, false);
        createTableSql(Salary.class, false);
        createTableSql(Staff.class, false);
        createTableSql(User.class, false);
        createTableSql(Wages.class, false);
    }
}