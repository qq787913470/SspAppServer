package com.yada.ssp.appServer.service;

import com.yada.ssp.appServer.dao.MerchantDao;
import com.yada.ssp.appServer.model.Merchant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MerchantService.class)
public class MerchantServiceTest {

    @MockBean
    private MerchantDao merchantDao;
    @Autowired
    private MerchantService merchantService;

    @Test
    public void getSubMer() {
        Mockito.when(merchantDao.getOne(Mockito.anyString())).then((Answer<Merchant>) invocation -> {
            String merNo = invocation.getArgument(0).toString();
            Merchant mer = new Merchant();
            switch (merNo) {
                case "0":
                    mer.setChildren(null);
                    break;
            }
            return mer;
        });

        Map<String, String> subMer = merchantService.getSubMer("0");
        Assert.assertEquals(0, subMer.size());
    }

    @Test
    public void checkSubMer() {
        Mockito.when(merchantDao.findById(Mockito.anyString())).then((Answer<Optional<Merchant>>) invocation -> {
            String subMerNo = invocation.getArgument(0).toString();
            Merchant mer = new Merchant();
            Merchant subMer = new Merchant();
            switch (subMerNo) {
                case "2":
                    subMer.setMerType("2");
                    mer.setMerNo("1");
                    subMer.setMerchant(mer);
                    break;
                case "3":
                    subMer.setMerType("2");
                    mer.setMerNo("error");
                    subMer.setMerchant(mer);
                    break;
                case "4":
                    subMer.setMerType("2");
                    subMer.setMerchant(null);
                    break;
                case "5":
                    subMer.setMerType("0");
                    mer.setMerNo("1");
                    subMer.setMerchant(mer);
                    break;
            }
            return Optional.of(subMer);
        });

        Assert.assertTrue(merchantService.checkSubMer("1", "1"));
        Assert.assertTrue(merchantService.checkSubMer("1", "2"));
        Assert.assertFalse(merchantService.checkSubMer("1", "3"));
        Assert.assertFalse(merchantService.checkSubMer("1", "4"));
        Assert.assertFalse(merchantService.checkSubMer("1", "5"));
        Assert.assertFalse(merchantService.checkSubMer("1", "6"));
    }
}