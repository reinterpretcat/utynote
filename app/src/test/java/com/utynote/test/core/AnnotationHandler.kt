package com.utynote.test.core

import com.utynote.app.dependencies.NetModule
import com.utynote.test.annotations.HttpResponse
import com.utynote.test.network.HttpInterceptorFactory

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement

import rx.Observable


/**
 * A rule which processes custom annotations before test is run.
 */
class AnnotationHandler : TestWatcher() {
    override fun apply(base: Statement, description: Description): Statement {

        Observable.from(description.annotations)
                .ofType(HttpResponse::class.java)
                .forEach { a -> NetModule.setInterceptor(HttpInterceptorFactory.create(a.path)) }

        return super.apply(base, description)
    }
}
