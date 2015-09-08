package com.github.qrokodial.sparkle.components;

import java.util.concurrent.ConcurrentHashMap;

/**
 * For when the order of your components is irrelevant.
 *
 * @param <C>
 */
public class UnorderedComponentHolder<C extends Component> extends SparkleComponentHolder<C> {
    /**
     * Instantiates the class.
     */
    public UnorderedComponentHolder() {
        super(new ConcurrentHashMap<>());
    }
}
