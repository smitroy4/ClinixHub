package com.smit.ClinixHub.dto;

import com.smit.ClinixHub.entity.types.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponseDto {
    private BloodGroupType bloodGroupType;
    private Long count;
}
