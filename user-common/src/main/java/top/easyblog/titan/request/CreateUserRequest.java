package top.easyblog.titan.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册请求参数
 *
 * @author: frank.huang
 * @date: 2021-11-01 20:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private Byte identifierType;
    private String identifier;
    private String credential;
    private String credentialAgain;
    private String device;
    private String deviceOS;
    private String deviceIp;
}
