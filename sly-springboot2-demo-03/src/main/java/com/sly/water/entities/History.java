package com.sly.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * TODO: 送水历史
 *
 * @author leyuan
 * @date 2021/7/24 9:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    /**
     * 送水历史编号
     */
    private Integer hid;

    private Worker worker;

    private Customer customer;

    /**
     * 使用年月日的格式显示送水时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendWaterTime;

    /**
     * 送水数量
     */
    private Integer sendWaterCount;
}
