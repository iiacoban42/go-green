package server.controller;

import org.junit.Test;
import server.controller.CreateJwt;

import org.apache.commons.codec.binary.Base64;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CreateJWTTest {

    @Test
    public void createJWT() {
        String jwtToken = CreateJwt.createJwt("Paula");
        System.out.println(jwtToken);
        System.out.println("------------ Decode JWT ------------");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];

        System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        System.out.println("JWT Header : " + header);


        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        System.out.println("JWT Body : "+body);

        String headerTest = "{\"alg\":\"HS256\"}";
        String bodySub = "{\"sub\":\"Paula\"";
        String bodyID = "\"jti\":\"Paula\"";


        String[] bodyArray = body.split(",");
        Long iatTest = Long.parseLong(bodyArray[2].replaceAll("\\D+", ""));
        Long expTest = Long.parseLong(bodyArray[3].replaceAll("\\D+", ""));
        CreateJwt.validateToken(jwtToken);

        assertEquals(headerTest, header);
        assertEquals(bodySub, bodyArray[0]);
        assertEquals(bodyID, bodyArray[1]);
        assertEquals(1800, expTest-iatTest);
    }

    @Test
    public void usernameNull() {
        assertNull(CreateJwt.createJwt(null));
    }
}
