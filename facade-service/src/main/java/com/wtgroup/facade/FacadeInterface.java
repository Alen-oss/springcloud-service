package com.wtgroup.facade;

/**
 * dubbo服务对外暴漏的接口
 */
public interface FacadeInterface {

    public String getScore(String name);

    public int getRank(String name);
}
