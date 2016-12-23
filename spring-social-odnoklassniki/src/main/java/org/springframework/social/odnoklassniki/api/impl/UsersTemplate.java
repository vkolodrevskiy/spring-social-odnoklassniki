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

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.social.odnoklassniki.api.OdnoklassnikiProfile;
import org.springframework.social.odnoklassniki.api.UsersOperations;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * User operations.
 * @author vkolodrevskiy
 */
public class UsersTemplate extends AbstractOdnoklassnikiOperations implements UsersOperations {

    private static final String METHOD = "users.getCurrentUser";

    private final RestTemplate restTemplate;

    public UsersTemplate(String applicationKey, String clientSecret, RestTemplate restTemplate,
        String accessToken, boolean isAuthorizedForUser) {

        super(applicationKey, clientSecret, accessToken, isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    @Override
    public OdnoklassnikiProfile getProfile() {
        requireAuthorization();

        Map<String, String> params = new HashMap<String, String>();
        params.put("method", METHOD);
        URI uri = URIBuilder.fromUri(makeOperationURL(params)).build();

        Map<String, String> profiles = restTemplate.getForObject(uri, Map.class);
        //checkForError(profiles);

        OdnoklassnikiProfile profile = new OdnoklassnikiProfile(profiles.get("uid"),
            profiles.get("first_name"), profiles.get("last_name"), profiles.get("email"),
            "http://odnoklassniki.ru?id=" + profiles.get("uid"));

        profile.setPhoto(profiles.get("pic_1"));

        return profile;
    }
}
