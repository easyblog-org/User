package top.easyblog.titan.service.data;

import com.google.common.collect.Iterables;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import top.easyblog.titan.dao.auto.mapper.UserHeaderImgMapper;
import top.easyblog.titan.dao.auto.model.UserHeaderImg;
import top.easyblog.titan.dao.auto.model.UserHeaderImgExample;
import top.easyblog.titan.request.QueryUserHeaderImgRequest;
import top.easyblog.titan.request.QueryUserHeaderImgsRequest;

/**
 * @author frank.huang
 * @date 2022/01/30 10:53
 */
@Service
public class AccessUserHeaderImgService {

    @Autowired
    private UserHeaderImgMapper userHeaderImgMapper;


    public UserHeaderImg queryByRequest(QueryUserHeaderImgRequest request) {
        UserHeaderImgExample example = new UserHeaderImgExample();
        UserHeaderImgExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(request.getId())) {
            criteria.andIdEqualTo(request.getId());
        }
        if (Objects.nonNull(request.getUserId())) {
            criteria.andUserIdEqualTo(request.getUserId());
        }
        if (CollectionUtils.isNotEmpty(request.getStatuses())) {
            criteria.andStatusIn(request.getStatuses());
        }
        return Iterables.getFirst(userHeaderImgMapper.selectByExample(example), null);
    }


    public List<UserHeaderImg> queryHeaderImgList(QueryUserHeaderImgsRequest request) {
        UserHeaderImgExample example = new UserHeaderImgExample();
        UserHeaderImgExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(request.getId())) {
            criteria.andIdEqualTo(request.getId());
        } else if (CollectionUtils.isNotEmpty(request.getIds())) {
            criteria.andIdIn(request.getIds());
        }
        if (Objects.nonNull(request.getUserId())) {
            criteria.andIdEqualTo(request.getUserId());
        } else if (CollectionUtils.isNotEmpty(request.getUserIds())) {
            criteria.andIdIn(request.getUserIds());
        }
        if (Objects.nonNull(request.getStatus())) {
            criteria.andStatusEqualTo(request.getStatus());
        }
        
        if (Objects.nonNull(request.getLimit())) {
            example.setLimit(request.getLimit());
        }
        if (Objects.nonNull(request.getOffset())) {
            example.setOffset(request.getOffset());
        }
        return userHeaderImgMapper.selectByExample(example);
    }

    public void updateHeaderImgByRequest(UserHeaderImg img) {
        img.setUpdateTime(new Date());
        userHeaderImgMapper.updateByPrimaryKey(img);
    }
}