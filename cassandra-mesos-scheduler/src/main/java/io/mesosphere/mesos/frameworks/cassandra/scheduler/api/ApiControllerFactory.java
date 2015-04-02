/**
 *    Copyright (C) 2015 Mesosphere, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mesosphere.mesos.frameworks.cassandra.scheduler.api;

import com.google.common.collect.Sets;
import io.mesosphere.mesos.frameworks.cassandra.scheduler.CassandraCluster;

import java.util.Set;

public final class ApiControllerFactory {

    private ApiControllerFactory() {}

    public static Set<Object> buildInstances(CassandraCluster cassandraCluster, String cassandraVersion) {
        Set<Object> set = Sets.<Object>newHashSet(
            new FileResourceController(cassandraVersion));
        set.addAll(buildInstancesWithoutFiles(cassandraCluster));
        return set;
    }

    public static Set<Object> buildInstancesWithoutFiles(CassandraCluster cassandraCluster) {
        return Sets.<Object>newHashSet(
            new ApiController(cassandraCluster),
            new ClusterJobsController(cassandraCluster),
            new ConfigAndStatusController(cassandraCluster),
            new LiveEndpointsController(cassandraCluster),
            new NodeStateController(cassandraCluster),
            new ScaleOutController(cassandraCluster),
            new SeedNodesController(cassandraCluster)
        );
    }
}
