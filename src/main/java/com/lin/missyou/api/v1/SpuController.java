package com.lin.missyou.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.lin.missyou.bo.PageCounter;
import com.lin.missyou.exception.http.NotFoundException;
import com.lin.missyou.model.Spu;
import com.lin.missyou.service.SpuService;
import com.lin.missyou.util.CommonUtil;
import com.lin.missyou.vo.Paging;
import com.lin.missyou.vo.PagingDozer;
import com.lin.missyou.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping("/id/{id}/detail")
    public Spu getSpu(@PathVariable @Positive Long id){
        Spu spu = this.spuService.getSpu(id);
        if(null == spu){
            throw new NotFoundException(30003);
        }
        return spu;
    }

    @GetMapping("/id/{id}/simplify")
    public SpuSimplifyVO getSimplifySpu(@PathVariable @Positive Long id){
        Spu spu = this.spuService.getSpu(id);
        if(null == spu){
            throw new NotFoundException(30003);
        }
        SpuSimplifyVO simplifyVO = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu,simplifyVO);
        return simplifyVO;
    }

    @GetMapping("/latest1")
    public List<SpuSimplifyVO> getLatestSpuList1(){
        //知识：拷贝赋值
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<Spu> spuList = this.spuService.getLatestPagingSpu1();
        //定义List 遍历赋值对象
        List<SpuSimplifyVO> vos = new ArrayList<>();
        spuList.forEach(item ->{
            SpuSimplifyVO vo = mapper.map(item,SpuSimplifyVO.class);
            vos.add(vo);
        });
        return vos;
    }

    @GetMapping("/latest2")
    public List<SpuSimplifyVO> getLatestSpuList2(@RequestParam(defaultValue = "0") Integer start,
                                                           @RequestParam(defaultValue = "10") Integer count){
        //使用对象形式进行分页参数转化 方便获取更容易表达
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start,count);
        //应用定义Page对象
        Page<Spu> spuList = this.spuService.getLatestPagingSpu(pageCounter.getPage(),pageCounter.getCount());
        //具体实现分页对象（SpuSimplifyVO）
        Paging<Spu> paging = new Paging<>(spuList);
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<SpuSimplifyVO> vos = new ArrayList<>();
        paging.getItems().forEach(item ->{
            //赋值VO对象
            SpuSimplifyVO vo = mapper.map(item,SpuSimplifyVO.class);
            vos.add(vo);
        });
        return vos;
    }


    @GetMapping("/latest")
    public PagingDozer<Spu,SpuSimplifyVO> getLatestSpuList(@RequestParam(defaultValue = "0") Integer start,
                                                @RequestParam(defaultValue = "10") Integer count){
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start,count);
        Page<Spu> spuPage = spuService.getLatestPagingSpu(pageCounter.getPage(),pageCounter.getCount());
        return new PagingDozer(spuPage,SpuSimplifyVO.class); //抽象出来形成泛型更好服务更多实体对象
    }

    @RequestMapping("/by/category/{id}")
    public PagingDozer<Spu,SpuSimplifyVO> getByCategoryId(@PathVariable @Positive(message = "{id.positive}") Long id,
                                                             @RequestParam(name="is_root",defaultValue = "false") Boolean isRoot,
                                                             @RequestParam(defaultValue = "0") Integer start,
                                                             @RequestParam(defaultValue = "10") Integer count){
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start,count);
        Page<Spu> spuPage = spuService.getCategory(id,isRoot,pageCounter.getPage(),pageCounter.getCount());
        return new PagingDozer(spuPage,SpuSimplifyVO.class);
    }

    @RequestMapping("/search")
    public PagingDozer<Spu,SpuSimplifyVO> search(@RequestParam(name="q") String keyword,
                                                 @RequestParam(defaultValue = "0") Integer start,
                                                 @RequestParam(defaultValue = "10") Integer count){
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start,count);
        Page<Spu> spuPage = spuService.search(keyword,pageCounter.getPage(),pageCounter.getCount());
        return new PagingDozer(spuPage,SpuSimplifyVO.class);
    }














//    @GetMapping("/latest")
//    public List<SpuSimplifyVO> getLatestSpuList(){
//        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
//        List<Spu> spuList = this.spuService.getLatestPagingSpu();
//        List<SpuSimplifyVO> voList = new ArrayList<>();
//        spuList.forEach(s->{
//            SpuSimplifyVO vo = mapper.map(s, SpuSimplifyVO.class);
//            voList.add(vo);
//        });
//        return voList;
//    }
}
