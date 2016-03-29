package com.dssmp.pipeline;

import com.dssmp.pipeline.config.Configuration;
import com.dssmp.pipeline.config.ConfigurationException;
import com.dssmp.pipeline.config.PipelineConfiguration;
import com.dssmp.pipeline.config.PipelineOptions;
import com.dssmp.pipeline.rdbms.ConnectionFactroy;
import com.dssmp.pipeline.rdbms.Transfer;
import com.dssmp.pipeline.rdbms.impl.*;
import com.dssmp.pipeline.util.CONST;
import com.google.common.base.Strings;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 */
public class Application {

    /**
     * 主程序入口
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //读取并解析配置
        PipelineOptions opts = PipelineOptions.parse(args);
        String configFile = opts.getConfigFile();
        if (!Strings.isNullOrEmpty(configFile)) {
            //初始导入导出链接池
            PipelineConfiguration config = tryReadConfigurationFile(Paths.get(opts.getConfigFile()));
            if (config != null) {
                ConnectionFactroy exportConnectionFactory = getConnectionFactory(config.getExceptionDbType()
                        , config.getExceptionConnection()
                        , config.getExceptionUserName()
                        , config.getExceptionPassword());
                ConnectionFactroy importConnectionFactory = getConnectionFactory(config.getImportDbType()
                        , config.getImportConnection()
                        , config.getImportUserName()
                        , config.getImportPassword());

                if (exportConnectionFactory == null || importConnectionFactory == null) {
                    throw new Exception("no create export connection factory or no create import connection factory");
                }

                Transfer transfer = new MultithreadingTranfer(config, exportConnectionFactory, importConnectionFactory);
                transfer.tranfer();
            }
        } else {
            throw new Exception("No Found ConfigFile");
        }
    }

    /**
     * 获取链接工厂
     *
     * @param dbType
     * @param url
     * @param username
     * @param password
     * @return
     */
    private static ConnectionFactroy getConnectionFactory(String dbType, String url, String username, String password) {
        ConnectionFactroy connectionFactroy = null;
        switch (dbType) {
            case CONST.MSSQL:
                connectionFactroy = new MsSqlConnectionFactory(url, username, password);
                break;

            case CONST.MYSQL:
                connectionFactroy = new MySqlConnectionFactory(url, username, password);
                break;

            case CONST.ORACLE:
                connectionFactroy = new OracleSqlConnectionFactory(url, username, password);
                break;

            case CONST.POSTGREPSQL:
                connectionFactroy = new PostgrepSqlConnectionFactory(url, username, password);
                break;
        }
        return connectionFactroy;
    }

    private static PipelineConfiguration tryReadConfigurationFile(Path configFile) {
        try {
            return readConfigurationFile(configFile);
        } catch (ConfigurationException ce) {
            return null;
        }
    }

    private static PipelineConfiguration readConfigurationFile(Path configFile) throws ConfigurationException {
        try {
            return new PipelineConfiguration(Configuration.get(configFile));
        } catch (ConfigurationException ce) {
            throw ce;
        } catch (Exception e) {
            throw new ConfigurationException("Failed when reading configuration file: " + configFile, e);
        }
    }
}
