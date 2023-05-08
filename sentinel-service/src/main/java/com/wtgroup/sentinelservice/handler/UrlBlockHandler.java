package com.wtgroup.sentinelservice.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 针对Sentinel的RESTful接口的异常处理
 */
@Component
public class UrlBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {

        String msg = null;
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
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        // ObjectMapper是内置Jackson的序列化工具类，常用于将JAVA对象转化为JSON字符串，将JSON字符串转化为JAVA对象
        // readValue()从指定输入将Json转为对象，writeValue则将对象转化为Json对象写入指定输出
        ObjectMapper mapper = new ObjectMapper();
        // 某个对象属性为null时不进行序列化输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.writeValue(httpServletResponse.getWriter(), msg);
    }
}
