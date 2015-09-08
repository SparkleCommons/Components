package com.github.qrokodial.sparkle.components;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * For when the order of your components is important.
 *
 * @param <C>
 */
public class OrderedComponentHolder<C extends Component> extends SparkleComponentHolder<C> {
    /**
     * Instantiates the class.
     */
    public OrderedComponentHolder() {
        super(new ConcurrentSkipListMap<>());
    }
}
