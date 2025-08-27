package com.example.douyinautomation;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;

public class DouyinAccessibilityService extends AccessibilityService {
    
    private static final String TAG = "DouyinAutomation";
    private static final String DOUYIN_PACKAGE = "com.ss.android.ugc.aweme";
    
    private static DouyinAccessibilityService instance;
    private Handler handler;
    private boolean isAutomationRunning = false;
    private String targetDouyinId = "";
    private boolean isAutoLikeMode = false;
    
    // 自动化状态
    private static final int STATE_IDLE = 0;
    private static final int STATE_ENTERING_LIVE_ROOM = 1;
    private static final int STATE_AUTO_LIKING = 2;
    
    private int currentState = STATE_IDLE;
    
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        handler = new Handler(Looper.getMainLooper());
        Log.d(TAG, "无障碍服务已创建");
    }
    
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!isAutomationRunning) return;
        
        String packageName = event.getPackageName() != null ? event.getPackageName().toString() : "";
        
        // 只处理抖音应用的事件
        if (!DOUYIN_PACKAGE.equals(packageName)) return;
        
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) return;
        
        try {
            switch (currentState) {
                case STATE_ENTERING_LIVE_ROOM:
                    handleEnterLiveRoom(rootNode);
                    break;
                case STATE_AUTO_LIKING:
                    handleAutoLike(rootNode);
                    break;
            }
        } finally {
            rootNode.recycle();
        }
    }
    
    @Override
    public void onInterrupt() {
        Log.d(TAG, "无障碍服务被中断");
    }
    
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "无障碍服务已连接");
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        Log.d(TAG, "无障碍服务已销毁");
    }
    
    // 处理进入直播间的逻辑
    private void handleEnterLiveRoom(AccessibilityNodeInfo rootNode) {
        // 1. 查找搜索框
        AccessibilityNodeInfo searchBox = DouyinElementHelper.findSearchBox(rootNode);
        if (searchBox != null) {
            DouyinElementHelper.printNodeInfo(searchBox, "找到搜索框");
            searchBox.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            handler.postDelayed(() -> {
                // 2. 输入抖音号
                inputText(targetDouyinId);
                handler.postDelayed(() -> {
                    // 3. 点击搜索
                    clickSearchButton();
                }, 1000);
            }, 1000);
            return;
        }
        
        // 4. 查找用户头像并点击
        AccessibilityNodeInfo avatar = DouyinElementHelper.findUserAvatar(rootNode);
        if (avatar != null) {
            DouyinElementHelper.printNodeInfo(avatar, "找到用户头像");
            avatar.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            handler.postDelayed(() -> {
                // 5. 查找直播间入口并点击
                clickLiveRoomEntry();
            }, 2000);
            return;
        }
        
        // 6. 查找直播间按钮
        AccessibilityNodeInfo liveButton = DouyinElementHelper.findLiveRoomButton(rootNode);
        if (liveButton != null) {
            DouyinElementHelper.printNodeInfo(liveButton, "找到直播间按钮");
            liveButton.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            currentState = STATE_IDLE;
            isAutomationRunning = false;
            showToast("已进入直播间");
        }
    }
    
    // 处理自动点赞的逻辑
    private void handleAutoLike(AccessibilityNodeInfo rootNode) {
        // 查找点赞按钮
        AccessibilityNodeInfo likeButton = DouyinElementHelper.findLikeButton(rootNode);
        if (likeButton != null) {
            DouyinElementHelper.printNodeInfo(likeButton, "找到点赞按钮");
            likeButton.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            showToast("已点赞");
            
            // 延迟后继续点赞下一个视频
            handler.postDelayed(() -> {
                if (isAutoLikeMode) {
                    // 滑动到下一个视频
                    performSwipeUp();
                }
            }, 2000);
        }
    }
    

    
    // 输入文本
    private void inputText(String text) {
        // 这里需要实现文本输入逻辑
        // 由于无障碍服务的限制，可能需要使用其他方法
        Log.d(TAG, "输入文本: " + text);
    }
    
    // 点击搜索按钮
    private void clickSearchButton() {
        // 实现搜索按钮点击逻辑
        Log.d(TAG, "点击搜索按钮");
    }
    
    // 点击直播间入口
    private void clickLiveRoomEntry() {
        // 实现直播间入口点击逻辑
        Log.d(TAG, "点击直播间入口");
    }
    
    // 执行向上滑动
    private void performSwipeUp() {
        Path path = new Path();
        path.moveTo(500, 1000);
        path.lineTo(500, 200);
        
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 0, 500));
        
        dispatchGesture(builder.build(), null, null);
    }
    
    // 显示Toast消息
    private void showToast(String message) {
        handler.post(() -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }
    
    // 静态方法：开始进入直播间
    public static void startEnterLiveRoom(String douyinId) {
        if (instance != null) {
            instance.targetDouyinId = douyinId;
            instance.currentState = STATE_ENTERING_LIVE_ROOM;
            instance.isAutomationRunning = true;
            instance.isAutoLikeMode = false;
            
            // 启动抖音应用
            Intent intent = instance.getPackageManager().getLaunchIntentForPackage(DOUYIN_PACKAGE);
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                instance.startActivity(intent);
            }
        }
    }
    
    // 静态方法：开始自动点赞
    public static void startAutoLike() {
        if (instance != null) {
            instance.currentState = STATE_AUTO_LIKING;
            instance.isAutomationRunning = true;
            instance.isAutoLikeMode = true;
            
            // 启动抖音应用
            Intent intent = instance.getPackageManager().getLaunchIntentForPackage(DOUYIN_PACKAGE);
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                instance.startActivity(intent);
            }
        }
    }
    
    // 静态方法：停止自动化
    public static void stopAutomation() {
        if (instance != null) {
            instance.isAutomationRunning = false;
            instance.currentState = STATE_IDLE;
            instance.isAutoLikeMode = false;
        }
    }
    
    // 静态方法：检查服务是否启用
    public static boolean isServiceEnabled() {
        String enabledServices = Settings.Secure.getString(
            instance != null ? instance.getContentResolver() : null,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        );
        return enabledServices != null && enabledServices.contains(DouyinAccessibilityService.class.getName());
    }
} 