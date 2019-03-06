package server.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreateJWT {
    private static String signingKey = "secret key";

    /**
     * Creates a JSON Web Token that validates the user when they go to a different link.
     * @param username username and unique id of the user
     * @return the token
     */
    public static String createJWT(String username) {
        if (username == null) {
            return null;
        }
        // for setting date
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // for generating secret key
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String base64Key = Base64.encodeBase64String(signingKey.getBytes());

        // creating builder
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(username);
        builder.setId(username);
        builder.setIssuedAt(now);
        builder.setExpiration(createExpirationDate(nowMillis));
        builder.signWith(signatureAlgorithm, base64Key);

        return builder.compact();
    }

    public static Date createExpirationDate(long nowMillis) {
        try {
            if (nowMillis <= 0) {
                throw new IllegalAccessException();

            }
            long expMillis = TimeUnit.MINUTES.toMillis(30);
            return new Date(nowMillis+expMillis);

        } catch(IllegalAccessException e) {
            System.out.print("Time should be bigger than 0");
            return null;
        }
    }
}
