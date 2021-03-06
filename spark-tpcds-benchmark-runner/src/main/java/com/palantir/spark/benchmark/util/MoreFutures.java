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

package com.palantir.spark.benchmark.util;

import com.palantir.logsafe.exceptions.SafeRuntimeException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class MoreFutures {
    private MoreFutures() {
        // utility class.
    }

    public static <T> T join(Future<T> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SafeRuntimeException(e);
        } catch (ExecutionException e) {
            throw new SafeRuntimeException("Encountered an ExecutionException", e.getCause());
        }
    }
}
