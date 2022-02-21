package com.example.geektrust.domain;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allocation {
    private double equity;
    private double debt;
    private double gold;
}
