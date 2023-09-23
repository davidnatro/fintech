package com.fintech.fintech;

import com.fintech.fintech.util.AppStarter;
import java.io.IOException;

public class WeatherApplication {

    public static void main(String[] args) {
        try {
            new AppStarter().run();
        } catch (IOException exception) {
            System.out.println("Cannot run program because of input/output error -> message: '"
                                       + exception.getMessage() + "'");
        }
    }
}
