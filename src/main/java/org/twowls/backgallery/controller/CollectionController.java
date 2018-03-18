package org.twowls.backgallery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.twowls.backgallery.model.CollectionDescriptor;
import org.twowls.backgallery.model.RealmDescriptor;
import org.twowls.backgallery.model.RealmOperation;
import org.twowls.backgallery.service.RealmAuthenticator;
import org.twowls.backgallery.service.RealmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>TODO add documentation...</p>
 *
 * @author Dmitry Chubarov
 */
@RestController
public class CollectionController {
    private static final Logger logger = LoggerFactory.getLogger(CollectionController.class);
    private final RealmService realmService;

    @Autowired
    CollectionController(RealmService realmService) {
        this.realmService = Objects.requireNonNull(realmService);
    }

    @GetMapping(value = "/{realmName}/{collectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionDescriptor>
    collectionInfo(@PathVariable String realmName, @PathVariable String collectionName,
            HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        RealmDescriptor realm = realmService.findByName(realmName);
        if (authorize(realm, RealmOperation.GET_COLLECTION_INFO, servletRequest, servletResponse)) {
            return ResponseEntity.ok(new CollectionDescriptor());
        }

        return null;
    }

    private boolean authorize(RealmDescriptor realm, RealmOperation requestedOp,
            HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        boolean auth = false;
        RealmAuthenticator authenticator = realmService.authenticatorFor(realm);
        if (authenticator != null) {
            logger.debug("Authenticating with realm \"{}\" using authenticator {}.",
                    realm.description(), authenticator);

            auth = authenticator.authorized(realm, requestedOp, servletRequest);
        }

        if (!auth) {
            logger.warn("Not authorized for operation {}", requestedOp);
            servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return auth;
    }
}
