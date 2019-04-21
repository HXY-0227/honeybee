package com.honeybee.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Package: com.honeybee.common.bean
 * Description：
 * Date: Created in 2019/4/21 15:26
 * Company: haier
 */
@Setter
@Getter
@ToString
public class CustomerYearInfoBean implements Serializable {

    private static final long serialVersionUID = 6215959623128445890L;

    /**
     * 时间  yyyy-mm
     */
    private String time;
    /**
     * 新增会员
     */
    private String newMember;
    /**
     * 总收益
     */
    private String totalProfit;
}
