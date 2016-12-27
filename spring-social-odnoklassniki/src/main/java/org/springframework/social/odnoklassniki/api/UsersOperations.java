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
package org.springframework.social.odnoklassniki.api;

/**
 * User operations.
 *
 * @author vkolodrevskiy
 */
public interface UsersOperations {

    /**
     * Retrieves the profile for the authenticated user.
     *
     * @return the user's profile information.
     * @throws org.springframework.social.ApiException if there is an error while communicating with Odnoklassniki.
     * @throws org.springframework.social.MissingAuthorizationException if OdnoklassnikiTemplate was not created with an access token.
     */
    OdnoklassnikiProfile getProfile();
}
