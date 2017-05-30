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

package io.khasang.sokol.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENTS")
public class Department extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DEPARTMENT_ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

/*    @OneToMany(mappedBy = "department")
    private List<Request> requests = new ArrayList<>();*/

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestType> requestTypes = new ArrayList<>();

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RequestType> getRequestTypes() {
        return requestTypes;
    }

    public void setRequestTypes(List<RequestType> requestTypes) {
        this.requestTypes = requestTypes;
    }

    /*    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public List<RequestType> getRequestTypes() {
        return requestTypes;
    }*/

/*    public void addRequestType(RequestType requestType) {
        requestTypes.add(requestType);
        requestType.setDepartment(this);
    }

    public void removeRequestType(RequestType requestType) {
        requestTypes.remove(requestType);
        requestType.setDepartment(null);
    }*/

/*    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }*/

/*    public void setRequestTypes(List<RequestType> requestTypes) {
        this.requestTypes = requestTypes;
    }*/
}
