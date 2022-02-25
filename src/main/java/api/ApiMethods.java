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
import static io.restassured.RestAssured.given;

public class ApiMethods {

    private static Response response;
    private static RequestSpecification request;
    private static String res;
    private static Object server;
    private static Object photo;
    private static Object hash;

    public static void prepareForResponse() {
        RestAssured.baseURI = "https://api.vk.com/method/";
        request = RestAssured.given()
                .when()
                .formParam("access_token", "93324a886def812db3af27f2a8f810e2b06045ca2978064add0d7b88cf1a11f8fbe647d77b82fb8e53f9f")
                .formParam("v", "5.131");
    }

    public static int wallPost(String message) {
        prepareForResponse();
        response = request.when()
                .basePath("/wall.post")
                .formParam("message", message)
                .post()
                .then()
                .extract().response();
        return response.getBody().jsonPath().get("response.post_id");
    }

    public static void getWallUploadServer() {
        prepareForResponse();
        response = request.when()
                .basePath("/photos.getWallUploadServer")
                .formParam("owner_id", "706364498")
                .post()
                .then()
                .extract().response();
        res = response.getBody().jsonPath().get("response.upload_url");
    }

    public static void sendPhotoToURL() {
        getWallUploadServer();
        RestAssured.baseURI = res;
        response = request.when()
                .contentType(ContentTypeEnum.MULTIPART.getOption())
                .multiPart("photo", new File("testPhoto.png"))
                .post(res)
                .then()
                .extract().response();
        server = response.getBody().jsonPath().get("server");
        photo = response.getBody().jsonPath().get("photo");
        hash = response.getBody().jsonPath().get("hash");
    }

    public static int savePhotoToWall() {
        sendPhotoToURL();
        prepareForResponse();
        Response response1 = request
                .when()
                .basePath("/photos.saveWallPhoto")
                .formParam("server", server)
                .formParam("photo", photo)
                .formParam("hash", hash)
                .post()
                .then()
                .extract().response();
        int photoId = response1.getBody().jsonPath().get("response[0].id");
        return photoId;
    }

    public static void wallEdit(int post_id, String message, int photo_id) {
        prepareForResponse();
        response = request.when()
                .basePath("/wall.edit")
                .formParam("owner_id", "706364498")
                .formParam("post_id", post_id)
                .formParam("message", message)
                .formParam("attachments", "photo" + "706364498_" + photo_id)
                .post()
                .then()
                .extract().response();
    }

    public static void wallCreateComment(int post_id, String message) {
        prepareForResponse();
        response = request.when()
                .basePath("/wall.createComment")
                .formParam("owner_id", "706364498")
                .formParam("post_id", post_id)
                .formParam("message", message)
                .post()
                .then()
                .extract().response();
    }

    public static int likesIsLiked(int post_id) {
        prepareForResponse();
        response = request.when()
                .basePath("/likes.isLiked")
                .formParam("owner_id", "706364498")
                .formParam("type", "post")
                .formParam("item_id", post_id)
                .post()
                .then()
                .extract().response();
        return response.getBody().jsonPath().get("response.liked");
    }

    public static void wallDelete(int post_id) {
        prepareForResponse();
        response = request.when()
                .basePath("/wall.delete")
                .formParam("owner_id", "706364498")
                .formParam("post_id", post_id)
                .post()
                .then()
                .extract().response();
    }

    public static int postPin(int post_id) {
        prepareForResponse();
        response = request.when()
                .basePath("/wall.pin")
                .formParam("owner_id", "706364498")
                .formParam("post_id", post_id)
                .post()
                .then()
                .extract().response();
        return response.getBody().jsonPath().get("response");
    }

}
