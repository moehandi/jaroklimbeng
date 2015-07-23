package main;

import java.io.IOException;

public class Cuaca {

    double tahun = 0.0;
    double bulan = 0.0;
    double hari = 0.0;
    double temperatur = 0.0;
    double kelembaban = 0.0;
    double rainfall = 0.0;
    double statRain = 0.0;
    String textNormalizedDataset = "";
    String textNormalizedTestset = "";
    String textDataPrint = "";
    double maxX;
    double minX;

    public void getNormalizeDataset(String sTemperatur, String sKelembaban, String sRainfall, String sStatRain) throws IOException {
        temperatur = Double.parseDouble(sTemperatur);
        kelembaban = Double.parseDouble(sKelembaban);
        rainfall = (Double.parseDouble(sRainfall));
        statRain = Double.parseDouble(sStatRain);

        maxX = findMax(temperatur, kelembaban, rainfall, statRain);
        minX = findMin(temperatur, kelembaban, rainfall, statRain);
        System.out.println("NORM:" + temperatur + "," + kelembaban + "," + rainfall + "," + statRain + "(Max: " + maxX + ",Min: " + minX + (")"));
        textNormalizedDataset += "" + normalize(temperatur) + ", " + normalize(kelembaban) + ", " + normalize(rainfall) + ", " + normalize(statRain) + "\n";
        System.out.println("" + normalize(temperatur) + ", " + normalize(kelembaban) + ", " + normalize(rainfall) + ", " + normalize(statRain) + "\n");

    }

    public void getNormalizeTestSet(String sTemperatur, String sKelembaban, String sRainfall, String sStatRain) throws IOException {
        temperatur = Double.parseDouble(sTemperatur);
        kelembaban = Double.parseDouble(sKelembaban);
        rainfall = (Double.parseDouble(sRainfall));

        textNormalizedTestset += "" + normalize(temperatur) + ", " + normalize(kelembaban) + ", " + normalize(rainfall)+ ", " + normalize(statRain) + "\n";
    }

    public double normalize(double x) {
        return ((x / 100) * 0.8 + 0.1);
    }

    public double findMax(double... vals) {
        double max = Double.MIN_VALUE;

        for (double d : vals) {
            if (d > max) {
                max = d;
            }
        }
        return max;
    }

    public double findMin(double... vals) {
        double min = Double.MAX_VALUE;

        for (double d : vals) {
            if (d < min) {
                min = d;
            }
        }
        return min;
    }
    
}
