package com.qibao.model.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by giozola on 2017/7/7.
 */
public class BaseDomain implements Serializable{
    private static final long serialVersionUID = -5386818304619762871L;
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    /**
     * 获取主键ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }
}
