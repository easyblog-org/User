package top.easyblog.titan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * System unified response code
 *
 * @author frank.huang
 * @since 2021/8/21 22:13
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    //sever internal
    SUCCESS,
    INVALID_PARAMS,
    NOT_FOUND,
    INTERNAL_ERROR,
    DATA_ACCESS_FAIL,
    ERROR_LOGIN_POLICY,

    //parameter
    PARAMTER_NOT_VALID,
    PARAMETER_VALIDATE_FAILED,
    LOGIN_FAILED,
    LOGOUT_FAILED,
    REQUIRED_PARAM_TOKEN_NOT_EXISTS,
    REQUIRED_REQUEST_PARAM_NOT_EXISTS,
    REPEAT_USER_NAME,
    PASSWORD_NOT_VALID,
    PASSWORD_NOT_EQUAL,
    PASSWORD_VALID_FAILED,
    USER_NOT_FOUND,
    USER_ACCOUNT_EXISTS,
    USER_HEADER_IMG_NOT_FOUND,
    USER_HEADER_IMGS_NOT_FOUND,
    USER_ACCOUNT_NOT_FOUND,
    ACCOUNT_IS_FREEZE,
    ACCOUNT_IS_DELETE,
    ACCOUNT_IS_PRE_ACTIVE,
    SIGN_IN_LOG_NOT_FOUND,
    PHONE_AREA_CODE_NOT_FOUND,
    PHONE_AREA_CODE_ALREADY_EXISTS,
    PHONE_ACCOUNT_ALREADY_EXISTS,
    IDENTIFIER_NOT_EMAIL,
    EMAIL_ACCOUNT_EXISTS,
    IDENTIFIER_NOT_PHONE,

    SIGN_FAIL,
    SIGN_ERROR,
    SIGN_NOT_FOUND,
    SING_HAS_EXPIRE,

    REMOTE_INVOKE_FAIL;


    public String getCode() {
        return this.name().toLowerCase();
    }
}
