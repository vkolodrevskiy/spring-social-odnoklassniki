/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.odnoklassniki.config.xml;

import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.odnoklassniki.config.support.OdnoklassnikiApiHelper;
import org.springframework.social.odnoklassniki.connect.OdnoklassnikiConnectionFactory;
import org.springframework.social.odnoklassniki.security.OdnoklassnikiAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * Implementation of {@link AbstractProviderConfigBeanDefinitionParser}
 * that creates a {@link OdnoklassnikiConnectionFactory}.
 *
 * @author vkolodrevskiy
 */
class OdnoklassnikiConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {

    public OdnoklassnikiConfigBeanDefinitionParser() {
        super(OdnoklassnikiConnectionFactory.class, OdnoklassnikiApiHelper.class);
    }

    @Override
    protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
        return OdnoklassnikiAuthenticationService.class;
    }
}
