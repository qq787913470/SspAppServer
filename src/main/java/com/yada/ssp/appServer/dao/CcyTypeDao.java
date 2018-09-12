package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.CcyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bjy on 2018/9/5.
 * 币种DAO
 */
public interface CcyTypeDao extends JpaRepository<CcyType, String>, CrudRepository<CcyType, String> {
}
