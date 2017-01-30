package com.aexample.security;

public interface ISecurityRegService {

    String validatePasswordResetToken(long id, String token);

}
