package com.koblizek.jes;

import com.koblizek.jes.util.Registry;

import java.lang.reflect.Method;

public final class EventRegistry implements Registry {
    private EventRegistry() {}


    @Override
    public void registerEvent(Class<? extends Event> event) {

    }

    @Override
    public void registerHandler(Class<?> handlerClass) {

    }

    @Override
    public void registerHandler(Method method) {

    }

    @Override
    public void promptEvent(Class<? extends Event> event) {

    }
}
