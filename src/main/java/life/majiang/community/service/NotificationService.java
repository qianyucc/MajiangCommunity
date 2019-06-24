package life.majiang.community.service;

import life.majiang.community.dto.*;
import life.majiang.community.enums.*;
import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import org.apache.ibatis.session.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author lijing
 * @date 2019-06-24-18:26
 * @discroption
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private CommentMapper commentMapper;

    public PageInfoDTO list(Long id, Integer page, Integer size) {

        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id);
        Integer totalCount = (int) notificationMapper.countByExample(example);
        // 计算总页数
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 0) {
            page = 1;
        }

        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        PageInfoDTO<NotificationDTO> pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setData(notificationDTOs);
        pageInfoDTO.setPageInfo(totalPage, page);

        //计算offset和size
        Integer offset = size * (page - 1);

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id);
        notificationExample.setOrderByClause("gmt_create desc");
        List<Notification> notificationList = notificationMapper.selectByExampleWithRowbounds(notificationExample, new RowBounds(offset, size));

        for (Notification notification : notificationList) {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTOs.add(notificationDTO);
        }
        return pageInfoDTO;
    }

    public long getUnreadNotificationCount(User user) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(user.getId())
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification updNotification = new Notification();
        updNotification.setStatus(NotificationStatusEnum.READ.getStatus());
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andIdEqualTo(id);
        notificationMapper.updateByExampleSelective(updNotification, notificationExample);

        NotificationDTO notificationDTO = new NotificationDTO();
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(notification, notificationDTO);
        if(notification.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()){
            Comment comment = commentMapper.selectByPrimaryKey(notification.getOuterid());
            notificationDTO.setOuterid(comment.getParentId());
        }
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(updNotification.getType()));
        return notificationDTO;
    }
}
