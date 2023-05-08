package com.wtgroup.sentinelservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.springframework.stereotype.Component;

/**
 * 订单接口实现类
 */
@Component
public class OrderImpl implements Order {

    @Override
    @SentinelResource(value = "createOrder", blockHandler = "createOrderBlockHandler")
    public String createOrder() {
        return "创建订单成功！！";
    }

    @Override
    @SentinelResource("destroyOrder")
    public String destroyOrder() {
        return "销毁订单成功！！";
    }

    /**
     * blockHandler函数的访问范围是public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后一个额外的参数类型必须要是BlockException
     * blockHandler函数默认需要要和原方法在同一个类中，若希望使用其他类的函数，则需要使用blockHandlerClass指定对应类的Class对象，该类对应的方法必须为static，否则无法解析
     */
    public String createOrderBlockHandler(BlockException e) {

        String msg = "";
        if (e instanceof FlowException) {
            msg = "接口已被限流";
        } else if (e instanceof DegradeException) {
            msg = "接口已被熔断";
        } else if (e instanceof ParamFlowException) {
            msg = "热点参数限流";
        } else if (e instanceof SystemBlockException) {
            msg = "系统规则不满足要求";
        } else if (e instanceof AuthorityException) {
            msg = "授权规则不通过";
        }
        return msg;
    }
}
