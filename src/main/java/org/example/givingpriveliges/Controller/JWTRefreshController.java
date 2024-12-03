package org.example.givingpriveliges.Controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.givingpriveliges.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JWTRefreshController {
//    private String SECRET_KEY = jwtService.fetchSecretKey();
    private long TOKEN_LIFETIME;// 1 hour
    private  long REFRESH_THRESHOLD ; // Refresh if more than 30 minutes passed
    private final String SECRET_KEY;
    private final JWTService jwtService;
    @Autowired
    public JWTRefreshController(JWTService jwtService) {
        this.jwtService = jwtService;
        this.SECRET_KEY = jwtService.fetchSecretKey();
    }
    @PostMapping("/refresh-token")
    public String refreshToken(@RequestHeader("Authorization") String oldToken) {
        try {
            // Decode and validate the token
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(oldToken.replace("Bearer ", "")).getBody();

            // Check expiration time
            long now = System.currentTimeMillis();
            long issuedAt = claims.getIssuedAt().getTime();
            long expiration = claims.getExpiration().getTime();
            TOKEN_LIFETIME=expiration-issuedAt;
            System.out.println("Token Lifetime : "+TOKEN_LIFETIME);
            REFRESH_THRESHOLD = TOKEN_LIFETIME / 2;
            System.out.println("Refresh Threshold : "+REFRESH_THRESHOLD);
            if ((expiration - now) < REFRESH_THRESHOLD) {
                // Create a new token
                return Jwts.builder()
                        .setSubject(claims.getSubject())
                        .setIssuedAt(new Date(now))
                        .setExpiration(new Date(now + TOKEN_LIFETIME)) // Extend expiration
                        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                        .compact();

                // Return the new token
//                return ResponseEntity.ok().header("Authorization", "Bearer " + newToken).build();
            }

            // If no refresh is needed, return the old token
            return oldToken;

        } catch (Exception e) {
            return "Error refreshing token";
        }
    }
}