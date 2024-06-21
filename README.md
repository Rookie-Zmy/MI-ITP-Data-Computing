# 云盘系统

## Docker镜像运行

### 1. Mysql准备

1. 在 Docker 中下载一个 MySQL 镜像并运行：

    在命令行中依次输入以下命令：

    ```sh
    docker pull mysql:latest
    docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD="123456" -d mysql:latest
    ```

2. 在 MySQL 镜像中建立需要的表：

    在命令行中输入：

    ```sh
    docker ps
    ```

    找到 `mysql:latest` 镜像的 ID 和 Name 并记录下来。

    然后在命令行输入：

    ```sh
    docker exec -it <记录的id> /bin/bash
    mysql -uroot -p
    ```

    输入密码 `123456` 进入 MySQL 服务。

    建立数据库：

    ```sql
    CREATE DATABASE pandown
      CHARACTER SET utf8mb4
      COLLATE utf8mb4_general_ci;
    ```

    建立表：

    ```sql
    CREATE TABLE file (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      link VARCHAR(100) NOT NULL
    );
    ```

### 2. 运行云盘系统

将 `ly_pandown` 代码下载后，在 IDEA 中作为一个项目打开，在 `target` 目录下有一个 `Dockerfile` 文件。

- `mysql_name` 表示在第一步中记录的 MySQL 的 Name
- `service_path` 表示需要挂载到宿主机的目录（服务器的文件仓库）

在此目录下打开命令行，输入指令：

```sh
docker run -d --name pandown --link <mysql_name>:mysql -p 8080:8080 -v <service_path>:/var/lib/postgres pandown:01
```

### 3.访问云盘系统

在本地浏览器打开 localhost:8080 即可查看 Pandown 云盘系统的功能。

### 注意

在 Windows 中实现该代码，需要开启本地的 3306 端口，用于数据库的连接。开启方法参考链接：https://blog.csdn.net/weixin_43296313/article/details/127996612

### Dockerfile
FROM openjdk:latest

COPY pandown-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
    
    
    
