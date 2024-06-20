# 云盘系统

##Docker镜像运行

*1、Mysql准备
  *a、在docker中下载一个mysql镜像并运行：
    *在命令行中依次输入以下命令：
    ```javascript
      docker pull mysql:latest
      docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD="123456" -d mysql:latest
    ```
    
  b、在mysql镜像中建立需要的表：
    在命令行中输入：
      docker ps
    找到mysql:latest镜像的id和name并记录下来
    然后在命令行输入：
      docker exec -it 记录的id /bin/bash
      mysql -uroot -p
    输入密码123456进入mysql服务
    建立数据库：
      CREATE DATABASE pandown
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_general_ci;
    建立表
      CREATE TABLE file (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        link VARCHAR(100) NOT NULL
      );

2、将ly_pandown代码下载后，在idea中作为一个项目打开，在target目录下有一个Dockerfile文件
  mysql_name表示在第一步中记录的mysql的name
  service_path表示需要挂载到宿主机的目录(服务器的文件仓库)
  在此目录下打开命令行，输入指令：
    docker run -d --name pandown --link mysql_name:mysql -p 8080:8080  -v service_path:/var/lib/postgres  pandown:01

3、在本地浏览器打开localhost:8080即可查看pandown云盘系统的功能

注意：在windows中实现该代码，需要开启本地的3306端口，用于数据库的连接。开启方法参考链接：https://blog.csdn.net/weixin_43296313/article/details/127996612
    
    
    
