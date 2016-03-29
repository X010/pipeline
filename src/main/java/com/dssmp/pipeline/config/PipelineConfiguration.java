package com.dssmp.pipeline.config;

import com.dssmp.pipeline.util.JsonParser;
import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
public class PipelineConfiguration extends Configuration {
    private final static String EXCEPTIONCONNECTION = "exportconnection";
    private final static String IMPORTCONNECTION = "importconnection";
    private final static String EXCEPTIONDB = "exportdb";
    private final static String IMPORTDB = "importdb";
    private final static String EXCEPTIONDBTYPE = "exportdbtype";
    private final static String IMPORTDBTYPE = "importdbtype";
    private final static String EXCEPTIONSQL = "exportsql";
    private final static String IMPORTSQL = "importsql";
    private final static String MAPPING = "mapping";

    /**
     * 导出链接
     *
     * @return
     */
    public String getExceptionConnection() {
        return readString(EXCEPTIONCONNECTION);
    }

    /**
     * 导入链接
     *
     * @return
     */
    public String getImportConnection() {
        return readString(IMPORTCONNECTION);
    }

    /**
     * 导出数据名称
     *
     * @return
     */
    public String getExceptionDb() {
        return readString(EXCEPTIONDB);
    }

    /**
     * 导入数据名称
     *
     * @return
     */
    public String getImportDb() {
        return readString(IMPORTDB);
    }

    /**
     * 获取导出数据类型
     *
     * @return
     */
    public String getExceptionDbType() {
        return readString(EXCEPTIONDBTYPE);
    }

    /**
     * 获以导入数据类型数据库
     *
     * @return
     */
    public String getImportDbType() {
        return readString(IMPORTDBTYPE);
    }

    /**
     * 获取异常
     *
     * @return
     */
    public String getExceptionSQL() {
        return readString(EXCEPTIONSQL);
    }

    /**
     * 获取导入数据SQL
     *
     * @return
     */
    public String getImportSQL() {
        return readString(IMPORTSQL);
    }


    public List<FieldMapping> getFieldMapping() {
        String mappingStr = readString(MAPPING);
        if (!Strings.isNullOrEmpty(mappingStr)) {
            List<FieldMapping> fieldMappings = JsonParser.parseArray(mappingStr, FieldMapping.class);
            return fieldMappings;
        }
        return null;
    }

    public PipelineConfiguration(Configuration config) {
        super(config);
    }


    public PipelineConfiguration(Map<String, Object> map) {
        super(map);
    }


    /**
     * 字段对应
     */
    public static class FieldMapping implements Serializable {
        private String exportField;
        private String importField;
        private String exportType;
        private String importType;

        public String getExportField() {
            return exportField;
        }

        public void setExportField(String exportField) {
            this.exportField = exportField;
        }

        public String getImportField() {
            return importField;
        }

        public void setImportField(String importField) {
            this.importField = importField;
        }

        public String getExportType() {
            return exportType;
        }

        public void setExportType(String exportType) {
            this.exportType = exportType;
        }

        public String getImportType() {
            return importType;
        }

        public void setImportType(String importType) {
            this.importType = importType;
        }
    }
}
