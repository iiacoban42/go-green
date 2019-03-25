package server.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreateJwt {
    private static String signingKey = "secret key";

    /**
     * Creates a JSON Web Token that validates the user when they go to a different link.
     * @param username username and unique id of the user
     * @return the token
     */
    public static String createJwt(String username) {
        if (username == null) {
            return null;
        }
        // for setting date
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // for setting expiration date
        long thirtyMillis = System.currentTimeMillis() + 1800000;
        Date expDate = new Date(thirtyMillis);

        // for generating secret key
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String base64Key = Base64.encodeBase64String(signingKey.getBytes());


        // creating builder
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(username);
        builder.setId(username);
        builder.setIssuedAt(now);
        builder.setExpiration(expDate);
        builder.signWith(signatureAlgorithm, base64Key);

        System.out.println(builder.compact());
        return builder.compact();
    }

    public static long getIssuedAtDate(String jwtToken) {
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        String[] bodyArray = body.split(",");
        Long getExpirationDate = Long.parseLong(bodyArray[2].replaceAll("\\D+", ""));

        return getExpirationDate;
    }

    public static long getExpirationDate(String jwtToken) {
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        String[] bodyArray = body.split(",");
        Long getExpirationDate = Long.parseLong(bodyArray[3].replaceAll("\\D+", ""));

        return getExpirationDate;
    }

    public static boolean validateToken(String jwtToken) {
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        String id = body.split(",")[0].split(":")[1];
        id = id.replace("\"", "");

        long iatDate = getIssuedAtDate(jwtToken);

        String validateToken = database.manager.UserManager.getUser(id).getToken();
        long expDate = getExpirationDate(validateToken);
        if (iatDate > expDate) {
            return false;
        }

        return true;
    }
}
