package ru.shalya.soashopservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TotalCountDto {
    List<VehicleDto> vehicles;
    long totalCount;
}
