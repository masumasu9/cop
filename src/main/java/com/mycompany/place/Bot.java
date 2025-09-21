/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.place;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            
            // Register your actual bot class (Place), not Bot
            botsApi.registerBot(new Place());
            
            System.out.println("Bot started successfully! 🎉");
            System.out.println("Press Ctrl+C to stop the bot.");
            
            // Keep the application running
            Thread.currentThread().join();
            
        } catch (TelegramApiException e) {
            System.err.println("Failed to start bot: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Bot stopped.");
        }

            int port = 8080;
        if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        }
        port(port);

        get("/health", (req, res) -> {
            res.type("text/plain");
            return "OK";
        });

        System.out.println("Health endpoint running on /health");
        
    }
}
