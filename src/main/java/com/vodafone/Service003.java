package com.vodafone;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

@RestController
@RequestMapping("/service")
public class Service003 {

   // @CachePut(value="echo", condition = "#noCache", key = "#root.methodName")
    // @Cacheable(value="echo", condition = "!#noCache", key = "#in")
    @Cacheable(value="echo")
    @RequestMapping(value = "/echo/{in}", method = RequestMethod.GET)
    public String echo(@PathVariable(value = "in") final String in, @AuthenticationPrincipal final UserDetails user) {
        return "Connected with: " + user.getUsername() + ". You have sent: " + in;
    }

    @CacheEvict(value="echo", allEntries=true)
    @RequestMapping(value = "/refresh", method = RequestMethod.DELETE)
    public void clearCache(final String in, @AuthenticationPrincipal final UserDetails user) {
    }
}
