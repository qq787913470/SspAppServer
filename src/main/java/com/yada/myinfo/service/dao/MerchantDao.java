package com.yada.myinfo.service.dao;

import com.yada.myinfo.service.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantDao extends JpaRepository<Merchant, String> {
}