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
package org.springframework.social.odnoklassniki.api.impl;

import org.springframework.social.odnoklassniki.api.OdnoklassnikiProfile;
import org.springframework.social.odnoklassniki.api.UsersOperations;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * User operations.
 *
 * @author vkolodrevskiy
 */
public class UsersTemplate extends AbstractOdnoklassnikiOperations implements UsersOperations {

    private final RestTemplate restTemplate;

    public UsersTemplate(RestTemplate restTemplate, String accessToken, String applicationKey, String clientSecret,
        boolean isAuthorizedForUser) {

        super(applicationKey, clientSecret, accessToken, isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public OdnoklassnikiProfile getProfile() {
        requireAuthorization();
        Map<String, String> data = new HashMap<>();
        data.put("fields", DEFAULT_FIELDS);
        URI uri = URIBuilder.fromUri(makeOperationURL("users.getCurrentUser", data)).build();
        OdnoklassnikiProfile profile = restTemplate.getForObject(uri, OdnoklassnikiProfile.class);

        return profile;
    }
}
