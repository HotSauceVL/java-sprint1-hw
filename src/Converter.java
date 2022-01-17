public class Converter {

    double convertToDistanseKm(int steps){

        return (double) steps * 75 / 100000;
    }

    double convertToKKallories(int steps){

        return (double) steps * 50 / 1000;
    }
}
