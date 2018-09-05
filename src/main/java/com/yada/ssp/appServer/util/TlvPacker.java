package com.yada.ssp.appServer.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TlvPacker {

    /**
     * 将tlv 转换为map
     *
     * @param tlv 字符串
     * @return 解析完成后的map
     */
    public static Map<String, String> unPacker(String tlv) {
        Map<String, String> map = new HashMap<>();
        int current = 0;
        while (current < tlv.length()) {
            String tag = tlv.substring(current, current + 3);
            int len = Integer.parseInt(tlv.substring(current + 3, current + 3 + 4));
            String value = tlv.substring(current + 3 + 4, current + 3 + 4 + len);
            map.put(tag, value);
            current = current + 3 + 4 + len;
        }
        return map;
    }

    /**
     * 将map转换为tlv
     *
     * @param map map
     * @return 转换的tlv String
     */
    public static String packer(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            String len = StringUtils.leftPad(String.valueOf(map.get(key).length()), 4, "0");
            sb.append(key);
            sb.append(len);
            sb.append(map.get(key));
        }
        return sb.toString();
    }
}
