package com.example.otobussirketiotomasyonu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class KoltukSecController {
    @FXML
    private Button koltuk1;
    @FXML
    private Button koltuk10;
    @FXML
    private Button koltuk11;
    @FXML
    private Button koltuk12;
    @FXML
    private Button koltuk13;
    @FXML
    private Button koltuk14;
    @FXML
    private Button koltuk15;
    @FXML
    private Button koltuk16;
    @FXML
    private Button koltuk17;
    @FXML
    private Button koltuk18;
    @FXML
    private Button koltuk19;
    @FXML
    private Button koltuk2;
    @FXML
    private Button koltuk20;
    @FXML
    private Button koltuk21;
    @FXML
    private Button koltuk22;
    @FXML
    private Button koltuk23;
    @FXML
    private Button koltuk24;
    @FXML
    private Button koltuk25;
    @FXML
    private Button koltuk26;
    @FXML
    private Button koltuk27;
    @FXML
    private Button koltuk28;
    @FXML
    private Button koltuk29;
    @FXML
    private Button koltuk3;
    @FXML
    private Button koltuk30;
    @FXML
    private Button koltuk31;
    @FXML
    private Button koltuk32;
    @FXML
    private Button koltuk33;
    @FXML
    private Button koltuk34;
    @FXML
    private Button koltuk35;
    @FXML
    private Button koltuk36;
    @FXML
    private Button koltuk37;
    @FXML
    private Button koltuk38;
    @FXML
    private Button koltuk4;
    @FXML
    private Button koltuk5;
    @FXML
    private Button koltuk6;
    @FXML
    private Button koltuk7;
    @FXML
    private Button koltuk8;
    @FXML
    private Button koltuk9;


    private static final int MAX_KOLTUK_SAYISI = 38;
    private int secilenKoltukNo = -1; // -1, herhangi bir koltuk seçilmediği anlamına gelir
    public int getSecilenKoltukNo() {   // Getter metodu ekleyerek seçilen koltuk numarasına erişim sağlayın
        return secilenKoltukNo; }
    Connection connection = dbConnector.getConnection();
    private KullaniciController kullaniciController;
    public void setKullaniciController(KullaniciController kullaniciController) {
        this.kullaniciController = kullaniciController;  }

    //veritabanından çekilecek verilere göre ilgili seferdeki otobüs koltuklarını cinsiyete bakarak renklendirme
    public void kontrolEtVeRenklendir(String kalkisYeri, String varisYeri, LocalDateTime seferZamani, String otobusSirketiAdi) {
        String query = "SELECT KoltukNo, Cinsiyet FROM Bilet WHERE KalkisYeri = ? AND VarisYeri = ? AND SeferZamani = ? AND OtobusSirketiAdi = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, kalkisYeri);
            preparedStatement.setString(2, varisYeri);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(seferZamani));
            preparedStatement.setString(4, otobusSirketiAdi);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int koltukNumarasi = resultSet.getInt("KoltukNo");
                String cinsiyet = resultSet.getString("Cinsiyet");
                Button currentButton = getButtonByKoltukNumarasi(koltukNumarasi);
                if (currentButton != null) {
                    if ("Erkek".equalsIgnoreCase(cinsiyet)) {
                        currentButton.setStyle("-fx-background-color: blue;");
                    } else if ("Kadın".equalsIgnoreCase(cinsiyet)) {
                        currentButton.setStyle("-fx-background-color: pink;");
                    } else {
                        currentButton.setStyle("-fx-background-color: white;");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void otobus1Koltuk1(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 1");
        kullaniciController.setSecilenKoltukNo("1");
        stageKapat(event);
    }


    @FXML
    private void otobus1Koltuk2(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 2");
        kullaniciController.setSecilenKoltukNo("2");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk3(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 3");
        kullaniciController.setSecilenKoltukNo("3");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk4(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 4");
        kullaniciController.setSecilenKoltukNo("4");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk5(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 5");
        kullaniciController.setSecilenKoltukNo("5");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk6(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 6");
        kullaniciController.setSecilenKoltukNo("6");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk7(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 7");
        kullaniciController.setSecilenKoltukNo("7");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk8(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 8");
        kullaniciController.setSecilenKoltukNo("8");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk9(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 9");
        kullaniciController.setSecilenKoltukNo("9");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk10(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 10");
        kullaniciController.setSecilenKoltukNo("10");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk11(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 11");
        kullaniciController.setSecilenKoltukNo("11");

        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk12(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 12");
        kullaniciController.setSecilenKoltukNo("12");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk13(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 13");
        kullaniciController.setSecilenKoltukNo("13");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk14(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 14");
        kullaniciController.setSecilenKoltukNo("14");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk15(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 15");
        kullaniciController.setSecilenKoltukNo("15");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk16(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 16");
        kullaniciController.setSecilenKoltukNo("16");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk17(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 17");
        kullaniciController.setSecilenKoltukNo("17");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk18(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 18");
        kullaniciController.setSecilenKoltukNo("18");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk19(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 19");
        kullaniciController.setSecilenKoltukNo("19");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk20(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 20");
        kullaniciController.setSecilenKoltukNo("20");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk21(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 21");
        kullaniciController.setSecilenKoltukNo("21");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk22(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 22");
        kullaniciController.setSecilenKoltukNo("22");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk23(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 23");
        kullaniciController.setSecilenKoltukNo("23");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk24(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 24");
        kullaniciController.setSecilenKoltukNo("24");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk25(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 25");
        kullaniciController.setSecilenKoltukNo("25");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk26(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 26");
        kullaniciController.setSecilenKoltukNo("26");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk27(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 27");
        kullaniciController.setSecilenKoltukNo("27");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk28(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 28");
        kullaniciController.setSecilenKoltukNo("28");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk29(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 29");
        kullaniciController.setSecilenKoltukNo("29");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk30(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 30");
        kullaniciController.setSecilenKoltukNo("30");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk31(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 31");
        kullaniciController.setSecilenKoltukNo("31");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk32(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 32");
        kullaniciController.setSecilenKoltukNo("32");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk33(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 33");
        kullaniciController.setSecilenKoltukNo("33");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk34(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 34");
        kullaniciController.setSecilenKoltukNo("34");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk35(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 35");
        kullaniciController.setSecilenKoltukNo("35");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk36(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 36");
        kullaniciController.setSecilenKoltukNo("36");
        stageKapat(event);

    }

    @FXML
    private void otobus1Koltuk37(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 37");
        kullaniciController.setSecilenKoltukNo("37");
        stageKapat(event);
    }

    @FXML
    private void otobus1Koltuk38(ActionEvent event) {
        // Burada yapılacak işlemler
        System.out.println("Düğmeye tıklandı: 38");
        kullaniciController.setSecilenKoltukNo("38");
        stageKapat(event);
    }

    private Button getButtonByKoltukNumarasi(int koltukNumarasi) {
        switch (koltukNumarasi) {
            case 1:
                return koltuk1;
            case 2:
                return koltuk2;
            case 3:
                return koltuk3;
            case 4:
                return koltuk4;
            case 5:
                return koltuk5;
            case 6:
                return koltuk6;
            case 7:
                return koltuk7;
            case 8:
                return koltuk8;
            case 9:
                return koltuk9;
            case 10:
                return koltuk10;
            case 11:
                return koltuk11;
            case 12:
                return koltuk12;
            case 13:
                return koltuk13;
            case 14:
                return koltuk14;
            case 15:
                return koltuk15;
            case 16:
                return koltuk16;
            case 17:
                return koltuk17;
            case 18:
                return koltuk18;
            case 19:
                return koltuk19;
            case 20:
                return koltuk20;
            case 21:
                return koltuk21;
            case 22:
                return koltuk22;
            case 23:
                return koltuk23;
            case 24:
                return koltuk24;
            case 25:
                return koltuk25;
            case 26:
                return koltuk26;
            case 27:
                return koltuk27;
            case 28:
                return koltuk28;
            case 29:
                return koltuk29;
            case 30:
                return koltuk30;
            case 31:
                return koltuk31;
            case 32:
                return koltuk32;
            case 33:
                return koltuk33;
            case 34:
                return koltuk34;
            case 35:
                return koltuk35;
            case 36:
                return koltuk36;
            case 37:
                return koltuk37;
            case 38:
                return koltuk38;

            default:
                return null;
        }
    }

    private void stageKapat(ActionEvent event) {
        // Olayın kaynağını (tıklanan düğme) al
        Node source = (Node) event.getSource();
        // Kaynağı içeren aşamayı (pencere) al
        Stage stage = (Stage) source.getScene().getWindow();
        // Aşamayı kapat
        stage.close();
    }

}