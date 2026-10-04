package com.liskovsoft.smartyoutubetv2.tv.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.leanback.app.BrowseFragment;

public class BrowseFragment extends androidx.leanback.app.BrowseFragment {
    
    private Button mShortsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        
        // البحث عن زر الـ Shorts وتعيين الاستماع له
        if (view != null) {
            mShortsButton = view.findViewById(R.id.btn_shorts_main);
            if (mShortsButton != null) {
                mShortsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openShorts();
                    }
                });
            }
        }
        
        return view;
    }

    /**
     * فتح شاشة الـ Shorts
     */
    private void openShorts() {
        // إنشاء intent لفتح نشاط الـ Shorts
        try {
            // محاولة فتح الـ Shorts من التطبيق
            String shortsPackage = getActivity().getPackageName();
            Intent shortsIntent = new Intent();
            shortsIntent.setAction(Intent.ACTION_VIEW);
            shortsIntent.setData(android.net.Uri.parse("https://www.youtube.com/shorts"));
            
            if (shortsIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(shortsIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}