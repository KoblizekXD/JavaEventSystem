package com.koblizek.jes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EventSubscriber {
    Class<? extends Event> value();
}
