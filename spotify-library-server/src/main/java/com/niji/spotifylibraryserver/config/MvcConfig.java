/**
 * Copyright (c) 2015-2019 AriadNEXT - All Rights Reserved. NOTICE: All information contained herein is, and remains the property of AriadNEXT and its
 * suppliers, if any. The intellectual and technical concepts contained herein are proprietary to AriadNEXT and its suppliers and may be covered by U.E. and
 * Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained from AriadNEXT.
 */
package com.niji.spotifylibraryserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Spring web mvc shortcuts
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui/index.html");
        registry.addRedirectViewController("/swagger-ui/", "/swagger-ui/index.html");
    }
}
