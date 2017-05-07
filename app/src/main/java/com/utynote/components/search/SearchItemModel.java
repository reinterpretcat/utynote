package com.utynote.components.search;

import android.support.annotation.NonNull;
import android.text.Spannable;

import static com.utynote.utils.Preconditions.checkNotNull;

public final class SearchItemModel {
    @NonNull public final Spannable title;

    private SearchItemModel(@NonNull Spannable title) {
        this.title = title;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Spannable mTitle;

        public Builder withTitle(@NonNull Spannable title) {
            mTitle = title;
            return this;
        }

        public SearchItemModel build() {
            return new SearchItemModel(checkNotNull(mTitle));
        }
    }
}
