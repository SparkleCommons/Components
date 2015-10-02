package com.github.qrokodial.sparkle.components;

import com.github.qrokodial.sparkle.utilities.collections.ArrayMap;

/**
 * For when the order of your components is important.
 */
public class SparkleOrderedComponentHolder extends SparkleComponentHolder implements OrderedComponentHolder {
    /**
     * Instantiates the class.
     */
    public SparkleOrderedComponentHolder() {
        super(new ArrayMap<>());
    }

    /**
     * Moves the specified element at the specified position in this list (optional operation).  Shifts the element
     * currently at that position (if any) and any subsequent elements to the right (adds one to their indices). If the
     * index is out of bounds, it corrects it to the closest element.
     *
     * @param componentClass the component to move
     * @param index the index to move the component to
     * @return the component holder instance
     */
    @Override
    public SparkleOrderedComponentHolder moveComponent(Class<? extends MovableComponent> componentClass, int index) {
        if (hasComponent(componentClass)) {
            ((ArrayMap<Class<? extends Component>, Component>)componentMap).put(componentClass, getComponent(componentClass).get(), index);
        }
        return this;
    }
}
