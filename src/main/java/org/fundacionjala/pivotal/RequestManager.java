package org.fundacionjala.pivotal;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestManager {

    public static Response post(final RequestSpecification requestSpec, final String endpoint,
                                final String body) {
        final Response response = RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(endpoint);
        return getResponseWithLogger(response);
    }

    public static Response post(final RequestSpecification requestSpec, final String endpoint,
                                final Map<String, String> body) {
        final Response response = RestAssured.given(requestSpec)
                .params(body)
                .when()
                .post(endpoint);
        return getResponseWithLogger(response);
    }

    public static Response put(final RequestSpecification requestSpec, final String endpoint,
                                final String body) {
        final Response response = RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .put(endpoint);
        return getResponseWithLogger(response);
    }

    public static Response put(final RequestSpecification requestSpec, final String endpoint,
                               final Map<String, String> body) {
        final Response response = RestAssured.given(requestSpec)
                .params(body)
                .when()
                .put(endpoint);
        return getResponseWithLogger(response);
    }

    public static Response delete(final RequestSpecification requestSpec, final String endpoint) {
        final Response response = RestAssured.given(requestSpec)
                .when()
                .delete(endpoint);
        return getResponseWithLogger(response);
    }

    public static Response get(final RequestSpecification requestSpec, final String endpoint) {
        final Response response = RestAssured.given(requestSpec)
                .when()
                .get(endpoint);
        return getResponseWithLogger(response);
    }

    private static Response getResponseWithLogger(Response response) {
        response.then()
                .log().status()
                .log().body();
        return response;
    }
}