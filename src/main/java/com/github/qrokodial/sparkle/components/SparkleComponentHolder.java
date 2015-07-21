package com.github.qrokodial.sparkle.components;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SparkleComponentHolder<C extends Component> implements ComponentHolder<C> {
    private Map<Class<? extends Component>, Component> componentMap;

    /**
     * Instantiates the class.
     */
    public SparkleComponentHolder() {
        componentMap = new ConcurrentHashMap<Class<? extends Component>, Component>();
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasComponent(Class<? extends C> componentClass) {
        return componentMap.containsKey(componentClass);
    }

    /**
     * {@inheritDoc}
     */
    public <T extends C> Optional<T> getComponent(Class<T> componentClass) {
        Component component = componentMap.get(componentClass);

        if (component == null) {
            return Optional.empty();
        }

        return Optional.of((T)component);
    }

    /**
     * {@inheritDoc}
     */
    public <T extends C> T attachComponent(Class<T> componentClass) throws IllegalAccessException, InstantiationException {
        Optional<T> component = getComponent(componentClass);

        if (component.isPresent()) {
            return component.get();
        }

        return componentClass.newInstance();
    }

    /**
     * {@inheritDoc}
     */
    public <T extends C> Optional<T> removeComponent(Class<T> componentClass) {
        Optional<T> component = getComponent(componentClass);

        componentMap.remove(componentClass);

        return component;
    }
}
