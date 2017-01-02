/*
 * Copyright 2017 the original author or authors.
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

import org.springframework.social.odnoklassniki.api.FriendsOperations;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Friends operations.
 *
 * @author vkolodrevskiy
 */
public class FriendsTemplate extends AbstractOdnoklassnikiOperations implements FriendsOperations {
    private final RestTemplate restTemplate;

    public FriendsTemplate(RestTemplate restTemplate, String accessToken, String applicationKey,
                           String applicationSecretKey, boolean isAuthorizedForUser) {
        super(applicationKey, applicationSecretKey, accessToken, isAuthorizedForUser);
        this.restTemplate = restTemplate;
    }

    @Override
    public String get(String fid) {
        Map<String, String> data = new HashMap<>();
        if (!StringUtils.isEmpty(fid)) {
            data.put("fid", fid);
        }

        URI uri = URIBuilder.fromUri(makeOperationURL("friends.get", data)).build();
        String friends = restTemplate.getForObject(uri, String.class);

        return friends;
    }
}
