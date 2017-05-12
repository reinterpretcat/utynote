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
    public <T extends Fragment> T findFragment(@NonNull String tag, Class<T> type) {
        return type.cast(checkNotNull(mFragmentManager.findFragmentByTag(tag)));
    }

    public void replaceFragment(@NonNull String tag, Func0<Fragment> factory) {
        mFragmentManager
                .beginTransaction()
                .replace(R.id.panel_content, getFragment(tag, factory), tag)
                .commit();
    }

    @NonNull
    public Fragment getFragment(@NonNull String tag, Func0<Fragment> factory) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        return fragment == null ? factory.call() : fragment;
    }
}
