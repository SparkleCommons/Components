package com.github.qrokodial.sparkle.components;

import java.util.Optional;

public interface ComponentHolder<C extends Component> {
    /**
     * @param componentClass
     * @return true if this holder contains a component, false otherwise
     */
    boolean hasComponent(Class<? extends C> componentClass);

    /**
     * @param componentClass
     * @param <T>
     * @return the component that matches the class that is held within this holder, if there is one
     */
    <T extends C> Optional<T> getComponent(Class<T> componentClass);

    /**
     * Attaches the component to the holder. If there is an attached component that already matches the class, it simply
     * returns that instance instead.
     *
     * @param componentClass
     * @param <T>
     * @return the newly attached instance of the component
     * @throws IllegalAccessException  if the class or its nullary
     *         constructor is not accessible.
     * @throws InstantiationException
     *         if this {@code Class} represents an abstract class,
     *         an interface, an array class, a primitive type, or void;
     *         or if the class has no nullary constructor;
     *         or if the instantiation fails for some other reason.
     */
    <T extends C> T attachComponent(Class<T> componentClass) throws IllegalAccessException, InstantiationException;

    /**
     * @param componentClass
     * @param <T>
     * @return the component that has been removed from this holder, if there is one
     */
    <T extends C> Optional<T> removeComponent(Class<T> componentClass);
}
