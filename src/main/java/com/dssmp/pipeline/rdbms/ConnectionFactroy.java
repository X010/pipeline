package com.dssmp.pipeline.rdbms;

import com.dssmp.pipeline.config.PipelineConfiguration;

import java.sql.Connection;
import java.util.Properties;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public abstract class ConnectionFactroy {

    private PipelineConfiguration pipelineConfiguration;

    protected String url;
    protected String username;
    protected String password;



    public ConnectionFactroy(PipelineConfiguration pipelineConfiguration) {
        this.pipelineConfiguration = pipelineConfiguration;
    }


    public ConnectionFactroy(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取链接
     *
     * @return
     */
    public abstract Connection getConnection() throws Exception;

    /**
     * 初始化链接池
     */
    public abstract void init();


    /**
     * 加载配置
     *
     * @return
     */
    protected  Properties loadProperty() {
        Properties properties = new Properties();
        properties.put("url", this.url);
        properties.put("username", this.username);
        properties.put("password", this.password);
        properties.put("maxActive", 20);
        properties.put("initialSize", 10);
        properties.put("maxWait", 12000);
        properties.put("minIdle", 5);
        properties.put("timeBetweenEvictionRunsMillis", 6000);
        properties.put("validationQuery", "SELECT now();");
        properties.put("testWhileIdle", true);
        properties.put("testOnBorrow", false);
        properties.put("testOnReturn", false);
        return properties;
    }
}
