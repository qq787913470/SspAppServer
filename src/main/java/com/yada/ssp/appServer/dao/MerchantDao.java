package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MerchantDao extends JpaRepository<Merchant, String>, CrudRepository<Merchant, String> {
}