package io.office360.common.web.listeners;

import com.google.common.base.Preconditions;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public abstract class AbstractOffice360Listener<E extends ApplicationEvent> implements ApplicationListener<E> {

    public AbstractOffice360Listener() {
        super();
    }

    //

    @Override
    public final void onApplicationEvent(E ev) {
        Preconditions.checkNotNull(ev);

        addLinkHeader(ev);
    }

    protected abstract void addLinkHeader(E ev);
}
