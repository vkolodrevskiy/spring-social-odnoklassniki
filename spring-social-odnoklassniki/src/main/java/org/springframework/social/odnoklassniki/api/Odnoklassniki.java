/*
 * Copyright 2016-2017 the original author or authors.
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
package org.springframework.social.odnoklassniki.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.odnoklassniki.api.impl.OdnoklassnikiTemplate;
import org.springframework.web.client.RestOperations;

/**
 * Interface specifying a basic set of operations for interacting with GitHub.
 * Implemented by {@link OdnoklassnikiTemplate}.
 *
 * @author vkolodrevskiy
 */
public interface Odnoklassniki extends ApiBinding {

    /**
     * Returns the portion of the Odnoklassniki API containing the user operations.
     *
     * @return user operations
     */
    UsersOperations usersOperations();

    /**
     * Returns the underlying {@link RestOperations} object allowing for consumption of LinkedIn endpoints that may not be otherwise covered by the API binding.
     * The RestOperations object returned is configured to include an OAuth "Authorization" header on all requests.
     * @return a {@link RestOperations} for work against the LinkedIn API at a low-level.
     */
    RestOperations restOperations();
}
