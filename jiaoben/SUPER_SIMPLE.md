# 🎯 超级简单的APK生成方法

## 只需要3步！

### 第1步：上传到GitHub
1. 注册GitHub账号
2. 创建新仓库
3. 上传所有文件

### 第2步：等待自动构建
- GitHub会自动开始构建
- 等待5-10分钟

### 第3步：下载APK
- 点击Actions标签
- 下载生成的APK文件

## 详细步骤

### 1. 创建GitHub仓库
```bash
# 在GitHub网站上：
# 1. 点击 "New repository"
# 2. 输入仓库名：douyin-automation
# 3. 选择 "Public"
# 4. 点击 "Create repository"
```

### 2. 上传代码
```bash
# 在您的电脑上：
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/您的用户名/douyin-automation.git
git push -u origin main
```

### 3. 查看构建结果
- 点击仓库页面的 "Actions" 标签
- 等待构建完成（绿色勾号）
- 点击构建结果
- 下载 "douyin-automation-apk" 文件

## 如果不会用Git？

### 方法1：直接上传文件
1. 在GitHub仓库页面点击 "uploading an existing file"
2. 拖拽所有文件到上传区域
3. 点击 "Commit changes"

### 方法2：使用GitHub Desktop
1. 下载GitHub Desktop
2. 登录GitHub账号
3. 创建新仓库
4. 拖拽文件到仓库
5. 点击 "Commit to main"

## 安装APK

### 在手机上：
1. 下载APK文件
2. 点击APK文件
3. 允许安装未知来源应用
4. 完成安装

### 使用ADB：
```bash
adb install douyin-automation-apk.apk
```

## 使用应用

1. 打开应用
2. 点击"启用无障碍服务"
3. 在系统设置中开启无障碍服务
4. 输入抖音号，开始自动化

## 常见问题

### Q: 构建失败怎么办？
A: 检查GitHub Actions日志，通常是因为网络问题，重试即可

### Q: APK无法安装？
A: 确保手机允许安装未知来源应用

### Q: 应用无法运行？
A: 检查是否已启用无障碍服务权限

## 就是这么简单！

不需要安装任何开发工具，不需要配置环境，只需要上传代码就能自动生成APK！ 