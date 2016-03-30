package com.dssmp.pipeline.rdbms.impl;

import com.dssmp.pipeline.config.PipelineConfiguration;
import com.dssmp.pipeline.rdbms.ConnectionFactroy;
import com.dssmp.pipeline.rdbms.SQLUtil;
import com.dssmp.pipeline.rdbms.SQLUtilFactory;
import com.dssmp.pipeline.rdbms.Transfer;
import com.dssmp.pipeline.util.SQLTools;

import java.sql.ResultSet;

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
public class MultithreadingTranfer extends Transfer {

    public MultithreadingTranfer(PipelineConfiguration pipelineConfiguration, ConnectionFactroy exportConnectionFactory, ConnectionFactroy importConnectionFactory) {
        super(pipelineConfiguration, exportConnectionFactory, importConnectionFactory);
    }

    @Override
    public void tranfer() throws Exception {
        //获取导出导入工具类
        SQLUtil exportUtil = SQLUtilFactory.getSqlUtil(this.getPipelineConfiguration().getExceptionDbType(), this.getExportConnectionFactory());
        SQLUtil importUtil = SQLUtilFactory.getSqlUtil(this.getPipelineConfiguration().getImportDbType(), this.getImportConnectionFactory());

        if (exportUtil == null || importUtil == null) {
            throw new Exception("no found sql until");
        }

        //读取表里面的记录条数
        String countTotal = SQLTools.getCountSql(this.getPipelineConfiguration().getExceptionSQL());


        ResultSet rs = exportUtil.getData(countTotal);

        if (rs != null) {
            int total = 0;
            while (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();

            if (total > 0) {
                //数据存在开始组织导数据


            }
        } else {
            throw new Exception("no execute count sql");
        }
    }
}
