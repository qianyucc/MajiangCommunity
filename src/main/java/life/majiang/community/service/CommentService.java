package life.majiang.community.service;

import life.majiang.community.dto.*;
import life.majiang.community.enums.*;
import life.majiang.community.exception.*;
import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author lijing
 * @date 2019-06-21-16:02
 * @discroption
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_WRONG);
        }
        User commentator = userMapper.selectByPrimaryKey(comment.getCommentator());
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            Question dbQuestion = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1L);
            commentExtMapper.incCommentCount(parentComment);

            // 增加通知消息
            Notification notification = new Notification();
            notification.setGmtCreate(System.currentTimeMillis());
            notification.setNotifier(comment.getCommentator());
            notification.setReceiver(dbQuestion.getCreator());
            notification.setOuterid(dbComment.getId());
            notification.setOuterTitle(dbComment.getContent());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setType(NotificationTypeEnum.REPLY_COMMENT.getType());
            notification.setNotifierName(commentator.getName());
            notificationMapper.insert(notification);

        } else {
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

            // 增加通知消息
            Notification notification = new Notification();
            notification.setGmtCreate(System.currentTimeMillis());
            notification.setNotifier(comment.getCommentator());
            notification.setReceiver(question.getCreator());
            notification.setOuterid(question.getId());
            notification.setOuterTitle(question.getTitle());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setType(NotificationTypeEnum.REPLY_QUESTION.getType());
            notification.setNotifierName(commentator.getName());
            notificationMapper.insert(notification);
        }
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum question) {
        // 查找到所有评论
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(question.getType());
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        ArrayList<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        // 转化为Map减少时间复杂度，提高效率
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
