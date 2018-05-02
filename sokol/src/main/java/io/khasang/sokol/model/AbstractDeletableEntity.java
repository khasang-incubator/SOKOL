package io.khasang.sokol.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AbstractDeletableEntity extends AbstractBaseEntity {

    @Column(name = "IS_DELETED")
    private boolean deleted = false;
}
