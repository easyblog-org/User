package top.easyblog.titan.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author: frank.huang
 * @date: 2022-01-29 21:48
 */
@Data
@Builder
public class QueryUserRequest {
    private Long id;
    private String nickName;
    /**
     * 查询选项，accounts、sign_log 分别标识需要查询哪些数据
     */
    private String sections;
}
