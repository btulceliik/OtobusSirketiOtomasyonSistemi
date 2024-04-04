package com.example.otobussirketiotomasyonu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class YetkiliIslemlerController {

    @FXML
    private AnchorPane seferGoruntulePane;

    @FXML
    private TableView<Sefer> seferTable;

    @FXML
    private TableColumn<Sefer, String> otobusSirketiAdi;

    @FXML
    private TableColumn<Sefer, String> kalkisYeri;

    @FXML
    private TableColumn<Sefer, String> varisYeri;

    @FXML
    private TableColumn<Sefer, String> seferZamani;

    @FXML
    private TableColumn<Sefer, Double> biletFiyati;

    @FXML
    private AnchorPane biletGoruntulePane;

    @FXML
    private TableView<Bilet> biletTable;

    @FXML
    private TableColumn<Bilet, String> otobusSirketAdi;

    @FXML
    private TableColumn<Bilet, String> sefer;

    @FXML
    private TableColumn<Bilet, String> kalkis;

    @FXML
    private TableColumn<Bilet, String> varis;

    @FXML
    private TableColumn<Bilet, Integer> koltukNo;

    @FXML
    private AnchorPane yolcuGoruntulePane;

    @FXML
    private TableView<Yolcu> yolcuTable;

    @FXML
    private TableColumn<Yolcu, String> tcNo;

    @FXML
    private TableColumn<Yolcu, String> ad;

    @FXML
    private TableColumn<Yolcu, String> soyad;

    @FXML
    private TableColumn<Yolcu, String> dogumTarihi;

    @FXML
    private TableColumn<Yolcu, String> telefonNo;

   @FXML
    private Button seferEkleButton;
    @FXML
    private Button seferSilButton;

    @FXML
    private Button seferleriGoruntuleButton;

    @FXML
    private Button biletleriGoruntuleButton;

    @FXML
    private Button yolcularıGoruntuleButton;

    // Initialize metodunu tanımla
    @FXML
    private void initialize() {
        // TableColumn hücre değeri fabrikalarını başlat

        // Sefer bilgilerini içeren TableView için TableColumn'ları başlat
        otobusSirketiAdi.setCellValueFactory(new PropertyValueFactory<>("otobusSirketiAdi"));
        kalkisYeri.setCellValueFactory(new PropertyValueFactory<>("kalkisYeri"));
        varisYeri.setCellValueFactory(new PropertyValueFactory<>("varisYeri"));
        seferZamani.setCellValueFactory(new PropertyValueFactory<>("seferZamani"));
        biletFiyati.setCellValueFactory(new PropertyValueFactory<>("biletFiyati"));

        // Koltuk seçimi bilgilerini içeren TableView için TableColumn'ları başlat
        otobusSirketAdi.setCellValueFactory(new PropertyValueFactory<>("otobusSirketAdi"));
        kalkis.setCellValueFactory(new PropertyValueFactory<>("kalkis"));
        varis.setCellValueFactory(new PropertyValueFactory<>("varis"));
        sefer.setCellValueFactory(new PropertyValueFactory<>("sefer"));
        koltukNo.setCellValueFactory(new PropertyValueFactory<>("koltukNo"));

        // Yolcu bilgilerini içeren TableView için TableColumn'ları başlat
        tcNo.setCellValueFactory(new PropertyValueFactory<>("tcNo"));
        ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
        soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        dogumTarihi.setCellValueFactory(new PropertyValueFactory<>("dogumTarihi"));
        telefonNo.setCellValueFactory(new PropertyValueFactory<>("telefonNo"));

        // Başlangıçta Sefer TableView'ını görünür, diğer TableView'ları gizle
        seferTable.setVisible(true);
        biletTable.setVisible(false);
        yolcuTable.setVisible(false);
    }

    @FXML
    private void seferGoruntule() {
        // TableView'ın görünürlüğünü değiştirin
        seferTable.setVisible(!seferTable.isVisible());

        // TableView artık görünürse verileri getir ve doldur
        if (seferTable.isVisible()) {
            // TableView'da mevcut öğeleri temizle
            seferTable.getItems().clear();

            // Veritabanından veri alın ve TableView'ı doldurun
            try {
                Connection connection = dbConnector.getConnection();
                String query = "SELECT OtobusSirketiAdi, KalkisYeri, VarisYeri, SeferZamani, BiletFiyati FROM Sefer";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Sefer sefer = new Sefer(
                            resultSet.getString("OtobusSirketiAdi"),
                            resultSet.getString("KalkisYeri"),
                            resultSet.getString("VarisYeri"),
                            resultSet.getString("SeferZamani"),
                            resultSet.getDouble("BiletFiyati")
                    );
                    seferTable.getItems().add(sefer);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
        }

    }
    // Bilet görüntüleme metodunu tanımla
    @FXML
    /*TableView'ı (biletTable) görünür veya gizli yapmak ve TableView görünür olduğunda verileri bu TableView'a doldurmaktır.
    Eğer TableView zaten görünürse, mevcut verileri temizleyerek ve yeni verileri alarak TableView'ı günceller. */

    private void biletGoruntule() {
        // TableView'ın görünürlüğünü tersine çevir (görünürse gizle, gizliyse görünür yap)
        biletTable.setVisible(!biletTable.isVisible());

        // TableView görünürse, verileri getir ve TableView'ı doldur
        if (biletTable.isVisible()) {

            // TableView'daki mevcut verileri temizle
            biletTable.getItems().clear();

            // Veritabanından veri al ve TableView'ı doldur
            try {
                // Veritabanı bağlantısını oluştur
                Connection connection = dbConnector.getConnection();

                // Bilet tablosundan gerekli alanları içeren verileri seç
                String query = "SELECT OtobusSirketiAdi, KalkisYeri, VarisYeri, SeferZamani, KoltukNo FROM Bilet";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                // ResultSet üzerinde dönerek her bir kaydı TableView'a ekleyin
                while (resultSet.next()) {
                    Bilet bilet = new Bilet(
                            resultSet.getString("OtobusSirketiAdi"),
                            resultSet.getString("KalkisYeri"),
                            resultSet.getString("VarisYeri"),
                            resultSet.getString("SeferZamani"),
                            resultSet.getInt("KoltukNo")
                    );
                    biletTable.getItems().add(bilet);
                }
                // Veritabanı bağlantısını kapat
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void yolcuGoruntule() {

        yolcuTable.setVisible(!yolcuTable.isVisible());

        // TableView artık görünürse verileri getir ve doldur
        if (yolcuTable.isVisible()) {

            yolcuTable.getItems().clear();

            // Veritabanından veri alın ve TableView'ı doldurun
            try {
                Connection connection = dbConnector.getConnection();
                String query = "SELECT TCKimlikNo, Adi, Soyadi, DogumTarihi, TelefonNo FROM Bilet";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Yolcu yolcu = new Yolcu(
                            resultSet.getString("TCKimlikNo"),
                            resultSet.getString("Adi"),
                            resultSet.getString("Soyadi"),
                            resultSet.getString("DogumTarihi"),
                            resultSet.getString("TelefonNo")
                    );
                    yolcuTable.getItems().add(yolcu);
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
      }

}
    @FXML
    private void seferEkle() {
        try {
            Connection connection = dbConnector.getConnection();

            while (true) {
                TextInputDialog otobusSirketiDialog = new TextInputDialog();
                otobusSirketiDialog.setTitle("Yeni Sefer Ekle");
                otobusSirketiDialog.setHeaderText(null);
                otobusSirketiDialog.setContentText("Otobüs Şirketi Adı:");
                Optional<String> otobusSirketiAdiResult = otobusSirketiDialog.showAndWait();
                if (!otobusSirketiAdiResult.isPresent()) {
                    break;
                }
                String otobusSirketiAdi = otobusSirketiAdiResult.get();

                TextInputDialog kalkisYeriDialog = new TextInputDialog();
                kalkisYeriDialog.setTitle("Yeni Sefer Ekle");
                kalkisYeriDialog.setHeaderText(null);
                kalkisYeriDialog.setContentText("Kalkış Yeri:");
                Optional<String> kalkisYeriResult = kalkisYeriDialog.showAndWait();
                if (!kalkisYeriResult.isPresent()) {
                    break;
                }
                String kalkisYeri = kalkisYeriResult.get();

                TextInputDialog varisYeriDialog = new TextInputDialog();
                varisYeriDialog.setTitle("Yeni Sefer Ekle");
                varisYeriDialog.setHeaderText(null);
                varisYeriDialog.setContentText("Varış Yeri:");
                Optional<String> varisYeriResult = varisYeriDialog.showAndWait();
                if (!varisYeriResult.isPresent()) {
                    break;
                }
                String varisYeri = varisYeriResult.get();

                TextInputDialog seferZamaniDialog = new TextInputDialog();
                seferZamaniDialog.setTitle("Yeni Sefer Ekle");
                seferZamaniDialog.setHeaderText(null);
                seferZamaniDialog.setContentText("Sefer Zamanı:");
                Optional<String> seferZamaniResult = seferZamaniDialog.showAndWait();
                if (!seferZamaniResult.isPresent()) {
                    break;
                }
                String seferZamani = seferZamaniResult.get();

                TextInputDialog biletFiyatiDialog = new TextInputDialog();
                biletFiyatiDialog.setTitle("Yeni Sefer Ekle");
                biletFiyatiDialog.setHeaderText(null);
                biletFiyatiDialog.setContentText("Bilet Fiyatı:");
                Optional<String> biletFiyatiResult = biletFiyatiDialog.showAndWait();
                if (!biletFiyatiResult.isPresent()) {
                    break;
                }
                double biletFiyati = Double.parseDouble(biletFiyatiResult.get());

                // Yeni Sefer nesnesi oluştur
                Sefer yeniSefer = new Sefer(otobusSirketiAdi, kalkisYeri, varisYeri, seferZamani, biletFiyati);

                // TableView'a yeni seferi ekle
                seferTable.getItems().add(yeniSefer);

                int seferEkleResult = seferiVeritabaninaEkle(connection, otobusSirketiAdi, kalkisYeri, varisYeri, seferZamani, biletFiyati);

                if (seferEkleResult > 0) {
                    System.out.println("Sefer başarıyla eklendi.");
                } else {
                    System.out.println("Sefer eklenirken bir hata oluştu.");
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private int seferiVeritabaninaEkle(Connection connection, String otobusSirketiAdi, String kalkisYeri, String varisYeri, String seferZamani, double biletFiyati) throws SQLException {
        String ekleQuery = "INSERT INTO Sefer (OtobusSirketiAdi, KalkisYeri, VarisYeri, SeferZamani, BiletFiyati) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ekleStatement = connection.prepareStatement(ekleQuery);
        ekleStatement.setString(1, otobusSirketiAdi);
        ekleStatement.setString(2, kalkisYeri);
        ekleStatement.setString(3, varisYeri);
        ekleStatement.setString(4, seferZamani);
        ekleStatement.setDouble(5, biletFiyati);

        return ekleStatement.executeUpdate();
}



    @FXML
    private void seferSil() {
        try {
            // Seçili olan seferi al
            Sefer selectedSefer = seferTable.getSelectionModel().getSelectedItem();

            // Eğer hiçbir sefer seçilmediyse uyarı ver
            if (selectedSefer == null) {
                System.out.println("Lütfen silinecek bir sefer seçin.");
                return;
            }

            Connection connection = dbConnector.getConnection();
            String deleteQuery = "DELETE FROM Sefer WHERE OtobusSirketiAdi = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, selectedSefer.getOtobusSirketiAdi());

            int affectedRows = deleteStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Sefer başarıyla silindi.");
                seferTable.getItems().remove(selectedSefer); // Tablodan da seferi sil
            } else {
                System.out.println("Sefer silinirken bir hata oluştu.");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Veritabanına kayıt eklenirken bir hata oluştu: " + e.getMessage());
            // Hata durumunda isteğe bağlı olarak farklı bir işlem yapılabilir

        }


    }
}