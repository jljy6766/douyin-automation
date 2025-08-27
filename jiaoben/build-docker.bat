@echo off
echo 使用Docker构建Android APK...

REM 检查Docker是否安装
docker --version >nul 2>&1
if errorlevel 1 (
    echo 错误: 未找到Docker，请先安装Docker Desktop
    pause
    exit /b 1
)

REM 构建Docker镜像
echo 构建Docker镜像...
docker build -t douyin-automation-builder .

if errorlevel 1 (
    echo Docker镜像构建失败！
    pause
    exit /b 1
)

REM 运行Docker容器构建APK
echo 开始构建APK...
docker run --rm -v %cd%:/app douyin-automation-builder

if errorlevel 1 (
    echo APK构建失败！
    pause
    exit /b 1
)

echo 构建成功！
echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk

pause 