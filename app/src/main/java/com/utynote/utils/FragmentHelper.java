package com.utynote.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.utynote.R;

import rx.functions.Func0;

import static com.utynote.utils.Preconditions.checkNotNull;

public final class FragmentHelper {

    @NonNull private final FragmentManager mFragmentManager;

    public FragmentHelper(@NonNull FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @NonNull
    public <T extends Fragment> T find(@NonNull String tag, Class<T> type) {
        return type.cast(checkNotNull(mFragmentManager.findFragmentByTag(tag)));
    }

    public void replace(@NonNull String tag, Func0<Fragment> factory) {
        mFragmentManager
                .beginTransaction()
                .replace(R.id.panel_content, get(tag, factory), tag)
                .commit();
    }

    public boolean has(@NonNull String tag) {
        return mFragmentManager.findFragmentByTag(tag) != null;
    }

    @NonNull
    public Fragment get(@NonNull String tag, Func0<Fragment> factory) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        return fragment == null ? factory.call() : fragment;
    }
}
