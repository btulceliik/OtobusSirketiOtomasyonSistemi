package com.example.otobussirketiotomasyonu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;


public class KoltukSec2Controller{
    @FXML
    private Button koltuk10Button;
    @FXML
    private Button koltuk11Button;
    @FXML
    private Button koltuk12Button;
    @FXML
    private Button koltuk13Button;
    @FXML
    private Button koltuk14Button;
    @FXML
    private Button koltuk15Button;
    @FXML
    private Button koltuk16Button;
    @FXML
    private Button koltuk17Button;
    @FXML
    private Button koltuk18Button;
    @FXML
    private Button koltuk19Button;
    @FXML
    private Button koltuk1Button;
    @FXML
    private Button koltuk20Button;
    @FXML
    private Button koltuk21Button;
    @FXML
    private Button koltuk22Button;
    @FXML
    private Button koltuk23Button;
    @FXML
    private Button koltuk24Button;
    @FXML
    private Button koltuk25Button;
    @FXML
    private Button koltuk26Button;
    @FXML
    private Button koltuk27Button;
    @FXML
    private Button koltuk28Button;
    @FXML
    private Button koltuk29Button;
    @FXML
    private Button koltuk2Button;
    @FXML
    private Button koltuk30Button;
    @FXML
    private Button koltuk31Button;
    @FXML
    private Button koltuk32Button;
    @FXML
    private Button koltuk33Button;
    @FXML
    private Button koltuk34Button;
    @FXML
    private Button koltuk35Button;
    @FXML
    private Button koltuk36Button;
    @FXML
    private Button koltuk37Button;
    @FXML
    private Button koltuk38Button;
    @FXML
    private Button koltuk39Button;
    @FXML
    private Button koltuk3Button;
    @FXML
    private Button koltuk40Button;
    @FXML
    private Button koltuk41Button;
    @FXML
    private Button koltuk42Button;
    @FXML
    private Button koltuk43Button;
    @FXML
    private Button koltuk44Button;
    @FXML
    private Button koltuk45Button;
    @FXML
    private Button koltuk46Button;
    @FXML
    private Button koltuk4Button;
    @FXML
    private Button koltuk5Button;
    @FXML
    private Button koltuk6Button;
    @FXML
    private Button koltuk7Button;
    @FXML
    private Button koltuk8Button;
    @FXML
    private Button koltuk9Button;
    @FXML
    private AnchorPane otobus2AnchorPane;

    private static final int MAX_KOLTUK_SAYISI = 46;
    private int secilenKoltukNo = -1; // -1, herhangi bir koltuk seçilmediği anlamına gelir
    // Getter metodu ekleyerek seçilen koltuk numarasına erişim sağlayın
    public int getSecilenKoltukNo() {

        return secilenKoltukNo;
    }

    Connection connection = dbConnector.getConnection();
    private KullaniciController kullaniciController;
    public void setKullaniciController(KullaniciController kullaniciController) {
        this.kullaniciController = kullaniciController;
    }

    public void kontrolEtVeRenklendir2(String kalkisYeri, String varisYeri, LocalDateTime seferZamani, String otobusSirketiAdi) {
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
    void koltuk10Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 10");
        kullaniciController.setSecilenKoltukNo("10");
        stageKapat(event);

    }

