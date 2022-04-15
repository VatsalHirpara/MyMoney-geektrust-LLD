package com.example.geektrust.factory;

import com.example.geektrust.command.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandFactory {
    public static Command createCommand(String commandString) {
        List<String> parameters = Arrays.stream(commandString.trim().split(" ")).collect(Collectors.toList());
        return Command.builder()
                .name(parameters.get(0))
                .parameters(new ArrayList<>(parameters.subList(1,parameters.size())))
                .build();
    }
}
