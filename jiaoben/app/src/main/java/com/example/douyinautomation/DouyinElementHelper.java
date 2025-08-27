package com.example.douyinautomation;

import android.view.accessibility.AccessibilityNodeInfo;
import android.util.Log;

import java.util.List;

/**
 * 抖音元素识别辅助类
 * 提供各种抖音界面元素的查找方法
 */
public class DouyinElementHelper {
    
    private static final String TAG = "DouyinElementHelper";
    
    /**
     * 查找搜索框
     */
    public static AccessibilityNodeInfo findSearchBox(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过文本查找
        AccessibilityNodeInfo searchBox = findNodeByText(root, "搜索");
        if (searchBox != null) return searchBox;
        
        // 方法2: 通过描述查找
        searchBox = findNodeByDescription(root, "搜索");
        if (searchBox != null) return searchBox;
        
        // 方法3: 通过ID查找（需要根据实际抖音版本调整）
        searchBox = findNodeById(root, "com.ss.android.ugc.aweme:id/search_box");
        if (searchBox != null) return searchBox;
        
        // 方法4: 通过可编辑属性查找
        return findEditableNode(root);
    }
    
    /**
     * 查找用户头像
     */
    public static AccessibilityNodeInfo findUserAvatar(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过描述查找
        AccessibilityNodeInfo avatar = findNodeByDescription(root, "头像");
        if (avatar != null) return avatar;
        
        // 方法2: 通过ID查找
        avatar = findNodeById(root, "com.ss.android.ugc.aweme:id/avatar");
        if (avatar != null) return avatar;
        
        // 方法3: 查找包含"用户"文本的节点
        avatar = findNodeByText(root, "用户");
        if (avatar != null) return avatar;
        
        return null;
    }
    
    /**
     * 查找直播间按钮
     */
    public static AccessibilityNodeInfo findLiveRoomButton(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过文本查找
        AccessibilityNodeInfo liveButton = findNodeByText(root, "直播");
        if (liveButton != null) return liveButton;
        
        // 方法2: 通过描述查找
        liveButton = findNodeByDescription(root, "直播");
        if (liveButton != null) return liveButton;
        
        // 方法3: 通过ID查找
        liveButton = findNodeById(root, "com.ss.android.ugc.aweme:id/live_button");
        if (liveButton != null) return liveButton;
        
        // 方法4: 查找包含"正在直播"的节点
        liveButton = findNodeByText(root, "正在直播");
        if (liveButton != null) return liveButton;
        
        return null;
    }
    
    /**
     * 查找点赞按钮
     */
    public static AccessibilityNodeInfo findLikeButton(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过描述查找
        AccessibilityNodeInfo likeButton = findNodeByDescription(root, "点赞");
        if (likeButton != null) return likeButton;
        
        // 方法2: 通过ID查找
        likeButton = findNodeById(root, "com.ss.android.ugc.aweme:id/like_button");
        if (likeButton != null) return likeButton;
        
        // 方法3: 查找心形图标
        likeButton = findNodeByDescription(root, "❤");
        if (likeButton != null) return likeButton;
        
        // 方法4: 查找包含"赞"的节点
        likeButton = findNodeByText(root, "赞");
        if (likeButton != null) return likeButton;
        
        return null;
    }
    
    /**
     * 查找关注按钮
     */
    public static AccessibilityNodeInfo findFollowButton(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过文本查找
        AccessibilityNodeInfo followButton = findNodeByText(root, "关注");
        if (followButton != null) return followButton;
        
        // 方法2: 通过描述查找
        followButton = findNodeByDescription(root, "关注");
        if (followButton != null) return followButton;
        
        // 方法3: 通过ID查找
        followButton = findNodeById(root, "com.ss.android.ugc.aweme:id/follow_button");
        if (followButton != null) return followButton;
        
        return null;
    }
    
    /**
     * 查找评论按钮
     */
    public static AccessibilityNodeInfo findCommentButton(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过描述查找
        AccessibilityNodeInfo commentButton = findNodeByDescription(root, "评论");
        if (commentButton != null) return commentButton;
        
        // 方法2: 通过ID查找
        commentButton = findNodeById(root, "com.ss.android.ugc.aweme:id/comment_button");
        if (commentButton != null) return commentButton;
        
        // 方法3: 查找包含"评论"的节点
        commentButton = findNodeByText(root, "评论");
        if (commentButton != null) return commentButton;
        
        return null;
    }
    
    /**
     * 查找分享按钮
     */
    public static AccessibilityNodeInfo findShareButton(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过描述查找
        AccessibilityNodeInfo shareButton = findNodeByDescription(root, "分享");
        if (shareButton != null) return shareButton;
        
        // 方法2: 通过ID查找
        shareButton = findNodeById(root, "com.ss.android.ugc.aweme:id/share_button");
        if (shareButton != null) return shareButton;
        
        // 方法3: 查找包含"分享"的节点
        shareButton = findNodeByText(root, "分享");
        if (shareButton != null) return shareButton;
        
        return null;
    }
    
    /**
     * 查找返回按钮
     */
    public static AccessibilityNodeInfo findBackButton(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过描述查找
        AccessibilityNodeInfo backButton = findNodeByDescription(root, "返回");
        if (backButton != null) return backButton;
        
        // 方法2: 通过ID查找
        backButton = findNodeById(root, "com.ss.android.ugc.aweme:id/back_button");
        if (backButton != null) return backButton;
        
        // 方法3: 查找导航栏返回按钮
        backButton = findNodeByDescription(root, "Navigate up");
        if (backButton != null) return backButton;
        
        return null;
    }
    
