package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import javax.annotation.Resources;
import java.io.File;
import static utils.TestingConfigurations.*;
import static io.restassured.RestAssured.given;
import static utils.TestingConfigurations.*;
import static utils.FileUtils.*;
import static utils.StringUtils.*;

public class ApiMethods {

    private static Response response;
    private static RequestSpecification request;
    private static String res;
    private static Object server;
    private static Object photo;
    private static Object hash;

    public static void prepareForResponse() {
        RestAssured.baseURI = getConfigValue("/baseUri");
        request = RestAssured.given()
                .when()
                .formParam(FormParamEnum.FORM_PARAM.getVersionToken(),getTestingValue("/token"))
                .formParam(FormParamEnum.FORM_PARAM.getVersionApi(), getConfigValue("/apiVersion"));
    }

    public static int wallPost(String message) {
        prepareForResponse();
        response = request.when()
                .basePath(getConfigValue("/wallPost"))
                .formParam(FormParamEnum.FORM_PARAM.getMessage(), message)
                .post()
                .then()
                .extract().response();
        return response.getBody().jsonPath().get(ResponseOptionEnum.RESPONSE_OPTION.getPostId());
    }

    public static void getWallUploadServer() {
        prepareForResponse();
        response = request.when()
                .basePath(getConfigValue("/getWallUploadServer"))
                .formParam(FormParamEnum.FORM_PARAM.getOwnerId(), getTestingValue("/ownerId"))
                .post()
                .then()
                .extract().response();
        res = response.getBody().jsonPath().get(ResponseOptionEnum.RESPONSE_OPTION.getUploadUrl());
    }

    public static void sendPhotoToURL() {
        getWallUploadServer();
        RestAssured.baseURI = res;
        response = request.when()
                .contentType(ContentTypeEnum.MULTIPART.getOption())
                .multiPart(FormParamEnum.FORM_PARAM.getPhoto(), loadFile(getTestingValue("/filePath")))
                .post(res)
                .then()
                .extract().response();
        server = response.getBody().jsonPath().get(FormParamEnum.FORM_PARAM.getServer());
        photo = response.getBody().jsonPath().get(FormParamEnum.FORM_PARAM.getPhoto());
        hash = response.getBody().jsonPath().get(FormParamEnum.FORM_PARAM.getHash());
    }

    public static int savePhotoToWall() {
        sendPhotoToURL();
        prepareForResponse();
        Response response1 = request
                .when()
                .basePath(getConfigValue("/saveWallPhoto"))
                .formParam(FormParamEnum.FORM_PARAM.getServer(), server)
                .formParam(FormParamEnum.FORM_PARAM.getPhoto(), photo)
                .formParam(FormParamEnum.FORM_PARAM.getHash(), hash)
                .post()
                .then()
                .extract().response();
        int photoId = response1.getBody().jsonPath().get(ResponseOptionEnum.RESPONSE_OPTION.getListId());
        return photoId;
    }

    public static void wallEdit(int post_id, String message, int photo_id) {
        prepareForResponse();
        response = request.when()
                .basePath(getConfigValue("/wallEdit"))
                .formParam(FormParamEnum.FORM_PARAM.getOwnerId(), getTestingValue("/ownerId"))
                .formParam(FormParamEnum.FORM_PARAM.getPostId(), post_id)
                .formParam(FormParamEnum.FORM_PARAM.getMessage(), message)
                .formParam(FormParamEnum.FORM_PARAM.getAttachments(), String.valueOf(buildString(FormParamEnum.FORM_PARAM.getPhoto(),
                        getTestingValue("/photoOwnerId"), photo_id )))
                .post()
                .then()
                .extract().response();
            }

    public static void wallCreateComment(int post_id, String message) {
        prepareForResponse();
        response = request.when()
                .basePath(getConfigValue("/createComment"))
                .formParam(FormParamEnum.FORM_PARAM.getOwnerId(), getTestingValue("/ownerId"))
                .formParam(FormParamEnum.FORM_PARAM.getPostId(), post_id)
                .formParam(FormParamEnum.FORM_PARAM.getMessage(), message)
                .post()
                .then()
                .extract().response();
    }

    public static int likesIsLiked(int post_id) {
        prepareForResponse();
        response = request.when()
                .basePath(getConfigValue("/isLiked"))
                .formParam(FormParamEnum.FORM_PARAM.getOwnerId(), getTestingValue("/ownerId"))
                .formParam(FormParamEnum.FORM_PARAM.getType(), FormParamEnum.FORM_PARAM.getPost())
                .formParam(FormParamEnum.FORM_PARAM.getItemId(), post_id)
                .post()
                .then()
                .extract().response();
        return response.getBody().jsonPath().get(ResponseOptionEnum.RESPONSE_OPTION.getIsLiked());
    }

    public static void wallDelete(int post_id) {
        prepareForResponse();
        response = request.when()
                .basePath(getConfigValue("/wallDelete"))
                .formParam(FormParamEnum.FORM_PARAM.getOwnerId(), getTestingValue("/ownerId"))
                .formParam(FormParamEnum.FORM_PARAM.getPostId(), post_id)
                .post()
                .then()
                .extract().response();
    }

  }
