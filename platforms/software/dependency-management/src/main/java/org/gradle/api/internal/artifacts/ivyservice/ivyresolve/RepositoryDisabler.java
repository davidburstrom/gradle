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

package org.gradle.api.internal.artifacts.ivyservice.ivyresolve;

import java.util.Optional;

public interface RepositoryDisabler {

    boolean isDisabled(String repositoryId);

    /**
     * Gets the reason why the repository was disabled, if it was disabled.
     *
     * @param repositoryId The id of the repository to check
     * @return the reason why the repository was disabled, if it was disabled; otherwise, an empty {@link Optional}
     */
    Optional<Throwable> getDisabledReason(String repositoryId);

    /**
     * Disables the repository with the given id, recording the exception causing it to be disabled.
     *
     * @param repositoryId The id of the repository to disable
     * @param throwable    The reason why the repository is being disabled
     * @return {@code true} if the repository was disabled, {@code false} if it was already disabled
     */
    boolean disableRepository(String repositoryId, Throwable throwable);

    enum NoOpDisabler implements RepositoryDisabler {
        INSTANCE;

        @Override
        public boolean isDisabled(String repositoryId) {
            return false;
        }

        @Override
        public Optional<Throwable> getDisabledReason(String repositoryId) {
            return Optional.empty();
        }

        @Override
        public boolean disableRepository(String repositoryId, Throwable throwable) {
            return false;
        }
    }
}
