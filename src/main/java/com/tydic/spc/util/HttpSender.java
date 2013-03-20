package com.tydic.spc.util;


import com.tydic.spc.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送 HTTP 请求并返回结果。每个 HttpSender
 * 对象用于向一个地址发送，每次发送可以带不同的参数。
 *
 */
public class HttpSender {

    static final Logger log = LoggerFactory.getLogger(HttpSender.class);

    private String url;

    private String lastResponseContent = "";

    private Map<String, List<String>> parameters = new HashMap<String, List<String>>();

    /**
     * HTTP 响应状态码
     */
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public String getUrl() {
        return url;
    }

    public String getLastResponseContent() {
        return lastResponseContent;
    }

    /**
     * 构造函数
     *
     * @param url 要发送请求的地址
     */
    public HttpSender(String url) {
        this.url = url;
    }

    public HttpSender put(String name, String... values) {
        if (values != null) {
            List<String> params = parameters.get(name);
            if (params == null) {
                params = new ArrayList<String>();
                parameters.put(name, params);
            }

            for (String value : values) {
                if (value != null) {
                    params.add(value);
                }
            }
        }
        return this;
    }

    public void send() throws ApplicationException {
        log.debug("请求地址：" + url);
        sendSinglePartRequest();
    }


    private void sendSinglePartRequest() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setDoOutput(true);
            writeParams(connection.getOutputStream());

            this.statusCode = connection.getResponseCode();

            try {
                inputStream = connection.getInputStream();
                this.lastResponseContent = read(inputStream);
            } catch (IOException e) {
                if (connection.getErrorStream() != null) {
                    this.lastResponseContent = read(connection.getErrorStream());
                }
                throw new ApplicationException("请求 " + url + " 失败: " + e.getMessage(), e);
            }


            
        } catch (IOException e) {
            throw new ApplicationException("连接 " + url + " 失败: " + e.getMessage(), e);
        } finally {
            CloseableUtils.close(inputStream, outputStream);
        }
    }

    private void writeParams(OutputStream outputStream) {
        String string = "";
        for (String key : parameters.keySet()) {
            List<String> values = parameters.get(key);
            for (String value : values) {
                if (value != null) {
                    string += key + "=" + StringUtils.encodeParamValue(value) + "&";
                }
            }
        }
        string = StringUtils.removeEnd(string, "&");

        log.debug("参数：" + string);

        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        try {
            writer.write(string);
            writer.flush();
        } catch (IOException e) {
            throw new ApplicationException(e);
        } finally {
            CloseableUtils.close(writer);
        }
    }

    private String read(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

}
