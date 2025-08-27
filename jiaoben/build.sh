#!/bin/bash

echo "开始编译抖音自动化助手..."

# 检查是否安装了Java
if ! command -v java &> /dev/null; then
    echo "错误: 未找到Java环境，请先安装Java JDK"
    exit 1
fi

# 检查是否安装了Android SDK
if [ -z "$ANDROID_HOME" ]; then
    echo "警告: 未设置ANDROID_HOME环境变量"
    echo "请确保Android SDK已正确安装"
fi

# 清理之前的构建
echo "清理之前的构建..."
if [ -d "app/build" ]; then
    rm -rf app/build
fi

# 编译应用
echo "开始编译..."
./gradlew assembleDebug

if [ $? -ne 0 ]; then
    echo "编译失败！"
    exit 1
fi

echo "编译成功！"
echo "APK文件位置: app/build/outputs/apk/debug/app-debug.apk"

# 询问是否安装到设备
read -p "是否要安装到连接的设备？(y/n): " install
if [[ $install == "y" || $install == "Y" ]]; then
    echo "正在安装到设备..."
    adb install app/build/outputs/apk/debug/app-debug.apk
    if [ $? -ne 0 ]; then
        echo "安装失败！请检查设备连接"
    else
        echo "安装成功！"
    fi
fi

echo "按任意键继续..."
read -n 1 