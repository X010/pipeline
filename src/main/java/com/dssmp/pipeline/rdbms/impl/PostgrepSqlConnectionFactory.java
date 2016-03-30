package com.dssmp.pipeline.rdbms.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.dssmp.pipeline.config.PipelineConfiguration;
import com.dssmp.pipeline.rdbms.ConnectionFactroy;

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
public class PostgrepSqlConnectionFactory extends ConnectionFactroy {
    private DruidDataSource dds = null;

    public PostgrepSqlConnectionFactory(PipelineConfiguration pipelineConfiguration) {
        super(pipelineConfiguration);
    }

    public PostgrepSqlConnectionFactory(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public Connection getConnection() throws Exception {
        if (dds == null) {
            throw new Exception("No Found Connection Pooled");
        }
        return dds.getConnection();
    }

    @Override
    public void init() {
        Properties properties = loadProperty();
        try {
            dds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载配置
     *
     * @return
     */
    protected  Properties loadProperty() {
        Properties properties = new Properties();
        properties.put("driverClassName","org.postgresql.Driver");
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
