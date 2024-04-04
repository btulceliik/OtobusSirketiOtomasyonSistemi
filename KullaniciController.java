package com.example.otobussirketiotomasyonu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class KullaniciController {
    @FXML
    private TextField ad;

    @FXML
    private ComboBox<String> aracTur;


    @FXML
    private ComboBox<String> cinsiyet;

    @FXML
    private TextField dogumTarihi;

    @FXML
    private Button kaydetButton;

    @FXML
    private TextField koltukNo;

    @FXML
    private Button koltukSec;

    @FXML
    private TextField soyad;

    @FXML
    private TextField tcNo;

    @FXML
    private TextField telefon;

    @FXML
    private ComboBox<String> varisSehir;

    @FXML
    private ComboBox<String> otobusSirketAdi;
    @FXML
    private ComboBox<String> seferZamani;
    @FXML
    private ComboBox<String> kalkisSehir;
    @FXML
    private TextField biletUcreti;
    @FXML
    private Button odemeYap;
    @FXML
    private String secilenKoltukNo;


    // Veritabanı bağlantısını oluştur
    Connection connection = dbConnector.getConnection();

    // Controller sınıfının başlatılması için kullanılan metot
    public void initialize() {
        try {
            // Veritabanından sefer bilgilerini çek
            String query = "SELECT OtobusSirketiAdi, KalkisYeri, VarisYeri, SeferZamani, BiletFiyati FROM Sefer";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // ComboBox'ların seçeneklerini tutacak ObservableList'ler
                ObservableList<String> OtobusSirketOptions = FXCollections.observableArrayList();
                ObservableList<String> KalkisSehirOptions = FXCollections.observableArrayList();
                ObservableList<String> VarisSehirOptions = FXCollections.observableArrayList();
                ObservableList<String> SeferZamaniOptions = FXCollections.observableArrayList();

                // Veritabanından çekilen her bir satırı döngüyle işle
                while (resultSet.next()) {
                    // Veritabanındaki bilgileri çek
                    String otobusSirketiAdi = resultSet.getString("OtobusSirketiAdi");
                    OtobusSirketOptions.add(otobusSirketiAdi);

                    String kalkisYeri = resultSet.getString("KalkisYeri");
                    KalkisSehirOptions.add(kalkisYeri);

                    String varisYeri = resultSet.getString("VarisYeri");
                    VarisSehirOptions.add(varisYeri);

                    String seferZamani = resultSet.getString("SeferZamani");
                    SeferZamaniOptions.add(seferZamani);
                }

                // ComboBox'ları güncelle
                otobusSirketAdi.setItems(OtobusSirketOptions);
                kalkisSehir.setItems(KalkisSehirOptions);
                varisSehir.setItems(VarisSehirOptions);
                seferZamani.setItems(SeferZamaniOptions);

                // Seçilen değerlere göre BiletFiyati'ni alıp TextField'a yazdır
                otobusSirketAdi.setOnAction(event -> updateBiletFiyati());
                kalkisSehir.setOnAction(event -> updateBiletFiyati());
                varisSehir.setOnAction(event -> updateBiletFiyati());
                seferZamani.setOnAction(event -> updateBiletFiyati());
            }
        } catch (SQLException e) {
            // SQL isteği sırasında bir hata oluştuğunda hata durumunu işle
            e.printStackTrace(); // Hata durumunda hatayı yazdırabilir veya uygun bir şekilde ele alabilirsiniz
        }
    }

    // ComboBox'lardan seçilen değerlere göre BiletFiyati'ni güncelleyen metot,/*
    // Bu kod parçası, kullanıcının ComboBox'lardan seçtiği
    // otobüs şirketi,kalkış şehri, varış şehri ve sefer zamanına bağlı olarak veritabanından bilet ücretini çekip, bir
    // TextField'a bu ücreti yazan bir metodu temsil eder.
    private void updateBiletFiyati() {
        // ComboBox'lardan seçilen değerleri al
        String selectedOtobusSirketi = otobusSirketAdi.getSelectionModel().getSelectedItem();
        String selectedKalkisSehir = kalkisSehir.getSelectionModel().getSelectedItem();
        String selectedVarisSehir = varisSehir.getSelectionModel().getSelectedItem();
        String selectedSeferZamani = seferZamani.getSelectionModel().getSelectedItem();

        try {
            // Veritabanında seçilen değerlere göre BiletFiyati'nı almak için sorgu hazırla
            String biletFiyatiQuery = "SELECT BiletFiyati FROM Sefer WHERE OtobusSirketiAdi=? AND KalkisYeri=? AND VarisYeri=? AND SeferZamani=?";

            // PreparedStatement kullanarak sorguyu oluştur ve parametreleri set et
            try (PreparedStatement biletFiyatiStatement = connection.prepareStatement(biletFiyatiQuery)) {
                biletFiyatiStatement.setString(1, selectedOtobusSirketi);
                biletFiyatiStatement.setString(2, selectedKalkisSehir);
                biletFiyatiStatement.setString(3, selectedVarisSehir);
                biletFiyatiStatement.setString(4, selectedSeferZamani);

                // Sorguyu çalıştır ve sonuçları al
                ResultSet biletFiyatiResultSet = biletFiyatiStatement.executeQuery();

                // Eğer sonuçlar varsa
                if (biletFiyatiResultSet.next()) {
                    // BiletFiyati'ni al ve TextField'a set et
                    int biletFiyati = biletFiyatiResultSet.getInt("BiletFiyati");
                    biletUcreti.setText(String.valueOf(biletFiyati));
                }
            }
        } catch (SQLException e) {
            // SQL isteği sırasında bir hata oluştuğunda hata durumunu işle
            e.printStackTrace();
        }
    }
    // Koltuk seçimi butonuna tıklanınca gerçekleşen olay
    @FXML
    public void koltukSecButton(ActionEvent actionEvent) {
        /* Kullanıcı 2+1 veya 2+2 araç türünden birini seçerse, ilgili FXMLLoader ile uygun koltuk seçimi ekranını yükler ve gösterir.
        Ardından, ilgili koltuk seçimi kontrol sınıfına gerekli bilgileri gönderir*/
        // ComboBox'lardan seçilen değerleri al
        String kalkisYeri = kalkisSehir.getValue();
        String varisYeri = varisSehir.getValue();
        String selectedSeferZamaniStr = seferZamani.getValue(); // ComboBox'tan seçilen tarih ve saat bilgisini alma
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime seferZamani = LocalDateTime.parse(selectedSeferZamaniStr, formatter); // String'i LocalDateTime'a çevirme

        String otobusSirketiAdi = otobusSirketAdi.getValue();

        try {
            // ComboBox'tan seçilen araç türünü al
            String selectedAracTur = aracTur.getValue();
            if (selectedAracTur != null) {
                // Seçilen araç türüne göre farklı koltuk seçimi ekranlarını yükle ve göster
                if (selectedAracTur.equals("2+1")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("koltukSec-view.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    // KoltukSecController sınıfına eriş ve gerekli bilgileri gönder
                    KoltukSecController koltukSecController = loader.getController();
                    koltukSecController.setKullaniciController(this);
                    koltukSecController.kontrolEtVeRenklendir(kalkisYeri, varisYeri, seferZamani, otobusSirketiAdi);
                } else if (selectedAracTur.equals("2+2")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("koltukSec2-view.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    // KoltukSec2Controller sınıfına eriş ve gerekli bilgileri gönder
                    KoltukSec2Controller koltukSec2Controller = loader.getController();
                    koltukSec2Controller.setKullaniciController(this);
                    koltukSec2Controller.kontrolEtVeRenklendir2(kalkisYeri, varisYeri, seferZamani, otobusSirketiAdi);
                }
            }
        } catch (Exception e) {
            // Hata durumunda hatayı yazdır
            e.printStackTrace();
        }
    }
    // Ödeme yapma butonuna tıklanınca gerçekleşen olay
    @FXML
    public void odemeYapButton(ActionEvent actionEvent) {
        // kullanıcının girdiği bilgileri kullanarak yeni bir bilet kaydını veritabanına eklemektir.
        // PreparedStatement kullanarak parametrelerin set edilmesi ve ardından executeUpdate metodu ile SQL sorgusunun
        // çalıştırılması sağlanır.
        // Eğer işlem başarılı olursa, yeni bir ödeme yap ekranını yükler ve gösterir.
        try (Connection connection = dbConnector.getConnection()) {
            // Veritabanına yeni bir bilet eklemek için kullanılan SQL sorgusu
            String insertQuery = "INSERT INTO bilet (TCKimlikNo, Adi, Soyadi, DogumTarihi, TelefonNo, Cinsiyet, KalkisYeri, VarisYeri, SeferZamani, OtobusSirketiAdi, AracTuru, KoltukNo, Fiyat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Parametreleri set etme
                preparedStatement.setString(1, tcNo.getText());
                preparedStatement.setString(2, ad.getText());
                preparedStatement.setString(3, soyad.getText());

                // Doğum tarihini DateTimeFormatter kullanarak çözümleme
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate parsedBirthdate = LocalDate.parse(dogumTarihi.getText(), dateFormatter);
                preparedStatement.setDate(4, java.sql.Date.valueOf(parsedBirthdate));

                preparedStatement.setString(5, telefon.getText());
                preparedStatement.setString(6, cinsiyet.getValue());
                preparedStatement.setString(7, kalkisSehir.getValue());
                preparedStatement.setString(8, varisSehir.getValue());

                // Sefer zamanını DateTimeFormatter kullanarak çözümleme
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime parsedSeferZamani = LocalDateTime.parse(seferZamani.getValue(), dateTimeFormatter);
                preparedStatement.setTimestamp(9, Timestamp.valueOf(parsedSeferZamani));

                preparedStatement.setString(10, otobusSirketAdi.getValue());
                preparedStatement.setString(11, aracTur.getValue());
                preparedStatement.setString(12, koltukNo.getText());

                // Eğer 'Fiyat' sütunu int olarak tanımlanmışsa
                int fiyat = Integer.parseInt(biletUcreti.getText());
                preparedStatement.setInt(13, fiyat);

                // Veritabanına ekleme işlemi gerçekleştirme
                preparedStatement.executeUpdate();

                // Ödeme yap ekranını yükle ve göster
                Parent root = FXMLLoader.load(getClass().getResource("odemeYap-view.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Ödeme Yap Ekranı");
                stage.setScene(new Scene(root));
                stage.show();

                // Yeni bir pencereyi başarılı ekleme sonrasında açmak için geri kalan kodlar
                // (Bu kısımların tamamlanmamış olması ve eksik olması durumunda uygun bir şekilde düzenlenmelidir)
            } catch (IOException e) {
                // IOException oluşursa çalışma zamanı istisnası fırlat
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            // SQL isteği sırasında bir hata oluştuğunda hatayı yazdır
            e.printStackTrace(); // Gerçek bir uygulamada hatayı uygun bir şekilde ele almalısınız
        }
    }


    public void setSecilenKoltukNo(String koltukNo) {
        this.secilenKoltukNo = koltukNo;
        this.koltukNo.setText(koltukNo);

}



}