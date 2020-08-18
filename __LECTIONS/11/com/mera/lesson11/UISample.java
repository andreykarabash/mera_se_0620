package com.mera.lesson11;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UISample {


    interface ButtonHandler {
        void onButtonClick();
    }

    static class Button {
        String name;
        ButtonHandler onClick;

        public Button(String name, ButtonHandler onClick) {
            this.name = name;
            this.onClick = onClick;
        }

        void click() {
            onClick.onButtonClick();
        }
    }




    public static void main(String[] args) {


        final LocalDateTime now = LocalDateTime.now();


        Button save = new Button("Save", () -> {
            System.out.println("Что-то сохранилось в базу данных, время создания кнопки " + now);
            System.out.println("Конец обработчика кнопки");
        });

        Button closeWindow = new Button("Close", new ButtonHandler() {
            LocalDateTime nowOutside = now;

            @Override
            public void onButtonClick() {
                System.out.println("Окно закрылось:" + nowOutside);
            }
        });

        // now = LocalDateTime.now();

        save.click();
        closeWindow.click();
    }
}
