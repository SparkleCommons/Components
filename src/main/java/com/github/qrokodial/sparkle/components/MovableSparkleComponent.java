package com.github.qrokodial.sparkle.components;

public class MovableSparkleComponent<H extends OrderedComponentHolder> extends SparkleComponent<H> implements MovableComponent<H> {
    /**
     * Moves the component to the specified position in the holder (optional operation).  Shifts the element currently
     * at that position (if any) and any subsequent elements to the right (adds one to their indices). If the index is
     * out of bounds, it corrects it to the closest element.
     *
     * @param index the target index
     * @return the component instance
     */
    @Override
    public MovableSparkleComponent move(int index) {
        getHolder().ifPresent(holder -> holder.moveComponent(getClass(), index));
        return this;
    }
}
