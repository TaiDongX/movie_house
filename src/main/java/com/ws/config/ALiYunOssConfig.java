package com.ws.config;

import io.lettuce.core.dynamic.annotation.Value;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.22 11:19
 * @since JDK 1.8
 */
@Component
/*@PropertySource(value = "classpath:application.properties")
@ConfigurationProperties(prefix = "aliyun.oss")*/

public class ALiYunOssConfig  {
    private String bucketname;
    private String endpoint;
    private String keyid;
    private String keysecret;
    private String filehost;

    public String getBucketname() {
        return bucketname;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getKeysecret() {
        return keysecret;
    }

    public void setKeysecret(String keysecret) {
        this.keysecret = keysecret;
    }

    public String getFilehost() {
        return filehost;
    }

    public void setFilehost(String filehost) {
        this.filehost = filehost;
    }
}
