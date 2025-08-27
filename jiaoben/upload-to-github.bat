@echo off
echo 一键上传到GitHub并生成APK
echo ================================

REM 检查Git是否安装
git --version >nul 2>&1
if errorlevel 1 (
    echo 错误: 未找到Git，请先安装Git
    echo 下载地址: https://git-scm.com/downloads
    pause
    exit /b 1
)

REM 获取GitHub用户名
set /p github_username="请输入您的GitHub用户名: "
if "%github_username%"=="" (
    echo 错误: 用户名不能为空
    pause
    exit /b 1
)

REM 初始化Git仓库
echo 初始化Git仓库...
git init
git add .

REM 提交代码
echo 提交代码...
git commit -m "Initial commit: 抖音自动化助手"

REM 设置分支名
git branch -M main

REM 添加远程仓库
echo 添加远程仓库...
git remote add origin https://github.com/%github_username%/douyin-automation.git

REM 推送代码
echo 推送代码到GitHub...
git push -u origin main

if errorlevel 1 (
    echo.
    echo 推送失败！可能的原因：
    echo 1. GitHub用户名错误
    echo 2. 仓库不存在，请先在GitHub上创建仓库
    echo 3. 网络连接问题
    echo.
    echo 请手动创建仓库：
    echo 1. 访问 https://github.com/new
    echo 2. 仓库名: douyin-automation
    echo 3. 选择 Public
    echo 4. 点击 Create repository
    echo.
    pause
    exit /b 1
)

echo.
echo ? 代码上传成功！
echo.
echo ?? 接下来：
echo 1. 访问 https://github.com/%github_username%/douyin-automation
echo 2. 点击 "Actions" 标签
echo 3. 等待构建完成（约5-10分钟）
echo 4. 下载生成的APK文件
echo.
echo ?? 就是这么简单！
pause 