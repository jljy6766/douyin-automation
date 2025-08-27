package com.example.douyinautomation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private EditText etDouyinId;
    private Button btnEnableAccessibility;
    private Button btnEnterLiveRoom;
    private Button btnAutoLike;
    private Button btnStopAutomation;
    private TextView tvStatus;
    
    private static final int ACCESSIBILITY_SETTINGS_REQUEST = 1001;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        setupListeners();
        updateStatus();
    }
    
    private void initViews() {
        etDouyinId = findViewById(R.id.et_douyin_id);
        btnEnableAccessibility = findViewById(R.id.btn_enable_accessibility);
        btnEnterLiveRoom = findViewById(R.id.btn_enter_live_room);
        btnAutoLike = findViewById(R.id.btn_auto_like);
        btnStopAutomation = findViewById(R.id.btn_stop_automation);
        tvStatus = findViewById(R.id.tv_status);
    }
    
    private void setupListeners() {
        btnEnableAccessibility.setOnClickListener(v -> openAccessibilitySettings());
        btnEnterLiveRoom.setOnClickListener(v -> startEnterLiveRoom());
        btnAutoLike.setOnClickListener(v -> startAutoLike());
        btnStopAutomation.setOnClickListener(v -> stopAutomation());
    }
    
    private void openAccessibilitySettings() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivityForResult(intent, ACCESSIBILITY_SETTINGS_REQUEST);
    }
    
    private void startEnterLiveRoom() {
        if (!DouyinAccessibilityService.isServiceEnabled()) {
            Toast.makeText(this, "请先启用无障碍服务", Toast.LENGTH_SHORT).show();
            return;
        }
        
        String douyinId = etDouyinId.getText().toString().trim();
        if (douyinId.isEmpty()) {
            Toast.makeText(this, "请输入抖音号", Toast.LENGTH_SHORT).show();
            return;
        }
        
        DouyinAccessibilityService.startEnterLiveRoom(douyinId);
        Toast.makeText(this, "开始进入直播间", Toast.LENGTH_SHORT).show();
        updateStatus();
    }
    
    private void startAutoLike() {
        if (!DouyinAccessibilityService.isServiceEnabled()) {
            Toast.makeText(this, "请先启用无障碍服务", Toast.LENGTH_SHORT).show();
            return;
        }
        
        DouyinAccessibilityService.startAutoLike();
        Toast.makeText(this, "开始自动点赞", Toast.LENGTH_SHORT).show();
        updateStatus();
    }
    
    private void stopAutomation() {
        DouyinAccessibilityService.stopAutomation();
        Toast.makeText(this, "自动化已停止", Toast.LENGTH_SHORT).show();
        updateStatus();
    }
    
    private void updateStatus() {
        if (DouyinAccessibilityService.isServiceEnabled()) {
            tvStatus.setText("状态: 无障碍服务已启用");
            btnEnableAccessibility.setEnabled(false);
        } else {
            tvStatus.setText("状态: 无障碍服务未启用");
            btnEnableAccessibility.setEnabled(true);
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateStatus();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACCESSIBILITY_SETTINGS_REQUEST) {
            updateStatus();
        }
    }
} 