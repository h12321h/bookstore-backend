# 使用基础镜像
FROM openjdk:17

# 设置工作目录
WORKDIR /app

# 将整个 target 目录复制到容器中
COPY target/*.jar app.jar

# 暴露应用端口
EXPOSE 8761

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]
