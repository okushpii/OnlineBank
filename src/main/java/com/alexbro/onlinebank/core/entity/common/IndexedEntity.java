package com.alexbro.onlinebank.core.entity.common;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IndexedEntity extends BaseEntity{

    @NaturalId
    @Column(nullable = false, unique = true, updatable = false)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
