package com.qrokodial.sparkle.components;

import java.util.Optional;

public interface Component<H extends ComponentHolder> {
    /**
     * Called when the component is attached to a holder.
     *
     * @param holder
     */
    void onAttached(H holder);

    /**
     * Called when the component is detached from a holder.
     */
    void onDetached();

    /**
     * Gets the component holder that this component is bound to.
     *
     * @return the component holder
     */
    Optional<H> getHolder();
}
