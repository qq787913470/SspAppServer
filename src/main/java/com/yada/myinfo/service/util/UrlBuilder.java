package com.yada.myinfo.service.util;

import java.util.Map;

public class UrlBuilder {

    public static String buildGetUrl(String url, Map<String,String> query){
        GetUrlBuilder builder = new GetUrlBuilder(url);
        query.forEach(builder::addQuery);
        return builder.getString();
    }

    static class GetUrlBuilder{
        private StringBuffer buffer = new StringBuffer();
        private String flag = "?";

        GetUrlBuilder(String url) {
            buffer.append(url);
        }

        GetUrlBuilder addQuery(String key, String value){
            buffer.append(flag).append(key).append("=").append(value);
            flag = "&";
            return this;
        }

        public String getString(){
            return buffer.toString();
        }
    }
}
