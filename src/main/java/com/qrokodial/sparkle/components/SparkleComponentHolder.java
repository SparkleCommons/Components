package com.qrokodial.sparkle.components;

import com.qrokodial.sparkle.utilities.collections.ArrayUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public abstract class SparkleComponentHolder implements ComponentHolder {
    protected Map<Class<? extends Component>, Component> componentMap;

    /**
     * Instantiates the class.
     */
    public SparkleComponentHolder(Map<Class<? extends Component>, Component> componentMap) {
        this.componentMap = componentMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasComponent(Class<? extends Component> componentClass) {
        return componentMap.containsKey(componentClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Component> Optional<T> getComponent(Class<T> componentClass) {
        Component component = componentMap.get(componentClass);

        if (component == null) {
            return Optional.empty();
        }

        return Optional.of((T)component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Component> getComponents() {
        return componentMap.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Collection<T> getComponents(Class<T> type) {
        Collection<T> matches = new ArrayList<>();

        componentMap.forEach((componentClass, component) -> {
            if (type.isAssignableFrom(componentClass)) {
                matches.add((T)component);
            }
        });

        return matches;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Component> getComponents(Class<?>... types) {
        Collection<Component> matches = new ArrayList<>();

        componentMap.forEach((componentClass, component) -> {
            for (Class<?> type : types) {
                if (type.isAssignableFrom(componentClass)) {
                    matches.add(component);
                    break;
                }
            }
        });

        return matches;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Component> T attachComponent(Class<T> componentClass, Object... parameters) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
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
    public <T extends  Component> T forceAttachComponent(Class<T> componentClass, Object... parameters) {
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
    public <T extends Component> Optional<T> removeComponent(Class<T> componentClass) {
        Optional<T> component = getComponent(componentClass);
        if (component.isPresent()) {
            component.get().onDetached();
        }

        componentMap.remove(componentClass);

        return component;
    }
}
