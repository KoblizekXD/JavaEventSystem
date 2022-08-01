package com.koblizek.jes;

import com.koblizek.jes.util.ConstantContainer;
import com.koblizek.jes.util.Registry;

import java.lang.reflect.Method;
import java.util.*;

public final class EventRegistry<T, E> extends ConstantContainer implements Registry {

    public static EventRegistry<Class<? extends Event>, Map<Method, Object[]>> MAIN_REGISTRY = new EventRegistry<>();

    private final Map<Class<? extends Event>, Map<Method, Object[]>> eventListMap;

    private EventRegistry() {
        eventListMap = new HashMap<>();
    }

    public Map<Class<? extends Event>, Map<Method, Object[]>> getMap() {
        return eventListMap;
    }

    @Override
    public void registerEvent(Class<? extends Event> event) {
        eventListMap.put(event, new HashMap<>());
    }

    @Override
    public void registerHandler(Class<? extends Event> event, Class<?> handlerClass) {
        getSuitableMethodsForEvent(event, handlerClass).forEach(method ->
                eventListMap.get(event).put(method, null));
    }

    private List<Method> getSuitableMethodsForEvent(Class<? extends Event> event, Class<?> clazz) {
        return Arrays.stream(clazz.getMethods()).filter(method ->
                method.isAnnotationPresent(EventSubscriber.class) &&
                event.equals(method.getAnnotation(EventSubscriber.class).value())).toList();
    }

    @Override
    public void registerHandler(Class<? extends Event> event, Method method) {
        registerHandler(event, method, (Object) null);
    }

    @Override
    public void registerHandler(Class<? extends Event> event, Method method, Object... args) {
        eventListMap.get(event).put(method, args);
    }

    @Override
    public void promptEvent(Class<? extends Event> event) {
        eventListMap.forEach(((event1, methods) -> {
            if (event.getName().equals(event1.getName())) {
                methods.forEach((method, args) -> {
                    try {
                        if (args[0] == null) method.invoke(null);
                        else method.invoke(null, args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }));
    }

    @Override
    public void clear() {
        MAIN_REGISTRY.eventListMap.clear();
    }
    public void printArgTest() {
        System.out.println("hi");
    }
}
