package szachy;

public class NiepoprawneDane extends Exception {

    public String toString() {
        return "Niepoprawne dane! Wpisz dane w formie: (litera startu)(cyfra startu)(spacja)(litera końca)(cyfra końca) \nnp. a4 a6";
    }
}
