/*
1. Sukurti formą, kurioje būtų matoma lentelė su DB esančiu pasirinktų objektų sąrašu (browse).
Tai gali būti informacija apie, pvz., studentus. Lentelėje taip pat turi būti pateikiama svarbi
susijusi informacija iš kitų reliacinių lentelių, pvz., studento grupė, studijų programos, fakulteto
pavadinimas. Sąrašas turi būti valdomas, pasirenkant reikalingus parametrus (pvz., studijų
programą, dokumento datų intervalą ar pan.) įrašų filtravimui. Filtravimo elementai ir jų kiekis
parenkami pagal poreikį, tačiau pageidautina įvairių elementų demonstracija.
Formoje turėtų būti numatytos priemonės (pvz., mygtukas), kuriuo būtų iškviečiama forma
aktyvaus įrašo duomenų keitimui. Taip pat turėtų būti numatytos objekto duomenų ištrynimo bei
naujo objekto duomenų įterpimo operacijos.
2. Forma, kurioje būtų pateikta išsami informacija apie vieną DB esantį įrašą (kartu su susijusiais
duomenimis). Forma turėtų leisti keisti numatytus duomenis ar įrašyti duomenis apie naują
objektą. Formoje turi būti panaudoti tinkami duomenų-informacijos virsmo metodai bei patogūs
informacijos pateikimo vartotojui modeliai. Forma turi užtikrinti maksimalią duomenų kontrolę
duomenų įvedimo metu ir suprantamus garsinius ir vaizdo pranešimus apie aptinkamas klaidas.
Duomenų atnaujinimo veiksmai turi atnaujinti duomenis duomenų bazėje!

* */
package Management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Management/MainWindow.fxml")));
        stage.setTitle("Mokykla");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {launch();}
}

