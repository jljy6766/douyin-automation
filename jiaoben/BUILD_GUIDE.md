# Android APK 构建指南

## 🚀 方法1：使用在线构建服务（推荐）

### GitHub Actions（免费）
1. 将项目上传到GitHub
2. 创建 `.github/workflows/build.yml` 文件
3. 推送代码后自动构建APK

### 其他在线服务
- **GitLab CI/CD**：免费构建
- **Bitbucket Pipelines**：免费构建
- **Travis CI**：免费构建

## 🛠️ 方法2：使用Android SDK命令行工具

### 前提条件
1. 安装Java JDK 8或更高版本
2. 下载Android SDK
3. 设置环境变量

### 环境变量设置
```bash
# Windows
set ANDROID_HOME=C:\Users\YourName\AppData\Local\Android\Sdk
set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools

# Linux/Mac
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

### 构建步骤
```bash
# 1. 清理项目
rm -rf app/build

# 2. 编译资源
aapt2 compile --dir app/src/main/res -o compiled_resources.zip

# 3. 链接资源
aapt2 link -o app-debug.apk -I $ANDROID_HOME/platforms/android-34/android.jar compiled_resources.zip

# 4. 编译Java代码
javac -cp $ANDROID_HOME/platforms/android-34/android.jar app/src/main/java/com/example/douyinautomation/*.java

# 5. 打包APK
zip -r app-debug.apk classes.dex resources.arsc
```

## 📱 方法3：使用Docker容器

### 创建Dockerfile
```dockerfile
FROM openjdk:8-jdk

# 安装Android SDK
RUN apt-get update && apt-get install -y wget unzip
RUN wget https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip
RUN unzip commandlinetools-linux-8512546_latest.zip -d /opt/android-sdk

# 设置环境变量
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# 安装Android SDK组件
RUN yes | sdkmanager --licenses
RUN sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"

# 复制项目文件
COPY . /app
WORKDIR /app

# 构建APK
CMD ["./gradlew", "assembleDebug"]
```

### 使用Docker构建
```bash
# 构建镜像
docker build -t android-builder .

# 运行构建
docker run -v $(pwd):/app android-builder
```

## 🔧 方法4：使用预构建的Gradle Wrapper

### 手动下载Gradle Wrapper
1. 访问 https://github.com/gradle/gradle/releases
2. 下载 `gradle-8.4-bin.zip`
3. 解压到项目根目录
4. 使用 `./gradle assembleDebug` 构建

### 使用Maven（替代方案）
如果项目支持Maven，可以使用：
```bash
mvn clean package
```

## 📦 方法5：使用第三方工具

### 1. Buck（Facebook的构建工具）
```bash
# 安装Buck
brew install buck  # macOS
# 或下载二进制文件

# 构建
buck build //app:app
```

### 2. Bazel（Google的构建工具）
```bash
# 安装Bazel
# 创建BUILD文件
# 构建
bazel build //app:app
```

## 🎯 推荐的简单方法

### 对于Windows用户：
1. 下载Android Studio（即使不安装IDE）
2. 只安装Android SDK
3. 使用命令行工具构建

### 对于Linux/Mac用户：
1. 使用包管理器安装Android SDK
2. 使用Docker容器构建
3. 或使用在线CI/CD服务

## 📋 构建检查清单

### 构建前检查：
- [ ] Java JDK已安装（版本8+）
- [ ] Android SDK已安装
- [ ] 环境变量已设置
- [ ] 项目依赖已配置
- [ ] 签名配置已设置（可选）

### 构建后检查：
- [ ] APK文件已生成
- [ ] APK可以正常安装
- [ ] 应用可以正常启动
- [ ] 权限已正确配置

## 🚨 常见问题解决

### 1. 找不到Android SDK
```bash
# 检查ANDROID_HOME环境变量
echo $ANDROID_HOME

# 重新设置环境变量
export ANDROID_HOME=/path/to/android/sdk
```

### 2. 编译错误
```bash
# 清理项目
./gradlew clean

# 重新构建
./gradlew assembleDebug --stacktrace
```

### 3. 依赖下载失败
```bash
# 使用国内镜像
# 在gradle.properties中添加：
# systemProp.http.proxyHost=127.0.0.1
# systemProp.http.proxyPort=7890
```

### 4. 内存不足
```bash
# 增加JVM内存
export GRADLE_OPTS="-Xmx2048m -XX:MaxPermSize=512m"
```

## 📞 获取帮助

如果遇到构建问题：
1. 检查错误日志
2. 确认环境配置
3. 尝试清理重新构建
4. 查看官方文档
5. 在社区寻求帮助 