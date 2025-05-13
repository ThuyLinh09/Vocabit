package com.example.vocabit.utils;
import android.util.Base64;
import org.json.JSONObject;

public class JwtUtils {
    public static String getUsernameFromToken(String token) {
        try {
            String[] parts = token.split("\\.");
            String payload = new String(Base64.decode(parts[1], Base64.DEFAULT));
            JSONObject json = new JSONObject(payload);
            return json.getString("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

