package com.lustozas;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage palcoPrincipal) throws Exception {
        NavegadorDeTelas.setPalcoPrincipal(palcoPrincipal);
        NavegadorDeTelas.trocarTela("/tela-inicial.fxml");
        palcoPrincipal.setTitle("Jogo de Tabuleiro");
        palcoPrincipal.setResizable(true);
        palcoPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}