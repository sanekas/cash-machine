package edu.sanekas.console.api;

import edu.sanekas.wrapper.api.InputWrapper;

/**
 * InputProcesser handles IO requests and coverts them to custom wrapper
 */
public interface InputProcesser {
    InputWrapper processInput(String input);
}
