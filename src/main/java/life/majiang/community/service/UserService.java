package life.majiang.community.service;

import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

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
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser != null) {
            // 更新
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        } else {
            // 插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
