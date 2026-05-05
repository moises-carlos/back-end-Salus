package com.salusteam.salus.monitoring.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class MoodRequestDTO {

    @NotNull
    @Min(1) @Max(5)
    private Integer moodLevel;
    private List<String> tags;
    private String notes;
}