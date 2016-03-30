package com.dssmp.pipeline.rdbms.impl;

import com.dssmp.pipeline.rdbms.ConnectionFactroy;
import com.dssmp.pipeline.rdbms.SQLUtil;
import com.google.common.base.Preconditions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
public class PostgrepSqlUtil extends SQLUtil {

    public PostgrepSqlUtil(ConnectionFactroy connectionFactroy) {
        super(connectionFactroy);
    }

    @Override
    public ResultSet getData(String sql) {
        try {
            Connection connection = this.connectionFactroy.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertData(List<String> sqls) {
        Preconditions.checkNotNull(sqls);
        try {
            Connection connection = this.connectionFactroy.getConnection();
            final Statement statement = connection.createStatement();
            sqls.stream().forEach(sql -> {
                try {
                    statement.execute(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
