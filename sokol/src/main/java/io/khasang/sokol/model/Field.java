/*
 * Copyright 2016-2017 Sokol Development Team
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

package io.khasang.sokol.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FIELDS")
public class Field extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FIELD_ID")
    private Integer fieldId;
    @Column(name = "FIELD_NAME")
    private String fieldName;
    @ManyToOne
    @JoinColumn(name = "FIELD_TYPE_ID")
    private FieldType fieldType;
    @ManyToOne
    @JoinColumn(name = "REQUEST_TYPE_ID")
    private RequestType requestType;

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }
}
