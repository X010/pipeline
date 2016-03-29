package com.dssmp.pipeline;

import com.dssmp.pipeline.config.Configuration;
import com.dssmp.pipeline.config.ConfigurationException;
import com.dssmp.pipeline.config.PipelineConfiguration;
import com.dssmp.pipeline.config.PipelineOptions;
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
            Paths.get(opts.getConfigFile());

        } else {
            throw new Exception("No Found ConfigFile");
        }


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
