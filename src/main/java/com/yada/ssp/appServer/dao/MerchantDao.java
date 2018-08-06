package com.yada.ssp.appServer.dao;

import com.yada.ssp.appServer.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantDao extends JpaRepository<Merchant, String> {
}