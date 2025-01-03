package com.tomato.running.global.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverInfoResponse {

    @JsonProperty("response")
    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {
        private String email;
        private String name;
        private String gender;
        private String age;
        private String mobile;
    }

    public String getEmail() {
        return response.email;
    }
    public String getName() {
        return response.name;
    }
    public String getGender() {
        return response.gender;
    }

    public String getAge() {
        return response.age;
    }

    public String getMobile(){
        return response.mobile;
    }

}