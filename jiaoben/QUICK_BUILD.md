# 🚀 快速构建APK指南

## 最简单的方法（推荐）

### 方法1：使用GitHub Actions（无需本地环境）

1. **上传到GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/yourusername/douyin-automation.git
   git push -u origin main
   ```

2. **自动构建**
   - 推送代码后，GitHub Actions会自动构建
   - 在Actions标签页下载APK文件

### 方法2：使用Docker（推荐本地构建）

1. **安装Docker Desktop**
   - 下载：https://www.docker.com/products/docker-desktop

2. **运行构建脚本**
   ```bash
   # Windows
   build-docker.bat
   
   # Linux/Mac
   docker build -t douyin-builder .
   docker run --rm -v $(pwd):/app douyin-builder
   ```

### 方法3：使用在线构建服务

1. **GitLab CI/CD**
   - 上传到GitLab
   - 自动构建APK

2. **Bitbucket Pipelines**
   - 上传到Bitbucket
   - 配置构建管道

## 手动构建（需要Android SDK）

### 前提条件
- Java JDK 17+
- Android SDK
- Gradle

### 构建步骤
```bash
# 1. 设置环境变量
export ANDROID_HOME=/path/to/android/sdk

# 2. 构建APK
./gradlew assembleDebug

# 3. 查找APK文件
# 位置：app/build/outputs/apk/debug/app-debug.apk
```

## 📱 安装APK

### 方法1：ADB安装
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### 方法2：直接安装
- 将APK传输到手机
- 在手机上点击APK文件安装
- 允许安装未知来源应用

## 🔧 故障排除

### 常见问题
1. **构建失败**
   - 检查网络连接
   - 确认依赖下载成功

2. **APK无法安装**
   - 检查Android版本兼容性
   - 确认已允许未知来源安装

3. **应用崩溃**
   - 检查权限设置
   - 查看应用日志

## 📞 获取帮助

如果遇到问题：
1. 查看构建日志
2. 检查错误信息
3. 确认环境配置
4. 参考完整构建指南 