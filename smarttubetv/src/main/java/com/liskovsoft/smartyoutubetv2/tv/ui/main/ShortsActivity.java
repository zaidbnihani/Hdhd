package com.liskovsoft.smartyoutubetv2.tv.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import com.liskovsoft.smartyoutubetv2.common.misc.MotherActivity;

public class ShortsActivity extends MotherActivity {
    
    private Button mBackButton;
    private GridView mShortsGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shorts);
        
        // زر الرجوع
        mBackButton = findViewById(R.id.btn_back_shorts);
        if (mBackButton != null) {
            mBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        
        // شبكة الـ Shorts
        mShortsGrid = findViewById(R.id.shorts_grid);
        
        // تحميل الفيديوهات القصيرة من المصدر
        loadShorts();
    }

    /**
     * تحميل فيديوهات Shorts
     */
    private void loadShorts() {
        // سيتم تحميل الفيديوهات من API أو المخزن المؤقت
        // هذا مثال بسيط
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}