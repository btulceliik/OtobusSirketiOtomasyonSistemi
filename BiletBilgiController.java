package com.example.otobussirketiotomasyonu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*initialize metodu, pencere başlatıldığında çağrılır ve en son eklenen biletin bilgilerini veritabanından çeker.
 Eğer veri bulunursa, bu bilgileri ilgili JavaFX Label elemanlarına yerleştirir. Eğer veri bulunmazsa, bir uyarı mesajı gösterir.*/

public class BiletBilgiController implements Initializable {

    // FXML etiketlerine karşılık gelen Label elemanları
    @FXML
    private Label otobusSirketiAdiLabel;

    @FXML
    private Label KalkisYeriLabel;

    @FXML
    private Label varisYeriLabel;

    @FXML
    private Label seferZamaniLabel;

    @FXML
    private Label koltukYeriLabel;

    @FXML
    private Label adiLabel;

    @FXML
    private Label soyadiLabel;


    // Controller sınıfının başlatılması için kullanılan metot
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Veritabanı bağlantısını oluştur
            Connection connection = dbConnector.getConnection();

            // Bilet tablosundan en son eklenen kaydı bulmak için SQL sorgusu
            String query = "SELECT TOP 1 Adi, Soyadi, KalkisYeri, VarisYeri, SeferZamani, OtobusSirketiAdi, KoltukNo FROM Bilet ORDER BY EklenmeZamani DESC";

            // Prepared Statement ve ResultSet kullanarak sorguyu çalıştır
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    // Eğer veri bulunduysa, Label elemanlarına verileri yerleştir
                    adiLabel.setText(resultSet.getString("Adi"));
                    soyadiLabel.setText(resultSet.getString("Soyadi"));
                    KalkisYeriLabel.setText(resultSet.getString("KalkisYeri"));
                    varisYeriLabel.setText(resultSet.getString("VarisYeri"));
                    seferZamaniLabel.setText(resultSet.getString("SeferZamani"));
                    otobusSirketiAdiLabel.setText(resultSet.getString("OtobusSirketiAdi"));
                    koltukYeriLabel.setText(resultSet.getString("KoltukNo"));


                } else {
                    // Veri bulunamadıysa kullanıcıya uyarı mesajı göster
                    showAlert("Uyarı", "Veri Bulunamadı", "Veritabanında Bilet kaydı bulunmamaktadır.");
                }
            }
        } catch (SQLException e) {
            // SQL isteği sırasında bir hata oluştuğunda hata durumunu işle
            e.printStackTrace();
            // Hata durumunda işlemleri burada yönetebilirsiniz
        }
    }

    // Uyarı mesajı göstermek için yardımcı metot
    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}