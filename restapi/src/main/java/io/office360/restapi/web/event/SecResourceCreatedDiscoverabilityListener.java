package io.office360.restapi.web.event;

import io.office360.common.web.IUriMapper;
import io.office360.common.web.WebConstants;
import io.office360.common.web.listeners.ResourceCreatedDiscoverabilityListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: #1 Check usage
@Component
class SecResourceCreatedDiscoverabilityListener extends ResourceCreatedDiscoverabilityListener {

    @Autowired
    public SecResourceCreatedDiscoverabilityListener(IUriMapper uriMapper) {
        super(uriMapper);
    }

    //

    @Override
    protected final String getBase() {
        return WebConstants.PATH_SEP;
    }

}
