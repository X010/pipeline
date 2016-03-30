package com.dssmp.pipeline.rdbms;

import com.dssmp.pipeline.rdbms.impl.MsSqlUtil;
import com.dssmp.pipeline.rdbms.impl.MySqlUtil;
import com.dssmp.pipeline.rdbms.impl.OracleSqlUtil;
import com.dssmp.pipeline.rdbms.impl.PostgrepSqlUtil;
import com.dssmp.pipeline.util.CONST;

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
public class SQLUtilFactory {


    public static SQLUtil getSqlUtil(String dbType, ConnectionFactroy connectionFactroy) {
        SQLUtil sqlUtil = null;
        switch (dbType) {
            case CONST.MSSQL:
                sqlUtil = new MsSqlUtil(connectionFactroy);
                break;

            case CONST.MYSQL:
                sqlUtil = new MySqlUtil(connectionFactroy);
                break;

            case CONST.ORACLE:
                sqlUtil = new OracleSqlUtil(connectionFactroy);
                break;

            case CONST.POSTGREPSQL:
                sqlUtil = new PostgrepSqlUtil(connectionFactroy);
                break;
        }
        return sqlUtil;
    }

}
