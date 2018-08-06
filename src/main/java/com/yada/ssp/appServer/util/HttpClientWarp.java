package com.yada.ssp.appServer.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class HttpClientWarp {

    private HttpClientBuilder clientBuilder;

    public HttpClientWarp() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(256);
        cm.setDefaultMaxPerRoute(256);

        ConnectionKeepAliveStrategy keepAliveStrategy = new DefaultConnectionKeepAliveStrategy() {

            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAliveDuration = super.getKeepAliveDuration(response, context);
                if (keepAliveDuration == -1) {
                    keepAliveDuration = 10 * 1000; // 45 seconds
                }
                return keepAliveDuration;
            }
        };

        clientBuilder = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(keepAliveStrategy);
    }

    public String get(String url, String token) {
        CloseableHttpClient client = clientBuilder.build();
        try {
            HttpGet req = new HttpGet(new URI(url));
            req.addHeader("Cache-Control", "no-cache");
            req.addHeader("Authorization", "Bearer " + token);
            HttpResponse response = client.execute(req);
            return EntityUtils.toString(response.getEntity());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String post(String url, List<NameValuePair> data) throws IOException {
        CloseableHttpClient client = clientBuilder.build();

        HttpPost httpPost = new HttpPost(url);

        HttpEntity postEntity = new UrlEncodedFormEntity(data);
        httpPost.addHeader("Cache-Control", "no-cache");
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = client.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity);
    }
}
