# Indestrutible_backend
A MySQL management system backend.

## 遗留问题：
1. 需要事先定义好正确的数据源信息（第一次发送请求会触发建立默认连接）

## 备注：
1. 动态数据源信息保存在 ThreadLocal 中，而每次请求新建一个线程，无法实现共享。
