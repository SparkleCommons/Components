package com.qrokodial.sparkle.components.unordered;

import com.qrokodial.sparkle.components.SparkleComponentHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * For when the order of your components is irrelevant.
 */
public class SparkleUnorderedComponentHolder extends SparkleComponentHolder {
    /**
     * Instantiates the class.
     */
    public SparkleUnorderedComponentHolder() {
        super(new ConcurrentHashMap<>());
    }
}
