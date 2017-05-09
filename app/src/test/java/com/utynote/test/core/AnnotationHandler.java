package com.utynote.test.core;

import com.annimon.stream.Stream;
import com.utynote.app.dependencies.NetModule;
import com.utynote.test.annotations.HttpResponse;
import com.utynote.test.network.HttpInterceptorFactory;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


/**
 * A rule which processes custom annotations before test is run.
 */
public class AnnotationHandler extends TestWatcher {
    @Override
    public Statement apply(Statement base, Description description) {

        Stream.of(description.getAnnotations())
                .select(HttpResponse.class)
                .forEach(a -> NetModule.setInterceptor(HttpInterceptorFactory.create(a.path())));

        return super.apply(base, description);
    }
}
