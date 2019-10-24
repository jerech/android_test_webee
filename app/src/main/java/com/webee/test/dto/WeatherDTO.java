package com.webee.test.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherDTO {


    @Expose
    @SerializedName("weather")
    private List<Weather> weathers;

    @Expose
    @SerializedName("main")
    private Main main;

    public List<Weather> getWeathers() {
        return weathers;
    }

    public Main getMain() {
        return main;
    }

    public class Weather {
        @Expose
        @SerializedName("main")
        private String main;

        @Expose
        @SerializedName("description")
        private String description;

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }
    }

    public class Main {
        @Expose
        @SerializedName("temp")
        private float temp;

        @Expose
        @SerializedName("pressure")
        private float pressure;

        @Expose
        @SerializedName("humidity")
        private float humidity;

        public float getTemp() {
            return temp;
        }

        public float getPressure() {
            return pressure;
        }

        public float getHumidity() {
            return humidity;
        }
    }

}