    @FXML
    void koltuk11Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 11");
        kullaniciController.setSecilenKoltukNo("11");
        stageKapat(event);

    }

    @FXML
    void koltuk12Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 12");
        kullaniciController.setSecilenKoltukNo("12");
        stageKapat(event);

    }

    @FXML
    void koltuk13Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 13");
        kullaniciController.setSecilenKoltukNo("13");
        stageKapat(event);

    }

    @FXML
    void koltuk14Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 14");
        kullaniciController.setSecilenKoltukNo("14");
        stageKapat(event);

    }

    @FXML
    void koltuk15Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 15");
        kullaniciController.setSecilenKoltukNo("15");
        stageKapat(event);

    }

    @FXML
    void koltuk16Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 16");
        kullaniciController.setSecilenKoltukNo("16");
        stageKapat(event);

    }

    @FXML
    void koltuk17Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 17");
        kullaniciController.setSecilenKoltukNo("17");
        stageKapat(event);

    }

    @FXML
    void koltuk18Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 18");
        kullaniciController.setSecilenKoltukNo("18");
        stageKapat(event);

    }

    @FXML
    void koltuk19Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 19");
        kullaniciController.setSecilenKoltukNo("19");
        stageKapat(event);

    }

    @FXML
    void koltuk1Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 1");
        kullaniciController.setSecilenKoltukNo("1");
        stageKapat(event);

    }

    @FXML
    void koltuk20Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 20");
        kullaniciController.setSecilenKoltukNo("20");
        stageKapat(event);

    }

    @FXML
    void koltuk21Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 21");
        kullaniciController.setSecilenKoltukNo("21");
        stageKapat(event);

    }

    @FXML
    void koltuk22Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 22");
        kullaniciController.setSecilenKoltukNo("22");
        stageKapat(event);

    }

    @FXML
    void koltuk23Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 23");
        kullaniciController.setSecilenKoltukNo("23");
        stageKapat(event);

    }

    @FXML
    void koltuk24Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 24");
        kullaniciController.setSecilenKoltukNo("24");
        stageKapat(event);

    }

    @FXML
    void koltuk25Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 25");
        kullaniciController.setSecilenKoltukNo("25");
        stageKapat(event);

    }

    @FXML
    void koltuk26Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 26");
        kullaniciController.setSecilenKoltukNo("26");
        stageKapat(event);

    }

    @FXML
    void koltuk27Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 27");
        kullaniciController.setSecilenKoltukNo("27");
        stageKapat(event);

    }

    @FXML
    void koltuk28Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 28");
        kullaniciController.setSecilenKoltukNo("28");
        stageKapat(event);

    }

    @FXML
    void koltuk29Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 29");
        kullaniciController.setSecilenKoltukNo("29");
        stageKapat(event);

    }

    @FXML
    void koltuk2Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 2");
        kullaniciController.setSecilenKoltukNo("2");
        stageKapat(event);

    }

    @FXML
    void koltuk30Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 30");
        kullaniciController.setSecilenKoltukNo("30");
        stageKapat(event);

    }

    @FXML
    void koltuk31Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 31");
        kullaniciController.setSecilenKoltukNo("31");
        stageKapat(event);

    }

    @FXML
    void koltuk32Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 32");
        kullaniciController.setSecilenKoltukNo("32");
        stageKapat(event);

    }

    @FXML
    void koltuk33Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 33");
        kullaniciController.setSecilenKoltukNo("33");
        stageKapat(event);

    }

    @FXML
    void koltuk34Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 34");
        kullaniciController.setSecilenKoltukNo("34");
        stageKapat(event);

    }

    @FXML
    void koltuk35Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 35");
        kullaniciController.setSecilenKoltukNo("35");
        stageKapat(event);

    }

    @FXML
    void koltuk36Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 36");
        kullaniciController.setSecilenKoltukNo("36");
        stageKapat(event);

    }

    @FXML
    void koltuk37Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 37");
        kullaniciController.setSecilenKoltukNo("37");
        stageKapat(event);

    }

    @FXML
    void koltuk38Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 38");
        kullaniciController.setSecilenKoltukNo("38");
        stageKapat(event);

    }

    @FXML
    void koltuk39Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 39");
        kullaniciController.setSecilenKoltukNo("39");
        stageKapat(event);

    }

    @FXML
    void koltuk3Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 3");
        kullaniciController.setSecilenKoltukNo("3");
        stageKapat(event);

    }

    @FXML
    void koltuk40Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 40");
        kullaniciController.setSecilenKoltukNo("40");
        stageKapat(event);

    }

    @FXML
    void koltuk41Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 41");
        kullaniciController.setSecilenKoltukNo("41");
        stageKapat(event);

    }

    @FXML
    void koltuk42Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 42");
        kullaniciController.setSecilenKoltukNo("42");
        stageKapat(event);

    }

    @FXML
    void koltuk43Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 43");
        kullaniciController.setSecilenKoltukNo("43");
        stageKapat(event);

    }

    @FXML
    void koltuk44Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 44");
        kullaniciController.setSecilenKoltukNo("44");
        stageKapat(event);

    }

    @FXML
    void koltuk45Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 45");
        kullaniciController.setSecilenKoltukNo("45");
        stageKapat(event);

    }

    @FXML
    void koltuk46Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 46");
        kullaniciController.setSecilenKoltukNo("46");
        stageKapat(event);

    }

    @FXML
    void koltuk4Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 4");
        kullaniciController.setSecilenKoltukNo("4");
        stageKapat(event);

    }

    @FXML
    void koltuk5Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 5");
        kullaniciController.setSecilenKoltukNo("5");
        stageKapat(event);

    }

    @FXML
    void koltuk6Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 6");
        kullaniciController.setSecilenKoltukNo("6");
        stageKapat(event);

    }

    @FXML
    void koltuk7Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 7");
        kullaniciController.setSecilenKoltukNo("7");
        stageKapat(event);

    }

    @FXML
    void koltuk8Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 8");
        kullaniciController.setSecilenKoltukNo("8");
        stageKapat(event);

    }

    @FXML
    void koltuk9Method(ActionEvent event) {
        System.out.println("Düğmeye tıklandı: 9");
        kullaniciController.setSecilenKoltukNo("9");
        stageKapat(event);

    }

    private Button getButtonByKoltukNumarasi(int koltukNumarasi) {
        switch (koltukNumarasi) {
            case 1:
                return koltuk1Button;
            case 2:
                return koltuk2Button;
            case 3:
                return koltuk3Button;
            case 4:
                return koltuk4Button;
            case 5:
                return koltuk5Button;
            case 6:
                return koltuk6Button;
            case 7:
                return koltuk7Button;
            case 8:
                return koltuk8Button;
            case 9:
                return koltuk9Button;
            case 10:
                return koltuk10Button;
            case 11:
                return koltuk11Button;
            case 12:
                return koltuk12Button;
            case 13:
                return koltuk13Button;
            case 14:
                return koltuk14Button;
            case 15:
                return koltuk15Button;
            case 16:
                return koltuk16Button;
            case 17:
                return koltuk17Button;
            case 18:
                return koltuk18Button;
            case 19:
                return koltuk19Button;
            case 20:
                return koltuk20Button;
            case 21:
                return koltuk21Button;
            case 22:
                return koltuk22Button;
            case 23:
                return koltuk23Button;
            case 24:
                return koltuk24Button;
            case 25:
                return koltuk25Button;
            case 26:
                return koltuk26Button;
            case 27:
                return koltuk27Button;
            case 28:
                return koltuk28Button;
            case 29:
                return koltuk29Button;
            case 30:
                return koltuk30Button;
            case 31:
                return koltuk31Button;
            case 32:
                return koltuk32Button;
            case 33:
                return koltuk33Button;
            case 34:
                return koltuk34Button;
            case 35:
                return koltuk35Button;
            case 36:
                return koltuk36Button;
            case 37:
                return koltuk37Button;
            case 38:
                return koltuk38Button;

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