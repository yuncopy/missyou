package com.lin.missyou.service;

import com.lin.missyou.bo.OrderMessageBO;
import com.lin.missyou.core.enumeration.OrderStatus;
import com.lin.missyou.exception.http.ServerErrorException;
import com.lin.missyou.model.Order;
import com.lin.missyou.repository.OrderRepository;
import com.lin.missyou.repository.UserCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CouponBackService {

    @Autowired
    private UserCouponRepository userCouponRepository;

    @Autowired
    private OrderRepository orderRepository;

    @EventListener //这里打上事件标记即可执行监听  监听事件
    @Transactional
    public void returnBack(OrderMessageBO messageBO){
        Long uid = messageBO.getUid();
        Long oid = messageBO.getOid();
        Long couponId = messageBO.getCouponId();
        if(-1 == couponId){
            return;
        }
        Optional<Order> orderOption = orderRepository.findFirstByUserIdAndId(uid,oid);
        Order order = orderOption.orElseThrow(()->
                new ServerErrorException(9999)
        );
        if(order.getStatusEnum().equals(OrderStatus.UNPAID)
            || order.getStatusEnum().equals(OrderStatus.CANCELED)){
            userCouponRepository.returnBack(couponId,uid);
        }
    }
}
