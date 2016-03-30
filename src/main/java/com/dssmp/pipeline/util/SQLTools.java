package com.dssmp.pipeline.util;

import com.dssmp.pipeline.config.PipelineConfiguration;

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
public class SQLTools {

    private static final String PLACEHLODER = "#{PLACEHLODER}";

    /**
     * 获取统计SQL
     *
     * @param sql
     * @return
     */
    public static String getCountSql(String sql) {
        return sql.replace(PLACEHLODER, " count(1) as total ");
    }

    /**
     * 获取读取数据SQL
     *
     * @param sql
     * @param pipelineConfiguration
     * @return
     */
    public static String getSelectSql(String sql, PipelineConfiguration pipelineConfiguration) {
        return null;
    }

}
