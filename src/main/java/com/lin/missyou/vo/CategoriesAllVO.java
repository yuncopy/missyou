package com.lin.missyou.vo;

import com.lin.missyou.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoriesAllVO {
    private List<CategoryPureVO> roots;
    private List<CategoryPureVO> subs;

    public CategoriesAllVO(Map<Integer,List<Category>> map) {
        this.roots = map.get(1).stream()
                .map(r-> {
                    return new CategoryPureVO(r);
                })
                .collect(Collectors.toList());
        this.subs = map.get(2).stream()
                .map(CategoryPureVO::new)
                .collect(Collectors.toList());

        /*
        //第一种方式 VO构造函数直接处理 VO层按需处理数据比较合理
        List<Category> roots = map.get(1);
        roots.forEach(category1->{
            CategoryPureVO categoryPureVO = new CategoryPureVO(category1);
            this.roots.add(categoryPureVO);
        });

        List<Category> subs = map.get(2);
        subs.forEach(category2->{
            CategoryPureVO categoryPureVO = new CategoryPureVO(category2);
            this.subs.add(categoryPureVO);
        });
         */
    }
}
