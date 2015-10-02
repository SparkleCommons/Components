package com.github.qrokodial.sparkle.components;

import java.util.concurrent.ConcurrentHashMap;

/**
 * For when the order of your components is irrelevant.
 */
public class UnorderedComponentHolder extends SparkleComponentHolder {
    /**
     * Instantiates the class.
     */
    public UnorderedComponentHolder() {
        super(new ConcurrentHashMap<>());
    }
}
