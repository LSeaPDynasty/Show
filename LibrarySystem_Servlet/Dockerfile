# 使用官方的 Tomcat 镜像作为基础镜像
FROM tomcat:10.1.29

# 设置工作目录
WORKDIR /usr/local/tomcat/webapps

# 复制 WAR 文件到 Tomcat 的 webapps 目录
COPY target/LibrarySystem_Servlet.war /usr/local/tomcat/webapps/LibrarySystem_Servlet.war

# 暴露 8080 端口
EXPOSE 8199

# 启动 Tomcat
CMD ["catalina.sh", "run"]
