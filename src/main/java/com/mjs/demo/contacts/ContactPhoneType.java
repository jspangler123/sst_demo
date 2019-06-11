package com.mjs.demo.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ContactPhoneType {
  @JsonProperty("home")
  HOME,
  @JsonProperty("work")
  WORK,
  @JsonProperty("mobile")
  MOBILE
}
