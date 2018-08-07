package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.MerchantDao;
import com.yada.ssp.appServer.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MerchantService {

    private final MerchantDao merchantDao;

    @Autowired
    public MerchantService(MerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }


    /**
     * 获取下级商户
     *
     * @param merNo 集团商户号
     * @return 下级商户
     */
    public Map<String, String> getSubMer(String merNo) {
        Map<String, String> subMer = new HashMap<>();
        Merchant merchant = merchantDao.findById(merNo).orElse(new Merchant());
        if (merchant.getMerNo() != null) {
            subMer.put(merchant.getMerNo(), merchant.getMerName());
        }
        if ("1".equals(merchant.getMerType()) && merchant.getChildren() != null) {
            for (Merchant mer : merchant.getChildren()) {
                subMer.put(mer.getMerNo(), mer.getMerName());
            }
        }
        return subMer;
    }

    /**
     * 验证两个商户是否有层级关系
     *
     * @param merNo    父级商户
     * @param subMerNo 子级商户
     * @return boolean
     */
    public boolean checkSubMer(String merNo, String subMerNo) {
        // 自己是自己的子商户
        if (merNo.equals(subMerNo)) {
            return true;
        }

        Merchant subMer = merchantDao.findById(subMerNo).orElse(new Merchant());
        return subMer.getMerType() != null && "2".equals(subMer.getMerType())
                && subMer.getMerchant() != null && merNo.equals(subMer.getMerchant().getMerNo());
    }
}
