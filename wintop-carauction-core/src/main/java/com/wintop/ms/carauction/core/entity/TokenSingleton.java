package com.wintop.ms.carauction.core.entity;


import com.wintop.ms.carauction.core.model.TokenModel;

public class TokenSingleton {
    private TokenModel tokenModel;
    private static TokenSingleton instance = new TokenSingleton();
    private TokenSingleton(){}
    public static TokenSingleton getInstance() {
        return instance;
    }

    public TokenModel getTokenModel() {
        return tokenModel;
    }

    public void setTokenModel(TokenModel tokenModel) {
        this.tokenModel = tokenModel;
    }
}
