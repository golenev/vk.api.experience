package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class ApiMethods {

    private static Response response;
    private static RequestSpecification request;

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

}
