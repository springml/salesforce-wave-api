/*
 * Copyright 2015 - 2017, salesforce-wave-api, springml
 * Author  :
 * 	  Samual Alexander, springml
 * Contributors:
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springml.salesforce.wave.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//For below response
//{"id":"00TB0000003LgMzMAK","success":true,"errors":[]}
//In case of error below response is returned from salesforce
//[{"message":"Related To ID: id value of incorrect type: 006B000002nBrQ","errorCode":"MALFORMED_ID","fields":["WhatId"]}]
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForceResponse {
    private String id;
    private boolean success;
    private String error;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AddTaskResponse [id=" + id + ", success=" + success + ", error=" + error + "]";
    }

}
