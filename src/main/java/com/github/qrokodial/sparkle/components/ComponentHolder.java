package com.github.qrokodial.sparkle.components;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
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
     * @return all the components held by this holder
     */
    Collection<C> getComponents();

    /**
     * Gets all componenets that inherit a certain type.
     *
     * @param type
     * @param <T>
     * @return a collection of all the matching components
     */
    <T> Collection<T> getComponents(Class<T> type);

    /**
     * Gets all components that inherit certain types.
     *
     * @param types
     * @param <T>
     * @return a collection of all the matching components
     */
    Collection<C> getComponents(Class<?>... types);

    /**
     * Attaches the component to the holder. If there is an attached component that already matches the class, it simply
     * returns that instance instead.
     *
     * @param componentClass
     * @param parameters
     * @param <T>
     * @return the newly attached instance of the component
     * @throws IllegalAccessException  if the class or its nullary
     *         constructor is not accessible.
     * @throws InstantiationException
     *         if this {@code Class} represents an abstract class,
     *         an interface, an array class, a primitive type, or void;
     *         or if the class has no nullary constructor;
     *         or if the instantiation fails for some other reason.
     * @throws NoSuchMethodException if a matching method is not found.
     * @throws InvocationTargetException if the underlying constructor throws an exception.
     */
    <T extends C> T attachComponent(Class<T> componentClass, Object... parameters) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    /**
     * Does what {@link #attachComponent(Class, Object...)} does, but ignores all exceptions. Only used when you're
     * lazy and are sure no exceptions will be thrown.
     *
     * @param componentClass
     * @param parameters
     * @param <T>
     * @return the newly attached instance of the component
     */
    <T extends  C> T forceAttachComponent(Class<T> componentClass, Object... parameters);

    /**
     * @param componentClass
     * @param <T>
     * @return the component that has been removed from this holder, if there is one
     */
    <T extends C> Optional<T> removeComponent(Class<T> componentClass);
}
