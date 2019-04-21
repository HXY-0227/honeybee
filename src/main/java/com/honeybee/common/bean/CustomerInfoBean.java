package com.honeybee.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Package: com.honeybee.common.bean
 * Description：
 * Date: Created in 2019/4/20 23:54
 * Company: haier
 */
@Setter
@Getter
@ToString
public class CustomerInfoBean implements Serializable {
    private static final long serialVersionUID = -1075943935428599980L;

    /**
     * 新增会员
     */
    private String newMember;
    /**
     * 总收益
     */
    private String totalProfit;

    /**
     *会员数量
     */
    private String totalMember;

    /**
     *近一年的数据
     */
    private List<CustomerYearInfoBean> CustomerYearInfoBean;
}
