package com.yada.ssp.appServer.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TlvPackerTest {

    @Test
    public void unPacker() {
        String tlv = "00200191234567890123456789";
        Map<String, String> map = TlvPacker.unPacker(tlv);
        assertEquals("1234567890123456789", map.get("002"));
    }

    @Test
    public void packer() {
        Map<String, String> map = new HashMap<>();
        map.put("002", "1234567890123456789");
        String tlv = TlvPacker.packer(map);
        assertEquals("00200191234567890123456789", tlv);
    }
}