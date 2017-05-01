package com.utynote.components.nearby;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.utynote.R;
import com.utynote.components.nearby.departures.DeparturesFragment;
import com.utynote.components.nearby.live.LiveFragment;
import com.utynote.components.nearby.places.PlacesFragment;

class NearbyAdapter extends FragmentPagerAdapter {
    @NonNull private final Context mContext;

    private enum PageTypes {
        PLACES(R.drawable.ic_nearby_places, PlacesFragment.class),
        DEPARTURES(R.drawable.ic_nearby_departures, DeparturesFragment.class),
        LIVE(R.drawable.ic_nearby_live, LiveFragment.class);

        @DrawableRes private final int mTitleDrawableId;
        @NonNull private final Class mFragmentType;

        PageTypes(@DrawableRes int titleDrawableId, @NonNull Class fragmentType) {
            mTitleDrawableId = titleDrawableId;
            mFragmentType = fragmentType;
        }

        @DrawableRes
        public int getTitleDrawableId() {
            return mTitleDrawableId;
        }

        @NonNull
        public Class getFragmentType() {
            return mFragmentType;
        }
    }

    public NearbyAdapter(FragmentManager fragmentManager, @NonNull Context context) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            PageTypes pageType = PageTypes.values()[position];
            return (Fragment) pageType.getFragmentType().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create a fragment!");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        PageTypes pageType = PageTypes.values()[position];

        Drawable icon = ContextCompat.getDrawable(mContext, pageType.getTitleDrawableId());
        SpannableStringBuilder sb = new SpannableStringBuilder(" ");
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(icon, ImageSpan.ALIGN_BASELINE);
        sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }

    @Override
    public int getCount() {
        return PageTypes.values().length;
    }
}
