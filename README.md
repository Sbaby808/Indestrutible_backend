# Indestrutible_backend
A MySQL management system backend.

## 动态数据源处理
1. 发送测试连接请求时，将数据源信息放入  [DynamicDataSource](https://github.com/Sbaby808/Indestrutible_backend/blob/master/src/main/java/com/indestructible_backend/DataSourceHelper/DynamicDataSource.java) 的 DataSourceMap 中，键为 UUID 生成。
2. 在 Response 中添加此值返回。
3. 前端每次请求需附带初次建立连接时响应中的 dataSource 值，并在 Controller 中检查 dataSource 参数是否有效。
4. 在每次请求中将 dataSource 值放入 [DataSourceContextHolder](https://github.com/Sbaby808/Indestrutible_backend/blob/master/src/main/java/com/indestructible_backend/DataSourceHelper/DataSourceContextHolder.java) 中，即本地线程中，供 [DynamicDataSource](https://github.com/Sbaby808/Indestrutible_backend/blob/master/src/main/java/com/indestructible_backend/DataSourceHelper/DynamicDataSource.java) 的 determineCurrentLookupKey()方法调用，
实现数据源切换。

## 遗留问题：
1. 需要事先定义好正确的数据源信息（第一次发送请求会触发建立默认连接）
2. 在删除表时，为防止SQL注入加入转移符 ` 或 ' 会报错。
```java
@Update("DROP TABLE `${dbName}.${tbName}`")
void dropTable(@Param("dbName") String dbName, @Param("tbName") String tbName);

报错 : Access denied for user 'guest'@'%' to database 'information_schema'
原因：默认连接数据库是 information_schema ，但为什么没识别到 . 。

@Update("DROP TABLE '${dbName}.${tbName}'")
void dropTable(@Param("dbName") String dbName, @Param("tbName") String tbName);

报错 : You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''test.testTable'' at line 1
如果加 ` 对单引号进行转义的话，则又会报上边一样的错误。

```

## 备注：
1. 动态数据源信息保存在 ThreadLocal 中，而每次请求新建一个线程，无法实现共享。
