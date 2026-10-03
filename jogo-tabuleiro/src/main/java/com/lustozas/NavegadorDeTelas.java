package com.lustozas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavegadorDeTelas {

    private static Stage palcoPrincipal;

    public static void setPalcoPrincipal(Stage stage) {
        palcoPrincipal = stage;
    }

    public static void trocarTela(String caminhoFxml) throws IOException {
        Parent raiz = FXMLLoader.load(NavegadorDeTelas.class.getResource(caminhoFxml));
        aplicarCena(raiz);
    }

    public static <T> T trocarTelaComControlador(String caminhoFxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(NavegadorDeTelas.class.getResource(caminhoFxml));
        Parent raiz = loader.load();
        aplicarCena(raiz);
        return loader.getController();
    }

    private static void aplicarCena(Parent raiz) {
        Scene cena = new Scene(raiz);
        cena.getStylesheets().add(NavegadorDeTelas.class.getResource("/estilo.css").toExternalForm());
        palcoPrincipal.setScene(cena);
    }
}