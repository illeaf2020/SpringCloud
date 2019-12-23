package com.chxp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Dept(entity) -> Dept(mysql) 类表关系映射
 *
 * Lombok组件：
 * @Data get、set方法
 * @AllArgsConstructor 全参构造函数
 * @NoArgsConstructor 空参构造函数
 * @Accessors(chain = true) 链式风格 -> department.setDeptno(11L).setDname("AAA").setDb_source("DB01");
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable { // Serializable 序列化

    private Long  deptno; // 主键
    private String  dname; // 部门名称
    private String  db_source; // 指定数据库(微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库)
}
