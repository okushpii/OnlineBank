package com.alexbro.onlinebank.core.entity.config;

import com.alexbro.onlinebank.core.entity.common.BaseEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Configuration extends BaseEntity {

    @Column(name = "configKey")
    @NaturalId
    private String key;

    @Column
    private String value;

    @Column(name = "configGroup")
    private String group;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
