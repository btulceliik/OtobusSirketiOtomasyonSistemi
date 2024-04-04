module com.example.otobussirketiotomasyonu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.otobussirketiotomasyonu to javafx.fxml;
    exports com.example.otobussirketiotomasyonu;
}