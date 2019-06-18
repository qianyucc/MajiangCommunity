package life.majiang.community.service;

import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author lijing
 * @date 2019-06-15-12:56
 * @discroption
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            // 插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            // 更新
            User u = users.get(0);
            User updUser = new User();
            updUser.setGmtModified(System.currentTimeMillis());
            updUser.setName(user.getName());
            updUser.setAvatarUrl(user.getAvatarUrl());
            updUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(u.getId());
            userMapper.updateByExampleSelective(updUser, example);
        }
    }
}
