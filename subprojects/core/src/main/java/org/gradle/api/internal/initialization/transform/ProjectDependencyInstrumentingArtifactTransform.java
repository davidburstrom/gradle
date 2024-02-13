/*
 * Copyright 2023 the original author or authors.
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

package org.gradle.api.internal.initialization.transform;

import org.gradle.api.artifacts.transform.InputArtifact;
import org.gradle.api.file.FileSystemLocation;
import org.gradle.api.provider.Provider;
import org.gradle.api.tasks.Classpath;
import org.gradle.internal.instrumentation.api.types.BytecodeInterceptorFilter;
import org.gradle.internal.vfs.FileSystemAccess;
import org.gradle.work.DisableCachingByDefault;

import java.io.File;

/**
 * Artifact transform that instruments project based plugins with Gradle instrumentation.
 */
@DisableCachingByDefault(because = "Instrumented jars are too big to cache.")
public abstract class ProjectDependencyInstrumentingArtifactTransform extends BaseInstrumentingArtifactTransform {

    @Override
    @Classpath
    @InputArtifact
    public abstract Provider<FileSystemLocation> getInput();

    @Override
    protected BytecodeInterceptorFilter provideInterceptorFilter() {
        return BytecodeInterceptorFilter.INSTRUMENTATION_ONLY;
    }

    @Override
    protected File inputArtifact(FileSystemAccess fileSystemAccess) {
        return getInput().get().getAsFile();
    }
}
