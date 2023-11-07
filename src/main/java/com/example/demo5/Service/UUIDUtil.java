package com.example.demo5.Service;

import java.util.UUID;

public class UUIDUtil {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
