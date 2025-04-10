/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://talelin.com
 * @免费专栏 $ http://course.talelin.com
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020-04-05 21:30
 */
package com.lin.missyou.sample.redis.subscribe;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCancel {

    @EventListener
    public void cancel(String message) {
        System.out.println("message:"+message);
    }


}