    /**
     * 查找底部导航栏的首页按钮
     */
    public static AccessibilityNodeInfo findHomeTab(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过文本查找
        AccessibilityNodeInfo homeTab = findNodeByText(root, "首页");
        if (homeTab != null) return homeTab;
        
        // 方法2: 通过描述查找
        homeTab = findNodeByDescription(root, "首页");
        if (homeTab != null) return homeTab;
        
        // 方法3: 通过ID查找
        homeTab = findNodeById(root, "com.ss.android.ugc.aweme:id/tab_home");
        if (homeTab != null) return homeTab;
        
        return null;
    }
    
    /**
     * 查找底部导航栏的朋友按钮
     */
    public static AccessibilityNodeInfo findFriendTab(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过文本查找
        AccessibilityNodeInfo friendTab = findNodeByText(root, "朋友");
        if (friendTab != null) return friendTab;
        
        // 方法2: 通过描述查找
        friendTab = findNodeByDescription(root, "朋友");
        if (friendTab != null) return friendTab;
        
        // 方法3: 通过ID查找
        friendTab = findNodeById(root, "com.ss.android.ugc.aweme:id/tab_friend");
        if (friendTab != null) return friendTab;
        
        return null;
    }
    
    /**
     * 查找底部导航栏的发布按钮
     */
    public static AccessibilityNodeInfo findPublishTab(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过描述查找
        AccessibilityNodeInfo publishTab = findNodeByDescription(root, "发布");
        if (publishTab != null) return publishTab;
        
        // 方法2: 通过ID查找
        publishTab = findNodeById(root, "com.ss.android.ugc.aweme:id/tab_publish");
        if (publishTab != null) return publishTab;
        
        return null;
    }
    
    /**
     * 查找底部导航栏的消息按钮
     */
    public static AccessibilityNodeInfo findMessageTab(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过文本查找
        AccessibilityNodeInfo messageTab = findNodeByText(root, "消息");
        if (messageTab != null) return messageTab;
        
        // 方法2: 通过描述查找
        messageTab = findNodeByDescription(root, "消息");
        if (messageTab != null) return messageTab;
        
        // 方法3: 通过ID查找
        messageTab = findNodeById(root, "com.ss.android.ugc.aweme:id/tab_message");
        if (messageTab != null) return messageTab;
        
        return null;
    }
    
    /**
     * 查找底部导航栏的我按钮
     */
    public static AccessibilityNodeInfo findProfileTab(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        // 方法1: 通过文本查找
        AccessibilityNodeInfo profileTab = findNodeByText(root, "我");
        if (profileTab != null) return profileTab;
        
        // 方法2: 通过描述查找
        profileTab = findNodeByDescription(root, "我");
        if (profileTab != null) return profileTab;
        
        // 方法3: 通过ID查找
        profileTab = findNodeById(root, "com.ss.android.ugc.aweme:id/tab_profile");
        if (profileTab != null) return profileTab;
        
        return null;
    }
    
    // 辅助方法
    
    /**
     * 通过文本查找节点
     */
    private static AccessibilityNodeInfo findNodeByText(AccessibilityNodeInfo root, String text) {
        if (root == null) return null;
        
        List<AccessibilityNodeInfo> nodes = root.findAccessibilityNodeInfosByText(text);
        if (nodes != null && !nodes.isEmpty()) {
            return nodes.get(0);
        }
        
        for (int i = 0; i < root.getChildCount(); i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if (child != null) {
                AccessibilityNodeInfo result = findNodeByText(child, text);
                if (result != null) return result;
                child.recycle();
            }
        }
        
        return null;
    }
    
    /**
     * 通过描述查找节点
     */
    private static AccessibilityNodeInfo findNodeByDescription(AccessibilityNodeInfo root, String description) {
        if (root == null) return null;
        
        if (description.equals(root.getContentDescription())) {
            return root;
        }
        
        for (int i = 0; i < root.getChildCount(); i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if (child != null) {
                AccessibilityNodeInfo result = findNodeByDescription(child, description);
                if (result != null) return result;
                child.recycle();
            }
        }
        
        return null;
    }
    
    /**
     * 通过ID查找节点
     */
    private static AccessibilityNodeInfo findNodeById(AccessibilityNodeInfo root, String viewId) {
        if (root == null) return null;
        
        List<AccessibilityNodeInfo> nodes = root.findAccessibilityNodeInfosByViewId(viewId);
        if (nodes != null && !nodes.isEmpty()) {
            return nodes.get(0);
        }
        
        for (int i = 0; i < root.getChildCount(); i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if (child != null) {
                AccessibilityNodeInfo result = findNodeById(child, viewId);
                if (result != null) return result;
                child.recycle();
            }
        }
        
        return null;
    }
    
    /**
     * 查找可编辑的节点（通常是输入框）
     */
    private static AccessibilityNodeInfo findEditableNode(AccessibilityNodeInfo root) {
        if (root == null) return null;
        
        if (root.isEditable()) {
            return root;
        }
        
        for (int i = 0; i < root.getChildCount(); i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if (child != null) {
                AccessibilityNodeInfo result = findEditableNode(child);
                if (result != null) return result;
                child.recycle();
            }
        }
        
        return null;
    }
    
    /**
     * 打印节点信息用于调试
     */
    public static void printNodeInfo(AccessibilityNodeInfo node, String tag) {
        if (node == null) {
            Log.d(TAG, tag + ": null");
            return;
        }
        
        Log.d(TAG, tag + ": " +
                "text=" + node.getText() +
                ", desc=" + node.getContentDescription() +
                ", id=" + node.getViewIdResourceName() +
                ", clickable=" + node.isClickable() +
                ", editable=" + node.isEditable());
    }
} 