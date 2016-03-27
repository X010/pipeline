package com.dssmp.pipeline.config;

import com.google.common.base.Function;

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
public class BooleanConverter implements Function<Object, Boolean> {

    @Override
    public Boolean apply(Object input) {
        if (input != null && !(input instanceof Boolean)) {
            switch (input.toString().toLowerCase()) {
                case "true":
                case "yes":
                case "t":
                case "y":
                case "1":
                    return true;
                case "false":
                case "no":
                case "f":
                case "n":
                case "0":
                    return false;
                default:
                    throw new ConfigurationException(
                            "Cannot convert value to boolean: "
                                    + input.toString());

            }
        } else
            return (Boolean) input;
    }
}
