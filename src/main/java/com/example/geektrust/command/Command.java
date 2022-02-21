package com.example.geektrust.command;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Command {
    private String name;
    private List<String> parameters;
}
