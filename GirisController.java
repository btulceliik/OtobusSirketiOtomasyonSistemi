package com.example.otobussirketiotomasyonu;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GirisController {
    @FXML
    //yetkili giriş butonuna tıkladığında gerçekleşecek olayları tanımlar
    public void yetkiliGirisButton(ActionEvent actionEvent){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("yetkili-view.fxml"));
            Stage stage = new Stage();
            //Stage, JavaFX uygulamasındaki pencere veya sahne
            stage.setTitle("Yetkili Giriş Ekranı");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    //kullanıcı giriş butonuna tıkladığında gerçekleşecek olayları tanımlar
    public void kullaniciGirisButton(ActionEvent actionEvent){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("kullanici-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Kullanıcı Ekranı");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
}
}


}