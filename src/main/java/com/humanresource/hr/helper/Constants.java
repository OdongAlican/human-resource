package com.humanresource.hr.helper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Constants {
    public static final String DELETED_SUCCESSFULLY = "Deleted successfully";
    public static final String ENTITY_NOT_FOUND = "Entity not found";
    public static final String NAME_VALIDATOR = "Must be a minimum of 3 and maximum of 20 letters";
    public static final String NULL_FIELD_VALIDATOR = "Field cannot be null";
    public static final String BLANK_FIELD_VALIDATOR = "Field cannot be blank";
    public static final String EMAIL_VALIDATOR = "Email address must be valid";
    public static final String PHONE_VALIDATOR = "Invalid Ugandan phone number";
    public static final String PHONE_REGEX = "^\\+256\\d{9}";
    public static final String GENERAL_ROUTE = "/api/v1";
    public static final String READ_USER = "READ_USER";
    public static final String CREATE_USER = "CREATE_USER";
    public static final String DELETE_USER = "DELETE_USER";
    public static final String UPDATE_USER = "UPDATE_USER";
    public static final String READ_ROLE = "READ_ROLE";
    public static final String CREATE_ROLE = "CREATE_ROLE";
    public static final String DELETE_ROLE = "DELETE_ROLE";
    public static final String UPDATE_ROLE = "UPDATE_ROLE";
    public static final String READ_PERMISSION = "READ_PERMISSION";
    public static final String CREATE_PERMISSION = "CREATE_PERMISSION";
    public static final String DELETE_PERMISSION = "DELETE_PERMISSION";
    public static final String UPDATE_PERMISSION = "UPDATE_PERMISSION";
    public static final String USER_ROUTE = GENERAL_ROUTE + "/users/**";
    public static final String ROLE_ROUTE = GENERAL_ROUTE + "/roles/**";
    public static final String PERMISSION_ROUTE = GENERAL_ROUTE + "/permissions/**";
    public static final String AUTH_ROUTE = GENERAL_ROUTE + "/auth/**";
}
