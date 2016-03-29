package com.dssmp.pipeline.config;

import com.beust.jcommander.*;
import lombok.Getter;

import java.io.File;

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
@Parameters(separators = "=")
public class PipelineOptions {
    private static final String DEFAULT_CONFIG_FILE = "/etc/agent/agent.json";

    @Parameter(names = {"--configuration", "-c"}, description = "Path to the configuration file for the agent.", validateWith = FileReadableValidator.class)
    @Getter
    String configFile = DEFAULT_CONFIG_FILE;

    @Parameter(names = {"--help", "-h"}, help = true, description = "Display this help message")
    Boolean help;

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public Boolean getHelp() {
        return help;
    }

    public void setHelp(Boolean help) {
        this.help = help;
    }

    /**
     * 解析
     *
     * @param agrs
     * @return
     */
    public static PipelineOptions parse(String[] agrs) {
        PipelineOptions pipelineOptions = new PipelineOptions();
        JCommander jc = new JCommander(pipelineOptions);
        jc.setProgramName("piepline");
        try {
            jc.parse(agrs);
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            jc.usage();
            System.exit(1);
        }

        if (Boolean.TRUE.equals(pipelineOptions.help)) {
            jc.usage();
            System.exit(0);
        }
        return pipelineOptions;
    }

    public static class FileReadableValidator implements IParameterValidator {
        @Override
        public void validate(String name, String value)
                throws ParameterException {
            File f = new File(value);
            if (!f.exists()) {
                throw new ParameterException("Parameter " + name
                        + " points to a file that doesn't exist: " + value);
            }
            if (!f.canRead()) {
                throw new ParameterException("Parameter " + name
                        + " points to a file that's not accessible: " + value);
            }

        }


    }
}
