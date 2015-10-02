package com.github.qrokodial.sparkle.components;

public interface MovableComponent<H extends OrderedComponentHolder> extends Component<H> {
    /**
     * Moves the component to the specified position in the holder (optional operation).  Shifts the element currently
     * at that position (if any) and any subsequent elements to the right (adds one to their indices). If the index is
     * out of bounds, it corrects it to the closest element.
     *
     * @param index the target index
     * @return the component instance
     */
    MovableComponent move(int index);
}
