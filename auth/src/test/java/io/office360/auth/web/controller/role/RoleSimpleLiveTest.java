//package io.office360.auth.web.controller.role;
//
//import io.restassured.path.json.JsonPath;
//import io.restassured.http.Header;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static io.restassured.RestAssured.given;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class RoleSimpleLiveTest {
//
//    private final static String JSON = MediaType.APPLICATION_JSON.toString();
//
//    @LocalServerPort
//    int port;
//
//    @Test
//    public void test2() throws InterruptedException {
//
//        String response =
//                given().port(port)
//                        .auth().basic("trusted-app", "secret")
//                        .basePath("/")
//                        .contentType("multipart/form-data")
//                        .multiPart("grant_type", "password")
//                        .multiPart("username", "user@fake.com")
//                        .multiPart("password", "userpass")
//                        .when()
//                        .post("/oauth/token")
//                        .asString();
//
//
//
//        JsonPath jsonPath = new JsonPath(response);
//        String accessToken = jsonPath.getString("access_token");
//
//        System.out.println(accessToken);
//
//        String roles = given()
//                .port(port)
//                .basePath("/roles")
//                .header(new Header("Authorization", "Bearer " + accessToken))
//                .get("")
//                .then()
//                .statusCode(200)
//                .extract().asString();
//
//        System.out.println(roles);
////        Restasassured
////        given().port(port).basePath("/clothes").get("").then().statusCode(200);
//    }
//
//
////    @Autowired
////    private RoleSimpleApiClient api;
////
////    // find - one
////
////    @Test
////    public final void whenNonExistingResourceIsRetrieved_then404IsReceived() {
////        final Response response = getApi().findOneAsResponse(IDUtil.randomPositiveLong());
////
////        assertThat(response.getStatusCode(), is(404));
////    }
////
////    @Test
////    public final void whenResourceIsRetrievedByNonNumericId_then400IsReceived() {
////        // When
////        final Response res = getApi().read(getUri() + WebConstants.PATH_SEP + randomAlphabetic(6));
////
////        // Then
////        assertThat(res.getStatusCode(), is(400));
////    }
////
////    @Test
////    public final void givenResourceForIdExists_whenResourceOfThatIdIsRetrieved_then200IsRetrieved() {
////        // Given
////        final long id = getApi().create(createNewResource()).getId();
////
////        // When
////        final Response res = getApi().findOneAsResponse(id);
////
////        // Then
////        assertThat(res.getStatusCode(), is(200));
////    }
////
////    @Test
////    public final void givenResourceExists_whenResourceIsRetrieved_thenResourceIsCorrectlyRetrieved() {
////        // Given, When
////        final Role newResource = createNewResource();
////        final Role createdResource = getApi().create(newResource);
////
////        // Then
////        assertEquals(createdResource, newResource);
////    }
//
//    // find - all
//
//
//    // UTIL
//
////    private final String getUri() {
////        return getApi().getUri() + WebConstants.PATH_SEP;
////    }
////
////    private final RoleSimpleApiClient getApi() {
////        return api;
////    }
////
////    private final RequestSpecification givenAuthenticated() {
////        return getApi().givenAuthenticated();
////    }
////
////    private final Privilege createNewAssociationResource() {
////        return new Privilege(randomAlphabetic(8));
////    }
////
////    private final Role createNewResource() {
////        return new Role(randomAlphabetic(8), Sets.<Privilege> newHashSet());
////    }
//
//}
