package com.sly.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/28 9:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    private String workerName;

    private Integer workerSalary;

    private double workerMoney;

    private Integer sendWaterCount;

    private double finalSalary;
}
