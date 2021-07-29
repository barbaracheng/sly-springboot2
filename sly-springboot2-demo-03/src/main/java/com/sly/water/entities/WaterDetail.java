package com.sly.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/28 10:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterDetail {
    private String workerName;

    private String custList;

    private Integer sendCount;
}
