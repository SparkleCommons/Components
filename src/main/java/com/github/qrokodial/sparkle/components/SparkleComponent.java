package com.github.qrokodial.sparkle.components;

import java.util.Optional;

public class SparkleComponent<H extends ComponentHolder> implements Component<H> {
    private Optional<H> holder;

    /**
     * {@inheritDoc}
     */
    public void onAttached(H holder) {
        this.holder = Optional.of(holder);
        onRegistered();
    }

    /**
     * {@inheritDoc}
     */
    public void onDetached() {
        this.holder = Optional.empty();
    }

    /**
     * Called when the component is registered to a holder.
     */
    public void onRegistered() {
    }

    /**
     * {@inheritDoc}
     */
    public Optional<H> getHolder() {
        return holder;
    }
}
