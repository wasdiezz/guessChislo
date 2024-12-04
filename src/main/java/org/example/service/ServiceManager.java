package org.example.service;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private Map<String, Service> methods;

    private GuessLogic guessLogic;

    public ServiceManager() {
        methods = new HashMap<>();
        guessLogic = new GuessLogic();

        methods.put(State.WaitingCommandStart, guessLogic::processWaitingCommandStart);
        methods.put(State.WaitingInputAnswer, guessLogic::processWaitingInputAnswer);
        methods.put(State.WaitingUserGuess, guessLogic::processWaitingUserGuess);


    }

    public SendMessage callLogicMethod(String textFromUser, TransmittedData transmittedData) throws Exception {
        String state = transmittedData.getState();
        return methods.get(state).process(textFromUser, transmittedData);
    }
}
