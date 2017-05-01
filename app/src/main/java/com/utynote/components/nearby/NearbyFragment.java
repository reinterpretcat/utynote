package com.utynote.components.nearby;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utynote.R;

public class NearbyFragment extends Fragment {
    public static final String TAG = NearbyFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle bundle) {
        ViewPager viewPager = (ViewPager) inflater
                .inflate(R.layout.nearby_container_view, container, false)
                .findViewById(R.id.nearbyPager);
        viewPager.setAdapter(new NearbyAdapter(getFragmentManager(), getContext()));
        return viewPager;
    }
}
