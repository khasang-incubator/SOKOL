/*
 * Copyright 2016-2018 Sokol Development Team
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
package io.khasang.sokol.repository;

import io.khasang.sokol.model.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {
    List<RequestType> findAllByDeletedIsFalse();
}
