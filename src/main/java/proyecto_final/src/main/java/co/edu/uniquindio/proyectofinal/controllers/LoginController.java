package co.edu.uniquindio.proyectofinal.controllers;

import co.edu.uniquindio.proyectofinal.model.Marketplace;

public class LoginController {
    ModelFactoryController modelFactoryController;
    Marketplace marketplace;


    public LoginController(ModelFactoryController modelFactoryController) {
        this.modelFactoryController = modelFactoryController;
        marketplace = modelFactoryController.getMarketplace();
    }

    public boolean loginAdmin(String user, String pass) {
        return modelFactoryController.loginAdmin(user, pass);
    }

    public boolean loginVendedor(String user, String pass, int i) {
        return modelFactoryController.loginVendedor(user, pass, i);
    }
}
