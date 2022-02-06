package top.easyblog.titan.service.policy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import top.easyblog.titan.annotation.Transaction;
import top.easyblog.titan.bean.LoginDetailsBean;
import top.easyblog.titan.bean.UserDetailsBean;
import top.easyblog.titan.dao.auto.model.User;
import top.easyblog.titan.enums.AccountStatus;
import top.easyblog.titan.enums.IdentifierType;
import top.easyblog.titan.enums.Status;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.request.CreateAccountRequest;
import top.easyblog.titan.request.CreateUserRequest;
import top.easyblog.titan.request.LoginRequest;
import top.easyblog.titan.request.QueryUserRequest;
import top.easyblog.titan.request.RegisterUserRequest;
import top.easyblog.titan.response.ResultCode;
import top.easyblog.titan.service.data.AccessAccountService;
import top.easyblog.titan.service.data.AccessUserService;

/**
 * @author: frank.huang
 * @date: 2022-01-29 20:54
 */
@Slf4j
@Component
public class UserNameLoginPolicy implements LoginPolicy {

    @Autowired
    private AccessAccountService accessAccountService;
    @Autowired
    private AccessUserService accessUserService;

    @Override
    public LoginDetailsBean doLogin(LoginRequest request) {
        return null;
    }

    @Transaction
    @Override
    public UserDetailsBean doRegister(RegisterUserRequest request) {
        //1.根据nick_name查询用户信息
        QueryUserRequest queryUserRequest = QueryUserRequest.builder().nickName(request.getIdentifier()).build();
        User user = accessUserService.queryByRequest(queryUserRequest);
        if (Objects.nonNull(user)) {
            //1.1 用户名重复校验
            log.info("Error: repeat user_name:{} of user:{}", request.getIdentifier(), user.getId());
            throw new BusinessException(ResultCode.REPEAT_USER_NAME);
        }
        //2.前置密码合法性校验
        if (!StringUtils.equals(request.getCredential(), request.getCredentialAgain())) {
            throw new BusinessException(ResultCode.PASSWORD_NOT_EQUAL);
        }
        if (checkPasswordValid(request.getCredential())) {
            throw new BusinessException(ResultCode.PASSWORD_NOT_VALID);
        }
        //3.新建用户信息
        CreateUserRequest createUserRequest = CreateUserRequest.builder().nickName(request.getIdentifier()).build();
        User newUser = accessUserService.insertSelective(createUserRequest);

        //4.新建用户账号信息
        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
                .userId(newUser.getId())
                .identityType((int) IdentifierType.USER_NAME.getCode())
                .identifier(request.getIdentifier())
                .credential(request.getCredential())
                .verified(Status.ENABLE.getCode())
                .status(AccountStatus.ACTIVE.getCode())
                .build();
        accessAccountService.insertSelective(createAccountRequest);
        return null;
    }


}