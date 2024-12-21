# 使用基础镜像
FROM openjdk:17

# 设置工作目录
WORKDIR /app

# 将JAR文件复制到容器中
COPY target/*.jar app.jar

# 暴露应用端口
EXPOSE 8080

# 启动Spring Boot应用
ENTRYPOINT ["java", "-jar", "app.jar"]

