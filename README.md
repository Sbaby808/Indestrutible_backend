# Indestrutible_backend
A MySQL management system backend.

## 动态数据源处理
1. 发送测试连接请求时，将数据源信息放入  DynamicDataSource 的 DataSourceMap 中，键为 UUID 生成。
2. 在 Response 中添加此值返回。
3. 前端每次请求需附带初次建立连接时响应中的 dataSource 值，并在 Controller 中检查 dataSource 参数是否有效。
4. 在每次请求中将 dataSource 值放入 DataSourceContextHolder 中，即本地线程中，供 DynamicDataSource 的 determineCurrentLookupKey()方法调用，
实现数据源切换。

## 急需解决的问题：



## 遗留问题：
1. 需要事先定义好正确的数据源信息（第一次发送请求会触发建立默认连接）

## 备注：
1. 动态数据源信息保存在 ThreadLocal 中，而每次请求新建一个线程，无法实现共享。
