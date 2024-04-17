package com.example.backend_academic_monitoring.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receiver implements Serializable {
    @JsonProperty("receiver")
    private String receiver;
    @JsonProperty("shift")
    private Integer shift;
    @JsonProperty("grade")
    private List<Integer> grade;
}
