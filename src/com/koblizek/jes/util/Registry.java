package com.koblizek.jes.util;

import com.koblizek.jes.Event;

import java.lang.reflect.Method;
import java.util.Collection;

public interface Registry {
    void registerEvent(Class<? extends Event> event);
    void registerHandler(Class<?> handlerClass);
    void registerHandler(Method method);
    default void registerHandlers(Collection<Class<?>> handlers) {
        for (Class<?> handler : handlers) {
            registerHandler(handler);
        }
    }
    void promptEvent(Class<? extends Event> event);
}
