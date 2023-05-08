package com.wtgroup.nacosservice.dubbo;

import com.wtgroup.facade.FacadeInterface;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class FacadeInterfaceImpl implements FacadeInterface {

    public String getScore(String name) {

        return name + "的成绩为100分";
    }

    public int getRank(String name) {

        return 1;
    }
}
