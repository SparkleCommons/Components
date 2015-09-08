package com.github.qrokodial.sparkle.components;

import java.util.Collections;
import java.util.LinkedHashMap;

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
        super(Collections.synchronizedMap(new LinkedHashMap<>()));
    }
}
