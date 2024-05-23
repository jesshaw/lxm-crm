package com.lexiangmaio.crm.security;

import com.lexiangmaio.crm.domain.Authority;
import com.lexiangmaio.crm.domain.Resource;
import com.lexiangmaio.crm.domain.User;
import com.lexiangmaio.crm.repository.ResourceRepository;
import com.lexiangmaio.crm.repository.UserRepository;
import java.util.*;
import java.util.stream.Collectors;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public DomainUserDetailsService(UserRepository userRepository, ResourceRepository resourceRepository) {
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            return userRepository
                .findOneWithAuthoritiesByEmailIgnoreCase(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository
            .findOneWithAuthoritiesByLogin(lowercaseLogin)
            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<String> authorityies = user.getAuthorities().stream().map(Authority::getName).toList();
        List<SimpleGrantedAuthority> grantedAuthorities = authorityies.stream().map(SimpleGrantedAuthority::new).toList();
        //不使用Authority中的Resources一对多的方式获取，是因为验证发现存在不确定性
        Set<Resource> resources = new HashSet<>(resourceRepository.findAllByAuthorityNameIn(authorityies));
        return new CustomUserDetails(user.getLogin(), user.getPassword(), grantedAuthorities, resources);
    }
}
