package com.utynote.components.search;

import android.support.annotation.NonNull;

import static com.utynote.utils.Preconditions.checkNotNull;

public final class SearchItemData {
    @NonNull public final String primaryTitle;
    @NonNull public final String secondaryTitle;
    @NonNull public final String primarySubtitle;
    @NonNull public final String secondarySubtitle;

    private SearchItemData(@NonNull String primaryTitle,
                 @NonNull String secondaryTitle,
                 @NonNull String primarySubtitle,
                 @NonNull String secondarySubtitle) {
        this.primaryTitle = primaryTitle;
        this.secondaryTitle = secondaryTitle;
        this.primarySubtitle = primarySubtitle;
        this.secondarySubtitle = secondarySubtitle;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    static final class Builder {
        private String mPrimaryTitle;
        private String mSecondaryTitle;
        private String mPrimarySubtitle;
        private String mSecondarySubtitle;

        public Builder withPrimaryTitle(@NonNull String content) {
            mPrimaryTitle = content;
            return this;
        }

        public Builder withSecondaryTitle(@NonNull String content) {
            mSecondaryTitle = content;
            return this;
        }

        public Builder withPrimarySubtitle(@NonNull String content) {
            mPrimarySubtitle = content;
            return this;
        }

        public Builder withSecondarySubtitle(@NonNull String content) {
            mSecondarySubtitle = content;
            return this;
        }

        public SearchItemData build() {
            return new SearchItemData(checkNotNull(mPrimaryTitle), checkNotNull(mSecondaryTitle),
                    checkNotNull(mPrimarySubtitle), checkNotNull(mSecondarySubtitle));
        }
    }
}
