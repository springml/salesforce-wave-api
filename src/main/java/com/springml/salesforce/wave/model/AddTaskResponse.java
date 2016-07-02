package com.springml.salesforce.wave.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// For below response
// {"id":"00TB0000003LgMzMAK","success":true,"errors":[]}
// In case of error below response is returned from salesforce
// [{"message":"Related To ID: id value of incorrect type: 006B000002nBrQ","errorCode":"MALFORMED_ID","fields":["WhatId"]}]
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class AddTaskResponse extends ForceResponse {
}
