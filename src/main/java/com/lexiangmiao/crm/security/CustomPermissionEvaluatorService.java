package com.lexiangmiao.crm.security;

import com.lexiangmiao.crm.domain.Resource;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
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
     * @param resource resource name
     * @param access permission
     * @return has permission
     */
    public boolean hasPermission(String resource, String access) {
        CustomUserDetails user = getCurrentUser();
        return user != null && hasAccessToResource(user, access, resource);
    }

    /**
     * get the current user token from spring SecurityContextHolder
     * @return CustomUserDetails
     */
    private CustomUserDetails getCurrentUser() {
        // TODO: 2024/9/9 Currently fetching data from the DB, can be optimized later
        return SecurityUtils.getCurrentUserLogin()
            .map(login -> (CustomUserDetails) domainUserDetailsService.loadUserByUsername(login))
            .orElseThrow(() -> new AccessDeniedException("No Valid User Found"));
    }

    /**
     * checks if the resource has grants in any roles for the user
     *
     * @param user current user
     * @param access permission
     * @param resourceName resource name
     * @return has access permission
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
     * @param resource resource entity
     * @param resourceName resource name
     * @param access permission
     * @return has grant resource permission
     */
    private boolean hasGrants(Resource resource, String resourceName, String access) {
        if (!resourceName.equals(resource.getName())) return false;
        return resource.getPermission().equals(access);
    }
}
