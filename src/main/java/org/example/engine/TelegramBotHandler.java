package org.example.engine;

import org.example.Util;
import org.example.statemachine.ChatsRouter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

public class TelegramBotHandler extends TelegramLongPollingBot {

    private String botUsername;
    private String botToken;

    private ChatsRouter chatsRouter;

    public TelegramBotHandler() {
        botUsername = "guessmgokbot";
        botToken = "7704976204:AAGMFUwvQbsdi7zJaBJYiYXdMvyFsGl4vts";

        chatsRouter = new ChatsRouter();

    }

    private Random random = new Random();
    private int compNumber, userNumber;


    @Override
    public void onUpdateReceived(Update update) {
        long chatId = 0;
        int messageId = 0;
        String textFromUser = "";

        chatsRouter = new ChatsRouter();

        try {
            if (update.hasMessage()) {
                chatId = update.getMessage().getChatId();
                messageId = update.getMessage().getMessageId();
                textFromUser = update.getMessage().getText();
            } else if (update.hasCallbackQuery()) {
                chatId = update.getCallbackQuery().getMessage().getChatId();
                messageId = update.getCallbackQuery().getMessage().getMessageId();
                textFromUser = update.getCallbackQuery().getData();
            }


            SendMessage messageToUser = chatsRouter.route(chatId, textFromUser);

            execute(messageToUser);
        } catch (Exception e) {

            e.printStackTrace();

            DeleteMessage deleteMessageToUser = new DeleteMessage();
            deleteMessageToUser.setChatId(chatId);
            deleteMessageToUser.setMessageId(messageId);

            try {
                execute(deleteMessageToUser);
            } catch (TelegramApiException ee) {
                ee.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}