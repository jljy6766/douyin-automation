@echo off
echo 开始编译抖音自动化助手...

REM 检查是否安装了Java
java -version >nul 2>&1
if errorlevel 1 (
    echo 错误: 未找到Java环境，请先安装Java JDK
    pause
    exit /b 1
)

REM 检查是否安装了Android SDK
if not exist "%ANDROID_HOME%" (
    echo 警告: 未设置ANDROID_HOME环境变量
    echo 请确保Android SDK已正确安装
)

REM 清理之前的构建
echo 清理之前的构建...
if exist "app\build" rmdir /s /q "app\build"

REM 编译应用
echo 开始编译...
call gradlew.bat assembleDebug

if errorlevel 1 (
    echo 编译失败！
    pause
    exit /b 1
)

echo 编译成功！
echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk

REM 询问是否安装到设备
set /p install="是否要安装到连接的设备？(y/n): "
if /i "%install%"=="y" (
    echo 正在安装到设备...
    adb install app\build\outputs\apk\debug\app-debug.apk
    if errorlevel 1 (
        echo 安装失败！请检查设备连接
    ) else (
        echo 安装成功！
    )
)

pause 