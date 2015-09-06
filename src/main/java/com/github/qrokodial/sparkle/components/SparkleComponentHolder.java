package com.github.qrokodial.sparkle.components;

import com.github.qrokodial.sparkle.utilities.collections.ArrayUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SparkleComponentHolder<C extends Component> implements ComponentHolder<C> {
    private Map<Class<? extends Component>, Component> componentMap;

    /**
     * Instantiates the class.
     */
    public SparkleComponentHolder() {
        componentMap = new ConcurrentHashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasComponent(Class<? extends C> componentClass) {
        return componentMap.containsKey(componentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> Optional<T> getComponent(Class<T> componentClass) {
        Component component = componentMap.get(componentClass);

        if (component == null) {
            return Optional.empty();
        }

        return Optional.of((T) component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> T attachComponent(Class<T> componentClass, Object... parameters) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Optional<T> component = getComponent(componentClass);

        if (component.isPresent()) {
            return component.get();
        }

        Constructor<?> constructor = componentClass.getDeclaredConstructor(ArrayUtils.getTypes(parameters));
        constructor.setAccessible(true);

        T componentInstance = (T)constructor.newInstance(parameters);
        componentMap.put(componentClass, componentInstance);
        componentInstance.onAttached(this);

        return componentInstance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends  C> T forceAttachComponent(Class<T> componentClass, Object... parameters) {
        try {
            return attachComponent(componentClass, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> Optional<T> removeComponent(Class<T> componentClass) {
        Optional<T> component = getComponent(componentClass);
        if (component.isPresent()) {
            component.get().onDetached();
        }

        componentMap.remove(componentClass);

        return component;
    }
}
