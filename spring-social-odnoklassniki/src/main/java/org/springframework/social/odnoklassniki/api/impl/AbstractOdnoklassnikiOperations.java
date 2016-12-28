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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Generic class for operation templates.
 *
 * @author vkolodrevskiy
 */
public abstract class AbstractOdnoklassnikiOperations {
    private final static Log log = LogFactory.getLog(AbstractOdnoklassnikiOperations.class);

    private static final String OK_REST_URL = "http://api.ok.ru/fb.do?";

    private final SortedMap<String, String> params = new TreeMap<>(String::compareTo);

    private final boolean isAuthorized;
    private final String accessToken;
    private final String applicationSecretKey;

    public AbstractOdnoklassnikiOperations(String applicationKey, String applicationSecretKey, String accessToken, boolean isAuthorized) {

        this.isAuthorized = isAuthorized;
        this.accessToken = accessToken;
        this.applicationSecretKey = applicationSecretKey;

        params.put("application_key", applicationKey);
        params.put("access_token", this.accessToken);
        params.put("format", "json");
    }

    protected void requireAuthorization() {
        if (!isAuthorized) {
            throw new MissingAuthorizationException("odnoklassniki");
        }
    }

    protected String makeOperationURL(String method, Map<String, String> params) {
        this.params.put("method", method);
        this.params.putAll(params);

        StringBuilder url = new StringBuilder(OK_REST_URL);
        StringBuilder signature = new StringBuilder();

        for (String param : this.params.keySet()) {
            String value = this.params.get(param);
            if (!param.equals("access_token")) {
                signature.append(param).append("=").append(value);
            }
            try {
                url.append(param).append("=").append(URLEncoder.encode(value, "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                log.error("Error while encoding url parameter.", e);
            }
        }
        signature.append(encodeSignature(accessToken + applicationSecretKey));
        url.append("sig=").append(encodeSignature(signature.toString()));

        return url.toString();
    }

    private String encodeSignature(String signature) {
        return DigestUtils.md5DigestAsHex(signature.getBytes()).toLowerCase();
    }
}
