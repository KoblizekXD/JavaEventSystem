package com.koblizek.jes.util;

import com.koblizek.jes.Event;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

public interface Registry {
    void registerEvent(Class<? extends Event> event);
    void registerHandler(Class<? extends Event> event, Class<?> handlerClass);
    void registerHandler(Class<? extends Event> event, Method method);
    void registerHandler(Class<? extends Event> event, Method method, Object ... args);
    default void registerHandlers(Class<? extends Event> event, Collection<Class<?>> handlers) {
        for (Class<?> handler : handlers) {
            registerHandler(event, handler);
        }
    }
    default void registerHandlers(Class<? extends Event> event, List<Method> methods, List<Object[]> args) {
        for (int i = 0; i < methods.size(); i++) {
            registerHandler(event, methods.get(i), args.get(i));
        }
    }
    void promptEvent(Class<? extends Event> event);
}
