package com.example.otobussirketiotomasyonu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SifreYenilemeController {
    @FXML
    private TextField KullaniciAdiField;

    @FXML
    private Label kullaniciAdiText;

    @FXML
    private PasswordField sifreField;

    @FXML
    private PasswordField tekrarSifreField;

    @FXML
    private Button sifreYenileButton;

    @FXML
    public void sifreyiYenileButton(ActionEvent actionEvent) {
        String username = KullaniciAdiField.getText();
        String newPassword = sifreField.getText();

        // Şifre ve tekrar şifre alanlarını kontrol et
        if (!newPassword.equals(tekrarSifreField.getText())) {
            showAlertDialog("Şifre Hatası", "Şifreler uyuşmuyor!");
            return;
        }

        // Yeni şifre kurallarını kontrol et
        if (!isValidPassword(newPassword)) {
            showAlertDialog("Geçersiz Şifre", "Şifre en az 6 karakterden oluşmalı ve en az 1 küçük harf, 1 büyük harf ve 1 rakam içermelidir.");
            return;
        }

        // Veritabanı bağlantısını al
        Connection connection = dbConnector.getConnection();

        if (connection != null) {
            try {
                // Kullanıcı adının veritabanında olup olmadığını kontrol et
                String checkUserQuery = "SELECT * FROM Yetkili WHERE KullaniciAdi = ?";
                try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery)) {
                    checkUserStatement.setString(1, username);
                    ResultSet resultSet = checkUserStatement.executeQuery();

                    if (resultSet.next()) {
                        // Kullanıcı adı varsa, şifreyi güncelle
                        String updatePasswordQuery = "UPDATE Yetkili SET Sifre = ? WHERE KullaniciAdi = ?";
                        try (PreparedStatement updatePasswordStatement = connection.prepareStatement(updatePasswordQuery)) {
                            updatePasswordStatement.setString(1, newPassword);
                            updatePasswordStatement.setString(2, username);
                            updatePasswordStatement.executeUpdate();

                            showAlertDialog("Başarılı", "Şifreniz başarıyla yenilendi!");
                        }
                    } else {
                        showAlertDialog("Kullanıcı Adı Hatası", "Hata: Kullanıcı adı bulunamadı!");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Veritabanı bağlantısını kapat
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Yeni şifre kurallarını kontrol eden metod
    private boolean isValidPassword(String password) {
        // En az 6 karakter, en az 1 küçük harf, en az 1 büyük harf, en az 1 rakam içermeli
        return password.length() >= 6 && password.matches(".*[a-z]+.*") && password.matches(".*[A-Z]+.*") && password.matches(".*\\d+.*");
    }

    // Alert penceresi gösteren metod
    private void showAlertDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
}
}