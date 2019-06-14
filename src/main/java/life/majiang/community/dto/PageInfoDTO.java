package life.majiang.community.dto;

import lombok.*;

import java.util.*;

/**
 * @author lijing
 * @date 2019-06-11-11:41
 * @discroption 储存分页信息
 */
@Data
public class PageInfoDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showEndPage;
    private boolean showNext;
    private Integer totalPage;

    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    public void setPageInfo(Integer totalPage, Integer page) {


        this.page = page;
        this.totalPage = totalPage;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }


        // 是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        // 是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        // 是否展示返回首页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        // 是否展示到尾页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
