package com.bms.globalcustomvalidations.service.constants;

public class ValidationMessages {
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

    public static final String USERNAME_IS_REQUIRED = "Username cannot be empty";
    public static final String USERNAME_MIN_LENGTH = "Username must be at least 3 characters";

    public static final String EMAIL_IS_REQUIRED = "Email cannot be empty";
    public static final String EMAIL_IS_NOT_VALID = "Email is not valid";

    public static final String PASSWORD_IS_REQUIRED = "Password cannot be empty";
    public static final String PASSWORD_MIN_LENGTH = "Password must be at least 6 characters";
    public static final String PASSWORD_IS_NOT_VALID = "Password is not valid";

}
