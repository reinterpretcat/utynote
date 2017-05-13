package com.utynote.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.utynote.R;

import rx.functions.Func0;

import static com.utynote.utils.Preconditions.checkNotNull;

public final class Fragments {

    @NonNull private final FragmentManager mFragmentManager;

    public Fragments(@NonNull FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @NonNull
    public <T extends Fragment> T find(@NonNull String tag, Class<T> type) {
        return type.cast(checkNotNull(mFragmentManager.findFragmentByTag(tag)));
    }

    public Fragments addToContent(@NonNull String tag, Func0<Fragment> factory) {
        add(tag, R.id.main_content, factory);
        return this;
    }

    public Fragments addToPanel(@NonNull String tag, Func0<Fragment> factory) {
        add(tag, R.id.panel_content, factory);
        return this;
    }

    public Fragments replaceInPanel(@NonNull String tag, Func0<Fragment> factory) {
        mFragmentManager
                .beginTransaction()
                .replace(R.id.panel_content, get(tag, factory), tag)
                .commit();

        return this;
    }

    public boolean isAttached(@NonNull String tag) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        return fragment != null && !fragment.isDetached();
    }

    @NonNull
    public Fragment get(@NonNull String tag, Func0<Fragment> factory) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        return fragment == null ? factory.call() : fragment;
    }

    private Fragments add(@NonNull String tag, @IdRes int containerId, Func0<Fragment> factory) {
        mFragmentManager
                .beginTransaction()
                .add(containerId, get(tag, factory), tag)
                .commit();

        return this;
    }
}
