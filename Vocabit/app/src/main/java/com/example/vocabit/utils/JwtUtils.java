package com.example.vocabit.utils;
import android.util.Base64;
import org.json.JSONObject;

public class JwtUtils {
    public static String getUsernameFromToken(String token) {
        try {
            // Tách JWT thành 3 phần: header, payload, và signature
            String[] parts = token.split("\\.");

            // Giải mã phần payload (Base64)
            String payload = new String(Base64.decode(parts[1], Base64.URL_SAFE));

            // Chuyển payload thành JSONObject
            JSONObject json = new JSONObject(payload);

            // Truy xuất giá trị 'sub' (nếu bạn sử dụng 'sub' cho username)
            return json.getString("sub");  // 'sub' chứa username nếu bạn đã dùng claim này
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

