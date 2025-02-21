package com.bo.orkaw.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

  private Object data;

  public ApiResponse(Object data) {
    this.data = data;
  }


}
