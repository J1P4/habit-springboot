package com.example.habit.type;

public class EProviderFactory {
    public static EProvider of(String provider) {
        return switch (provider) {
            case "KAKAO" -> EProvider.KAKAO;
            default -> throw new IllegalArgumentException("Invalid Provider Type.");
        };
    }
}
