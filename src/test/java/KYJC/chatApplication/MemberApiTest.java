package KYJC.chatApplication;

import KYJC.chatApplication.Member.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiTest {

    @LocalServerPort
    int port;

    @Autowired
    DatabaseCleanup databaseCleanup;

    @Autowired
    JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        databaseCleanup.execute();
        RestAssured.port = port;
    }

    @Test
    void 회원가입() {
        MemberSignupResponse 회원 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateMemberRequest(
                        "도라에몽",
                        "dora123",
                        "1213456789"))
                .when()
                .post("/signUp") // POST /members 요청
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(MemberSignupResponse.class);

        assertThat(회원).isNotNull();
    }

    @Test
    void 로그인() {
        MemberSignupResponse 회원 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateMemberRequest(
                        "도라에몽",
                        "dora123",
                        "1213456789"))
                .when()
                .post("/signUp") // POST /members 요청
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(MemberSignupResponse.class);

        assertThat(회원).isNotNull();

        AccessToken 로그인토큰 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new LoginRequest(
                        "dora123",
                        "1213456789"))
                .when()
                .post("/signIn") // POST /members 요청
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(AccessToken.class);

        assertThat(jwtProvider.isValidToken(로그인토큰.token())).isNotNull();
    }

    @Test
    void 탈퇴() {
        MemberSignupResponse 회원 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateMemberRequest(
                        "도라에몽",
                        "dora123",
                        "1213456789"))
                .when()
                .post("/signUp") // POST /members 요청
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(MemberSignupResponse.class);

        assertThat(회원).isNotNull();

        AccessToken 로그인토큰 = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new LoginRequest(
                        "dora123",
                        "1213456789"))
                .when()
                .post("/signIn") // POST /members 요청
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(AccessToken.class);

        assertThat(jwtProvider.isValidToken(로그인토큰.token())).isNotNull();

        RestAssured
                .given().log().all()
                // TODO: "token" 실제 코드 작성
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + 로그인토큰.token())
                .queryParam("id", 회원.Id()) // 요청 쿼리 파라미터로 id 전달
                .when()
                .delete("/member")
                .then().log().all()
                .statusCode(200);



    }
}
