package be.ictdynamic.services;

import org.springframework.stereotype.Service;

/**
 * Class SecurityServiceImpl.
 *
 * @author Wim Van den Brande
 * @since 19/01/2016 - 8:21
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean isButtonAllowed(String nameOfPage) {
        return true;
    }
}
