package com.utynote.components.search;

import android.support.annotation.NonNull;

import com.utynote.utils.Sequences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class SearchViewModel {

    interface Visitor {
        void visit(@NonNull Busy model);
        void visit(@NonNull Error model);
        void visit(@NonNull Data model);
    }

    interface Abstract {
        void accept(@NonNull Visitor visitor);
    }

    static final class Busy implements Abstract {
        @Override
        public void accept(@NonNull Visitor visitor) {
            visitor.visit(this);
        }
    }

    static final class Error implements Abstract {
        @NonNull public final String description;

        public Error(@NonNull String description) {
            this.description = description;
        }

        @Override
        public void accept(@NonNull Visitor visitor) {
            visitor.visit(this);
        }
    }

    static final class Data implements Abstract {
        @NonNull public final List<SearchItemData> data;

        public Data(@NonNull Iterable<SearchItemData> data) {
            this.data = Collections.unmodifiableList(Sequences.asList(data));
        }

        @Override
        public void accept(@NonNull Visitor visitor) {
            visitor.visit(this);
        }
    }
}
