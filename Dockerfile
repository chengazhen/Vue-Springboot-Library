# 使用官方的 OpenJDK 作为基础镜像
FROM openjdk:11-jre-slim

# 设置维护者信息（可选）
LABEL maintainer="ccz"

# 将项目的 jar 文件复制到容器中
ARG JAR_FILE=SpringBoot/target/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 暴露应用程序运行的端口（根据你的 Spring Boot 项目配置）
EXPOSE 9090

# 运行 jar 文件来启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "/app.jar"]