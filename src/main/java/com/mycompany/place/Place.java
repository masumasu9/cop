package com.mycompany.place;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Place extends TelegramLongPollingBot {
    
    @Override
    public void onUpdateReceived(Update update) {
      

        if (update.hasMessage()) {
    Long chatId = update.getMessage().getChatId();

    // Only work in these groups
    if (chatId.equals(-1002800500560L) || //coop 
        chatId.equals(-1002717991742L) || //test
        chatId.equals(-1002970524076L)) { //main

        Message msg = update.getMessage();
            
            // Log all messages for debugging
            System.out.println("📨 Message received in chat: " + msg.getChatId());
            
            // New member joined
            if (msg.getNewChatMembers() != null && !msg.getNewChatMembers().isEmpty()) {
                System.out.println("🎉 New member(s) joined - deleting message");
                deleteMessage(msg);
            }
            // Member left
            else if (msg.getLeftChatMember() != null) {
                System.out.println("👋 Member left - deleting message");
                deleteMessage(msg);
            }
        }
    }
    
    private void deleteMessage(Message msg) {
        DeleteMessage delete = new DeleteMessage();
        delete.setChatId(msg.getChatId().toString());
        delete.setMessageId(msg.getMessageId());
        
        try {
            execute(delete);
            System.out.println("✅ Successfully deleted join/leave message!");
        } catch (TelegramApiException e) {
            System.err.println("❌ Failed to delete message: " + e.getMessage());
            
            if (e.getMessage().contains("not enough rights")) {
                System.err.println("💡 Make sure bot is admin with 'Delete messages' permission");
            }
        }
    }
    
    @Override
    public String getBotUsername() {
        // Replace with your bot's actual username (without @)
        return "makkuronafukunookyakusan_bot";
    }
    
    @Override
    public String getBotToken() {
        // STEP 1: Replace this with your REAL token from BotFather
        String token = "7948637431:AAHZpSUHDufdYIZVMsbYk8XQbz5femMIBck";
        
        // STEP 2: Basic token validation
        if (!isValidTokenFormat(token)) {
            System.err.println("❌ INVALID TOKEN FORMAT!");
            System.err.println("Expected format: 1234567890:ABCdefGhIjKlMnOpQrStUvWxYz1234567890");
            System.err.println("Your token: " + token);
            System.err.println("Go to @BotFather and get a new token!");
        }
        
        return token;
    }
    
    private boolean isValidTokenFormat(String token) {
        // Basic format check
        if (token == null || token.length() < 20) {
            return false;
        }
        
        // Should contain exactly one colon
        String[] parts = token.split(":");
        if (parts.length != 2) {
            return false;
        }
        
        // First part should be numeric (bot ID)
        try {
            Long.parseLong(parts[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        
        // Second part should be long enough
        return parts[1].length() > 10;
    }
}
