package com.egitimasistanim.backend.entity.audit;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditJustCreatedDate<T> {

    public AuditJustCreatedDate(){

    }

    public AuditJustCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    protected Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
