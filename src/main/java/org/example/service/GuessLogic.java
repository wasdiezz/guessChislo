package org.example.service;

import org.example.Util;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Random;
import java.util.Scanner;

public class GuessLogic {






    public SendMessage processWaitingCommandStart(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUSer = new SendMessage();
        messageToUSer.setChatId(transmittedData.getChatId());

        if(textFromUser.equals("/start")) {
            messageToUSer.setText("Я загадал число, попробуй угадать! Введите число");
            transmittedData.setState(State.WaitingInputAnswer);
            return messageToUSer;
        }


        return messageToUSer;
    }


    public SendMessage processWaitingInputAnswer(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUSer = new SendMessage();
        messageToUSer.setChatId(transmittedData.getChatId());
        Random random = new Random();
        int compNumber = random.nextInt(1, 100 + 1);
        int userNumber = Integer.parseInt(textFromUser);

        if(Util.canConvertStringToInt(textFromUser)) {
            userNumber = Integer.parseInt(textFromUser);
            if (userNumber < compNumber) {
                messageToUSer.setText("Введите число больше.");
                return messageToUSer;
            } else if (userNumber > compNumber) {
                messageToUSer.setText("Введите число меньше.");
                return messageToUSer;
            }else {
                messageToUSer.setText("Вы угадали!");
                transmittedData.setState(State.WaitingUserGuess);
                return messageToUSer;
            }
        } else {
            messageToUSer.setText("Ошибка! Вы ввели не число");
            return messageToUSer;
        }

    }


    public SendMessage processWaitingUserGuess(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUSer = new SendMessage();
        messageToUSer.setChatId(transmittedData.getChatId());
        messageToUSer.setText("asdasdsadsa");
        return messageToUSer;
    }

}
