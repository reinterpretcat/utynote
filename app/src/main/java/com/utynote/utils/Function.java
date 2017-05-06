package com.utynote.utils;

public final class Function {

    public interface ZeroArgs<Return> {
        Return call();
    }

    public interface OneArg<Return, Arg> {
        Return call(Arg arg);
    }
}
