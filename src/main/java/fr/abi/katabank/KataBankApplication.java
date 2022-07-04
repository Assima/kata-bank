package fr.abi.katabank;

import fr.abi.katabank.controller.KataBankController;

public class KataBankApplication {

    public static void main(String[] args) {
        KataBankController kataBankController = new KataBankController();
        kataBankController.run();
    }
}
