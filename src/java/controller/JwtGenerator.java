/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Rizal.F
 */
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import org.json.simple.JSONObject;
import java.util.Date;

public class JwtGenerator {

    public static String createJwt(String clientEmail, String privateKey, String tokenUri) throws Exception {
        long now = System.currentTimeMillis() / 1000; // Waktu sekarang dalam detik

        // Header JWT
        JSONObject header = new JSONObject();
        header.put("alg", "RS256");
        header.put("typ", "JWT");

        // Payload JWT
        JSONObject payload = new JSONObject();
        payload.put("iss", clientEmail);
        payload.put("scope", "https://www.googleapis.com/auth/firebase.messaging");
        payload.put("aud", tokenUri);
        payload.put("exp", now + 3600); // Expired dalam 1 jam
        payload.put("iat", now); // Issued at (waktu sekarang)

        // Encode Header dan Payload ke Base64
        String encodedHeader = base64UrlEncode(header.toJSONString().getBytes());
        String encodedPayload = base64UrlEncode(payload.toJSONString().getBytes());

        // Buat String JWT tanpa signature dulu
        String unsignedJwt = encodedHeader + "." + encodedPayload;

        // Sign JWT dengan private key
        String signedJwt = unsignedJwt + "." + signJwt(unsignedJwt, privateKey);

        return signedJwt;
    }

    private static String signJwt(String data, String privateKeyPem) throws Exception {
        // Convert private key dari format PEM ke PrivateKey Java
        privateKeyPem = privateKeyPem.replace("-----BEGIN PRIVATE KEY-----", "")
                                     .replace("-----END PRIVATE KEY-----", "")
                                     .replaceAll("\\s", "");
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPem);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey privateKey = java.security.KeyFactory.getInstance("RSA").generatePrivate(keySpec);

        // Sign JWT dengan RS256
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signedBytes = signature.sign();

        return base64UrlEncode(signedBytes);
    }

    private static String base64UrlEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }
}

