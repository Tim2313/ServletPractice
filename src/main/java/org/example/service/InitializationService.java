package org.example.service;

import org.example.controller.DeveloperController;
import org.example.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitializationService {

    private final DeveloperController developerController = DeveloperController.getInstance();
    private final MainController mainController = MainController.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationService.class);
    private static InitializationService instance;

    public void getInitialized() {

        developerController.init();
        mainController.init();

        LOGGER.info("Controllers are successfully initialized!");
    }

    public static InitializationService getInstance() {
        if (instance == null) {
            instance = new InitializationService();
        }
        return instance;
    }
}
