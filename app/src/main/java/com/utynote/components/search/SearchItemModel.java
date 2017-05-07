package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;

import static com.utynote.utils.Preconditions.checkNotNull;

public final class SearchItemModel {
    @NonNull public final Spannable primaryTitle;
    @Nullable public final Spannable secondaryTitle;
    @Nullable public final Spannable primarySubtitle;
    @Nullable public final Spannable secondarySubtitle;

    private SearchItemModel(@NonNull Spannable primaryTitle,
                            @NonNull Spannable secondaryTitle,
                            @NonNull Spannable primarySubtitle,
                            @NonNull Spannable secondarySubtitle) {
        this.primaryTitle = primaryTitle;
        this.secondaryTitle = secondaryTitle;
        this.primarySubtitle = primarySubtitle;
        this.secondarySubtitle = secondarySubtitle;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    static final class Builder {
        private Spannable mPrimaryTitle;
        private Spannable mSecondaryTitle;
        private Spannable mPrimarySubtitle;
        private Spannable mSecondarySubtitle;

        public Builder withPrimaryTitle(@NonNull Spannable content) {
            mPrimaryTitle = content;
            return this;
        }

        public Builder withSecondaryTitle(@NonNull Spannable content) {
            mSecondaryTitle = content;
            return this;
        }

        public Builder withPrimarySubtitle(@NonNull Spannable content) {
            mPrimarySubtitle = content;
            return this;
        }

        public Builder withSecondarySubtitle(@NonNull Spannable content) {
            mSecondarySubtitle = content;
            return this;
        }

        public SearchItemModel build() {
            return new SearchItemModel(checkNotNull(mPrimaryTitle), mSecondaryTitle,
                    mPrimarySubtitle, mSecondarySubtitle);
        }
    }
}
