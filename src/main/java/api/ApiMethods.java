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
    private static  String res;
    private static  Object server;
    private static  Object photo;
    private static  Object hash;

        private static RequestSpecification prepareResponse () {
        return given()
                .contentType(ContentType.JSON)
                .when();
    }

        public static Response useMethodGet (String url)  {
        return prepareResponse().get(url);
    }

        public static Response useMethodPost (String url, Object body)  {
        return prepareResponse()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url);
    }

    public static int wallPost(String message){
        RestAssured.baseURI = "https://api.vk.com/method/";
        request = RestAssured.given();
        response = request.when()
                .basePath("/wall.post")
                .formParam("owner_id", "706364498")
                .formParam("access_token", "9118837e14520bc49088273190071c6710a1f4c9d484b22493dc857a5762927e465888b2254d44fc213ce")
                .formParam("v","5.131")
                .formParam("message",message)
                .post()
                .then()
                .statusCode(200)
                .extract().response();
        return response.getBody().jsonPath().get("response.post_id");
    }

    public static void getWallUploadServer(){
        RestAssured.baseURI = "https://api.vk.com/method/";
        request = RestAssured.given();
        response = request.when()
                .basePath("/photos.getWallUploadServer")
                .formParam("owner_id", "706364498")
                .formParam("access_token", "9118837e14520bc49088273190071c6710a1f4c9d484b22493dc857a5762927e465888b2254d44fc213ce")
                .formParam("v","5.131")
                .post()
                .then()
                .statusCode(200)
                .extract().response();
        res = response.getBody().jsonPath().get("response.upload_url");

    }

    public static void sendPhotoToURL(){
        getWallUploadServer();
        RestAssured.baseURI = res;
        response = request.when()
                .contentType(ContentTypeEnum.MULTIPART.getOption())
                .multiPart("photo", new File("testPhoto.jpg"))
                .post(res)
                .then()
                .statusCode(200)
                .extract().response();
        server = response.getBody().jsonPath().get("server");
        photo = response.getBody().jsonPath().get("photo");
        hash = response.getBody().jsonPath().get("hash");
    }


    public static int savePhotoToWall(){
        sendPhotoToURL();
        RestAssured.baseURI = "https://api.vk.com/method/";
        Response response1 = request
                .when()
                .basePath("/photos.saveWallPhoto")
                .formParam("server", server)
                .formParam("photo", photo)
                .formParam("hash", hash)
                .formParam("access_token", "9118837e14520bc49088273190071c6710a1f4c9d484b22493dc857a5762927e465888b2254d44fc213ce")
                .formParam("v","5.131")
                .post()
                .then()
                .statusCode(200)
                .extract().response();
        int photoId = response1.getBody().jsonPath().get("response[0].id");
        System.out.println(photoId);
        return  photoId;
    }

    public static void wallEdit(int post_id, String message, int photo_id ){
        RestAssured.baseURI = "https://api.vk.com/method/";
        request = RestAssured.given();
        response = request.when()
                .basePath("/wall.edit")
                .formParam("owner_id", "706364498")
                .formParam("access_token", "9118837e14520bc49088273190071c6710a1f4c9d484b22493dc857a5762927e465888b2254d44fc213ce")
                .formParam("v","5.131")
                .formParam("post_id",post_id)
                .formParam("message",message)
                .formParam("attachments","photo", "706364498_"+ photo_id)
                .post()
                .then()
                .statusCode(200)
                .extract().response();
    }

    public static void wallCreateComment(int post_id, String message){
        RestAssured.baseURI = "https://api.vk.com/method/";
        request = RestAssured.given();
        response = request.when()
                .basePath("/wall.createComment")
                .formParam("owner_id", "706364498")
                .formParam("access_token", "9118837e14520bc49088273190071c6710a1f4c9d484b22493dc857a5762927e465888b2254d44fc213ce")
                .formParam("v","5.131")
                .formParam("post_id",post_id)
                .formParam("message",message)
                .post()
                .then()
                .statusCode(200)
                .extract().response();
    }

   /* public static int likesIsLiked(int post_id){
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        request = RestAssured.given();
        response = request.when()
                .basePath(Methods.LIKES_IS_LIKED.toString())
                .formParam(VkFields.OWNER_ID.toString(), PropertiesUtility.getIntValue(Resources.TEST.toString(),"ownerId"))
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .formParam(VkFields.TYPE.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(), "typePost"))
                .formParam(VkFields.ITEM_ID.toString(),post_id)
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
        return  response.getBody().jsonPath().get("response.liked");
    }

    public static void wallDelete(int post_id){
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        request = RestAssured.given();
        response = request.when()
                .basePath(Methods.WALL_DELETE.toString())
                .formParam(VkFields.OWNER_ID.toString(), PropertiesUtility.getIntValue(Resources.TEST.toString(),"ownerId"))
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .formParam(VkFields.POST_ID.toString(),post_id)
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
    }*/

}
