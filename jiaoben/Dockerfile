FROM openjdk:17-jdk

# 设置工作目录
WORKDIR /app

# 安装必要的工具
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    curl \
    git \
    && rm -rf /var/lib/apt/lists/*

# 下载Android SDK命令行工具
RUN wget -q https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip \
    && unzip commandlinetools-linux-8512546_latest.zip -d /opt/android-sdk \
    && rm commandlinetools-linux-8512546_latest.zip

# 设置环境变量
ENV ANDROID_HOME=/opt/android-sdk
ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# 创建必要的目录结构
RUN mkdir -p $ANDROID_HOME/cmdline-tools/latest
RUN mv $ANDROID_HOME/cmdline-tools/* $ANDROID_HOME/cmdline-tools/latest/ 2>/dev/null || true

# 接受Android SDK许可证
RUN yes | sdkmanager --licenses

# 安装Android SDK组件
RUN sdkmanager \
    "platform-tools" \
    "platforms;android-34" \
    "build-tools;34.0.0" \
    "extras;android;m2repository" \
    "extras;google;m2repository"

# 复制项目文件
COPY . .

# 给gradlew执行权限
RUN chmod +x gradlew

# 构建APK
CMD ["./gradlew", "assembleDebug"] 