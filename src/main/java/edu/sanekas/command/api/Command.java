package edu.sanekas.command.api;

import edu.sanekas.api.Nominal;
import edu.sanekas.wrapper.api.OutputWrapper;

import java.util.Map;

public interface Command {
    OutputWrapper execute(Map<Nominal, Integer> commandOptions);
}
