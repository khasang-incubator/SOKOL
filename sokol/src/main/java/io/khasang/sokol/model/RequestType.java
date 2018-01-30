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

@Entity
@Table(name = "REQUEST_TYPES")
public class RequestType extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REQUEST_TYPE_ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

/*    @OneToMany(mappedBy = "requestType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests = new ArrayList<>();*/

//    @OneToMany(mappedBy = "requestType", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Field> fields = new HashSet<>();

/*    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", foreignKey = @ForeignKey(name = "DEPARTMENT_ID_FK"))
    private Department department;*/

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tittle) {
        this.title = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
