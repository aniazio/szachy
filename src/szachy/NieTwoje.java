package szachy;

public class NieTwoje extends Exception {

    public String toString() {
        return "Figura, którą chciałeś przesunąć, jest figurą przeciwnika.";
    }
}
