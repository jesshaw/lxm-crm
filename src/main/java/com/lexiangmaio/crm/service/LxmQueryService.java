package com.lexiangmaio.crm.service;

import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

@Transactional(readOnly = true)
public abstract class LxmQueryService<T> extends QueryService<T> {

    public LxmQueryService() {}

    @Override
    protected String wrapLikeQuery(String txt) {
        return txt.toUpperCase();
    }
}
