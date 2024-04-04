package com.example.otobussirketiotomasyonu;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class OdemeYapController {

    @FXML
    private TextField kartNo;
    @FXML
    private TextField cvc;
    @FXML
    private TextField sonKullanma;
    @FXML
    private Button onayla;

    private int maxKarakter = 16;
    private int maxCvcKarakter = 3;

    private  int maxSonKullanma = 5;

    @FXML
    private void initialize() {
        // Listener ekleyerek girilen metni kontrol et (hem kartNo hem de cvc için)
        addKarakterKontrolListener(kartNo, maxKarakter);
        addKarakterKontrolListener(cvc, maxCvcKarakter);
        addKarakterKontrolListener(sonKullanma, maxSonKullanma);
    }
    private void addKarakterKontrolListener(TextField textField, int maxKarakter) {
        /*metodun içindeki dinleyici, herhangi bir metin değişikliği olduğunda çalışır.
        Eğer yeni metin uzunluğu, belirtilen maksimum karakter sayısını aşıyorsa, TextField'ın metni eski değeriyle değiştirilir.
        Bu sayede kullanıcı, belirli bir metin uzunluğunu aşamaz ve girişi sınırlanmış olur.*/

        //textProperty'ye bir dinleyici eklenir. Bu dinleyici, herhangi bir metin değişikliği olduğunda tetiklenir.
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            //Dinleyici içindeki kod, eğer yeni metin uzunluğu maksimum karakter sayısını aşıyorsa,
            // TextField'ın metnini eski değeriyle değiştirir.
            if (newValue.length() > maxKarakter) {
                textField.setText(oldValue);
            }});}
    @FXML
    public void onaylaButton(ActionEvent actionEvent) {
        String kartNoText = kartNo.getText();
        String cvcText = cvc.getText();
        String sonKullanmaText = sonKullanma.getText();

        // Boş alan kontrolü
        if (kartNoText.isEmpty() || cvcText.isEmpty() || sonKullanmaText.isEmpty()) {
            // Eksik bilgi uyarısı
            showAlert("Lütfen tüm alanları doldurun.");

        } else {
            // Yükleme paneli oluştur
            Stage loadingStage = createLoadingStage();

            // Arka planda işlemi gerçekleştiren bir Task oluştur
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    // Burada gerçekleştirilecek işlemleri ekleyin
                    Thread.sleep(4000);
                    return null;
                }
            };

            // Task tamamlandığında yükleme panelini kapat
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    loadingStage.close();

                    // İşlemin tamamlandığında bilet bilgi ekranını göster
                    showBiletBilgiStage();
                }
            });

            // Task'i başlat
            new Thread(task).start();

            // Yükleme panelini göster
            loadingStage.show();
        }
    }


    private void showBiletBilgiStage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("biletBilgi-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bilet Bilgi Ekranı");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Yükleme ekranı oluşturan metod
    private Stage createLoadingStage() {
        // Yükleme ekranını oluşturmak için StackPane kullanılır
        StackPane root = new StackPane();

        // Başlık için bir Label ekleyin
        Label titleLabel = new Label("İşlem Gerçekleştiriliyor Lütfen Bekleyiniz..");
        titleLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        root.getChildren().add(titleLabel);

        // ProgressIndicator (ilerleme göstergesi) ekleyin
        ProgressIndicator progressIndicator = new ProgressIndicator();
        root.getChildren().add(progressIndicator);

        // Yükleme ekranı için bir Stage (pencere) oluşturun
        Stage loadingStage = new Stage();

        // Yükleme ekranını diğer pencerelerin önünde göstermek için modality'yi APPLICATION_MODAL olarak ayarlayın
        loadingStage.initModality(Modality.APPLICATION_MODAL);

        // Yükleme ekranının boyutunu ve içeriğini belirleyin
        loadingStage.setScene(new Scene(root, 350, 150));

        // Oluşturulan yükleme ekranını geri döndürün
        return loadingStage;
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uyarı");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
}

}