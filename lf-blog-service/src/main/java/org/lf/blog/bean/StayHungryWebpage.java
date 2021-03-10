package org.lf.blog.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Created by LF on 2020/09/24.
 */
@Configuration
@PropertySource("classpath:webpage.properties")
@ConfigurationProperties(prefix = "webpage")
public class StayHungryWebpage {
    private Integer workImgWidth;
    private Integer workImgHeight;
    private Integer workBigImgWidth;
    private Integer workBigImgHeight;

    public Integer getWorkImgWidth() {
        return workImgWidth;
    }

    public void setWorkImgWidth(Integer workImgWidth) {
        this.workImgWidth = workImgWidth;
    }

    public Integer getWorkImgHeight() {
        return workImgHeight;
    }

    public void setWorkImgHeight(Integer workImgHeight) {
        this.workImgHeight = workImgHeight;
    }

    public Integer getWorkBigImgWidth() {
        return workBigImgWidth;
    }

    public void setWorkBigImgWidth(Integer workBigImgWidth) {
        this.workBigImgWidth = workBigImgWidth;
    }

    public Integer getWorkBigImgHeight() {
        return workBigImgHeight;
    }

    public void setWorkBigImgHeight(Integer workBigImgHeight) {
        this.workBigImgHeight = workBigImgHeight;
    }
}
