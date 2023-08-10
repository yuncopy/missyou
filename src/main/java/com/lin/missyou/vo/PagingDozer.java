package com.lin.missyou.vo;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
//泛型抽象类
public class PagingDozer<T,K> extends Paging{

    @SuppressWarnings("unchecked")
    //定义构造函数初始化
    public PagingDozer(Page<T> pageT,Class<K> classK){
        this.initPageParameters(pageT);

        List<T> tList = pageT.getContent();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<K> voList = new ArrayList<>();
        tList.forEach(t -> {
            K vo = mapper.map(t,classK); // t 源对象  classK 目标对象
            voList.add(vo);
        });
        this.setItems(voList);
    }

}
