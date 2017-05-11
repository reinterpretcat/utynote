package com.utynote.app;

import android.support.annotation.NonNull;

import static com.utynote.utils.Preconditions.checkNotNull;

public final class AppConfig {

    @NonNull public final Search search;

    private AppConfig(@NonNull Search search) {
        this.search = search;
    }

    public static final class Search {
        @NonNull public final String server;
        @NonNull public final String format;

        private Search(@NonNull String server, @NonNull String format) {
            this.server = server;
            this.format = format;
        }
    }

    public static final class Builder {
        private Search mSearch;

        public Builder withSearch(@NonNull String server, @NonNull String formatType) {
            mSearch = new Search(server, formatType);
            return this;
        }

        public AppConfig build() {
            return new AppConfig(checkNotNull(mSearch));
        }
    }
}
