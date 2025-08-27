@echo off
echo һ���ϴ���GitHub������APK
echo ================================

REM ���Git�Ƿ�װ
git --version >nul 2>&1
if errorlevel 1 (
    echo ����: δ�ҵ�Git�����Ȱ�װGit
    echo ���ص�ַ: https://git-scm.com/downloads
    pause
    exit /b 1
)

REM ��ȡGitHub�û���
set /p github_username="����������GitHub�û���: "
if "%github_username%"=="" (
    echo ����: �û�������Ϊ��
    pause
    exit /b 1
)

REM ��ʼ��Git�ֿ�
echo ��ʼ��Git�ֿ�...
git init
git add .

REM �ύ����
echo �ύ����...
git commit -m "Initial commit: �����Զ�������"

REM ���÷�֧��
git branch -M main

REM ���Զ�ֿ̲�
echo ���Զ�ֿ̲�...
git remote add origin https://github.com/%github_username%/douyin-automation.git

REM ���ʹ���
echo ���ʹ��뵽GitHub...
git push -u origin main

if errorlevel 1 (
    echo.
    echo ����ʧ�ܣ����ܵ�ԭ��
    echo 1. GitHub�û�������
    echo 2. �ֿⲻ���ڣ�������GitHub�ϴ����ֿ�
    echo 3. ������������
    echo.
    echo ���ֶ������ֿ⣺
    echo 1. ���� https://github.com/new
    echo 2. �ֿ���: douyin-automation
    echo 3. ѡ�� Public
    echo 4. ��� Create repository
    echo.
    pause
    exit /b 1
)

echo.
echo ? �����ϴ��ɹ���
echo.
echo ?? ��������
echo 1. ���� https://github.com/%github_username%/douyin-automation
echo 2. ��� "Actions" ��ǩ
echo 3. �ȴ�������ɣ�Լ5-10���ӣ�
echo 4. �������ɵ�APK�ļ�
echo.
echo ?? ������ô�򵥣�
pause 