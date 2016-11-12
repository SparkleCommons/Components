package com.qrokodial.sparkle.components.ordered;

import com.qrokodial.sparkle.components.ComponentHolder;

public interface OrderedComponentHolder extends ComponentHolder {
    /**
     * Moves the specified element at the specified position in this list (optional operation).  Shifts the element
     * currently at that position (if any) and any subsequent elements to the right (adds one to their indices). If the
     * index is out of bounds, it corrects it to the closest element.
     *
     * @param componentClass the component to move
     * @param index the index to move the component to
     * @return the component holder instance
     */
    SparkleOrderedComponentHolder moveComponent(Class<? extends MovableComponent> componentClass, int index);
}
