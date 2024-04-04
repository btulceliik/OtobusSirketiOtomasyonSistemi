package com.example.otobussirketiotomasyonu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class YetkiliController {
    Connection connection = dbConnector.getConnection();
    Statement st = null;

    ResultSet set = null;
    @FXML
    private TextField kullaniciAd;
    @FXML
    private PasswordField sifre;

    @FXML
    public void girisYapButton(ActionEvent actionEvent){
        String kullaniciAdi = kullaniciAd.getText();
        String kullaniciSifre = sifre.getText();
        try{
            String query = "SELECT * FROM Yetkili WHERE KullaniciAdi='"+kullaniciAdi+"'and Sifre='"+kullaniciSifre+"'";
            st = connection.createStatement();
            set = st.executeQuery(query);
            if(set.next()){
                Parent root = FXMLLoader.load(getClass().getResource("yetkiliIslemler-view.fxml"));

                Stage stage = new Stage();
                stage.setTitle("Yetkili İşlemleri Ekranı");
                stage.setScene(new Scene(root, 650, 400));
                stage.show();
            }else{
                System.out.println("Hata!");
            }

        }catch (Exception e){
            e.printStackTrace();
}

}
    @FXML
    public void sifremiUnuttumButton(ActionEvent actionEvent) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("sifreYenileme-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Şifre Yenileme Ekranı");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
}

}
}