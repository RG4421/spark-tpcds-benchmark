/*
 * (c) Copyright 2020 Palantir Technologies Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.palantir.spark.benchmark.config;

import static com.palantir.logsafe.Preconditions.checkArgument;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.palantir.spark.benchmark.immutables.ImmutablesConfigStyle;
import org.immutables.value.Value;

@Value.Immutable
@ImmutablesConfigStyle
@JsonDeserialize(as = ImmutableDataGenerationConfiguration.class)
public interface DataGenerationConfiguration {
    int parallelism();

    String tempWorkingDir();

    SourceDataGenerationConfig tpcds();

    SourceDataGenerationConfig gensort();

    boolean overwriteData();

    @Value.Check
    default void check() {
        checkArgument(parallelism() > 0, "Data generation parallelism must be positive.");
    }
}