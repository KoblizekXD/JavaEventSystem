package com.koblizek.testing;

import com.koblizek.jes.Event;
import com.koblizek.jes.EventRegistry;
import com.koblizek.jes.EventSubscriber;

import java.lang.reflect.Method;
import java.util.Map;

import static com.koblizek.jes.EventRegistry.MAIN_REGISTRY;


public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        MAIN_REGISTRY.registerEvent(TestEvent.class);
        MAIN_REGISTRY.registerHandler(TestEvent.class, Main.class.getMethod("test", EventRegistry.class), MAIN_REGISTRY);
        MAIN_REGISTRY.promptEvent(TestEvent.class);
    }
    @EventSubscriber(TestEvent.class)
    public static void test(EventRegistry<Class<? extends Event>, Map<Method, Object[]>> reg) {
        reg.printArgTest();
    }
}
