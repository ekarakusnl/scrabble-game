package com.gamecity.scrabble.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Base entity defines common attributes for an entity.
 * 
 * @author ekarakus
 *
 */
@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdateDate;

    /**
     * Returns the id of the entity
     * 
     * @return unique id of the entity
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the entity
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the creation date of the entity
     * 
     * @return creation date as timestamp
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation date of the entity
     * 
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the last update date of the entity
     * 
     * @return last update date as timestamp
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Sets the last update date of the entity
     * 
     * @param lastUpdateDate
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

}
