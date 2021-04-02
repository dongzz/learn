# 数据库读写分离

## 主库
    写，删除
    url: localhost:3306

## 从库
    读
    url: localhost:3307

## 命令
-   show master status;
-   show slave status;
-   stop slave;
-   start slave;
-   change master to master_log_file='mysql-bin.000001',master_log_pos=156;