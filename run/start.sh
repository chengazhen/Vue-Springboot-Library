#!/bin/bash

# 设置错误处理
set -e

# 定义变量
SPRING_BOOT_DIR="SpringBoot"
VUE_DIR="vue"
SPRING_BOOT_PORT=9090
VUE_PORT=8080

# 启动 Spring Boot 项目
start_spring_boot() {
    echo "Starting Spring Boot application..."
    cd "$SPRING_BOOT_DIR" || exit
    mvn spring-boot:run &
    cd ..
}

# 等待 Spring Boot 启动
wait_for_spring_boot_to_start() {
    local port="${1:-$SPRING_BOOT_PORT}"
    local max_attempts=60
    local attempt=0
    local url="http://localhost:${port}"

    echo "Waiting for Spring Boot application to start on ${url}..."

    until curl -s -f -o /dev/null -m 1 "${url}" || [ $attempt -eq $max_attempts ]; do
        printf '.'
        attempt=$((attempt + 1))
        sleep 1
    done

    if [ $attempt -eq $max_attempts ]; then
        echo -e "\nTimeout: Spring Boot application did not start within ${max_attempts} seconds."
        return 1
    else
        echo -e "\nSpring Boot application is now running on ${url}"
        return 0
    fi
}

# 启动 Vue 前端项目
start_vue() {
    echo "Starting Vue frontend application..."
    cd "$VUE_DIR" || exit
    npm run serve &
    cd ..
}

# 主函数
main() {
    # 启动 Spring Boot
    start_spring_boot
    
    # 等待 Spring Boot 启动
    if ! wait_for_spring_boot_to_start "$SPRING_BOOT_PORT"; then
        echo "Failed to start Spring Boot application. Exiting."
        exit 1
    fi
    
    # 启动 Vue
    start_vue
    
    # 等待所有后台进程完成
    wait
}

# 运行主函数
main

# 捕获 SIGINT 信号（Ctrl+C）
trap 'echo "Stopping all processes..."; kill $(jobs -p)' SIGINT

# 等待所有后台进程完成
wait