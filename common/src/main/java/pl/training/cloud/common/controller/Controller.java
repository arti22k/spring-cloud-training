package pl.training.cloud.common.controller;

public interface Controller {

    String[] GLOBAL_PUBLIC_URIS = {"/v2/api-docs", "/configuration/ui", "/swagger-resources",
            "/configuration/security", "/webjars/**", "/swagger-resources/configuration/ui",
            "/swagger-ui.html", "/swagge‌​r-ui.html"};
    String ALL_URIS = "/**";

}
