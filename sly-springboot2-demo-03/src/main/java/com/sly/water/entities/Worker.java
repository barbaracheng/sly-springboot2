package com.sly.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:送水工实体类
 *
 * @author leyuan
 * @date 2021/7/23 15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    private Integer wid;

    private String workerName;

    private Integer workerSalary;

    private double workerMoney;

    private String workerImage;
}
