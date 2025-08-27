@echo off
echo 正在下载Gradle Wrapper JAR文件...

REM 创建目录
if not exist "gradle\wrapper" mkdir "gradle\wrapper"

REM 下载gradle-wrapper.jar
echo 下载gradle-wrapper.jar...
powershell -Command "& {Invoke-WebRequest -Uri 'https://github.com/gradle/gradle/raw/v8.4.0/gradle/wrapper/gradle-wrapper.jar' -OutFile 'gradle\wrapper\gradle-wrapper.jar'}"

if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo Gradle Wrapper JAR文件下载成功！
    echo 现在可以使用 gradlew.bat 来编译项目了
) else (
    echo 下载失败！请检查网络连接
)

pause 