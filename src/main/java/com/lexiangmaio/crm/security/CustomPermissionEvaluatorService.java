package com.lexiangmaio.crm.security;

import com.lexiangmaio.crm.domain.Resource;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("lxmAuth")
public class CustomPermissionEvaluatorService {

    private final Logger log = LoggerFactory.getLogger(CustomPermissionEvaluatorService.class);
    private final DomainUserDetailsService domainUserDetailsService;

    public CustomPermissionEvaluatorService(DomainUserDetailsService domainUserDetailsService) {
        this.domainUserDetailsService = domainUserDetailsService;
    }

    /**
     * Evaluates permission for the given resource and access
     *
     * @param resource
     * @param access
     * @return
     */
    public boolean hasPermission(String resource, String access) {
        CustomUserDetails user = getCurrentUser();
        return user != null && hasAccessToResource(user, access, resource);
    }

    /**
     * get the current user token from spring SecurityContextHolder
     * @return
     */
    private CustomUserDetails getCurrentUser() {
        // 暂时是从DB取数，后续可优化
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        if (login.isPresent()) {
            return (CustomUserDetails) domainUserDetailsService.loadUserByUsername(login.get());
        } else {
            throw new AccessDeniedException("No Valid User Found");
        }
        //        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //        if (auth.getPrincipal() instanceof UserDetails) {
        //            return (CustomUserDetails) auth.getPrincipal();
        //        } else {
        //            throw new AccessDeniedException("No Valid User Found");
        //        }
    }

    /**
     * checks if the resource has grants in any roles for the user
     *
     * @param user
     * @param access
     * @param resourceName
     * @return
     */
    private boolean hasAccessToResource(CustomUserDetails user, String access, String resourceName) {
        final boolean[] hasAccess = { false };
        Set<Resource> resources = user.getResources();
        if (resources != null && !resources.isEmpty()) {
            resources.forEach(resource -> {
                if (hasGrants(resource, resourceName, access)) {
                    log.debug("Role has access to resources: {} ", resourceName);
                    hasAccess[0] = true;
                }
            });
        }
        return hasAccess[0];
    }

    /**
     * @param resource
     * @param resourceName
     * @param access
     * @return
     */
    private boolean hasGrants(Resource resource, String resourceName, String access) {
        if (!resourceName.equals(resource.getName())) return false;
        return resource.getPermission().equals(access);
    }
}
