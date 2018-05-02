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
package io.khasang.sokol.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "REQUESTS")
@Getter
@Setter
public class Request extends AbstractBaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REQUEST_ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_TO")
    private User assignedTo;

    @Column(name = "ASSIGNED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedDate;

    @ManyToOne
    @JoinColumn(name = "REQUEST_STATUS_ID")
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "REQUEST_TYPE_ID")
    private RequestType requestType;

    @Version
    private int version;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE")
    private byte[] file;

    public Request() {
        super();
    }
}


