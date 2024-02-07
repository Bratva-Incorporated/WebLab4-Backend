package com.porunit.l4.data;

import lombok.Data;

@Data
public class ShotInputDTO {
    private double x;
    private double y;
    private int r;
    private boolean isHit;
}
