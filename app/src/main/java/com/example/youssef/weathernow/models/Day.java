package com.example.youssef.weathernow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("maxtemp_c")
    @Expose
    private Double maxtempC;
    @SerializedName("maxtemp_f")
    @Expose
    private Double maxtempF;
    @SerializedName("mintemp_c")
    @Expose
    private Double mintempC;
    @SerializedName("mintemp_f")
    @Expose
    private Double mintempF;
    @SerializedName("avgtemp_c")
    @Expose
    private Double avgtempC;
    @SerializedName("avgtemp_f")
    @Expose
    private Double avgtempF;
    @SerializedName("maxwind_mph")
    @Expose
    private Double maxwindMph;
    @SerializedName("maxwind_kph")
    @Expose
    private Double maxwindKph;
    @SerializedName("totalprecip_mm")
    @Expose
    private Integer totalprecipMm;
    @SerializedName("totalprecip_in")
    @Expose
    private Integer totalprecipIn;
    @SerializedName("avgvis_km")
    @Expose
    private Integer avgvisKm;
    @SerializedName("avgvis_miles")
    @Expose
    private Integer avgvisMiles;
    @SerializedName("avghumidity")
    @Expose
    private Integer avghumidity;
    @SerializedName("condition")
    @Expose
    private Condition_ condition;
    @SerializedName("uv")
    @Expose
    private Double uv;

    public Double getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(Double maxtempC) {
        this.maxtempC = maxtempC;
    }

    public Double getMaxtempF() {
        return maxtempF;
    }

    public void setMaxtempF(Double maxtempF) {
        this.maxtempF = maxtempF;
    }

    public Double getMintempC() {
        return mintempC;
    }

    public void setMintempC(Double mintempC) {
        this.mintempC = mintempC;
    }

    public Double getMintempF() {
        return mintempF;
    }

    public void setMintempF(Double mintempF) {
        this.mintempF = mintempF;
    }

    public Double getAvgtempC() {
        return avgtempC;
    }

    public void setAvgtempC(Double avgtempC) {
        this.avgtempC = avgtempC;
    }

    public Double getAvgtempF() {
        return avgtempF;
    }

    public void setAvgtempF(Double avgtempF) {
        this.avgtempF = avgtempF;
    }

    public Double getMaxwindMph() {
        return maxwindMph;
    }

    public void setMaxwindMph(Double maxwindMph) {
        this.maxwindMph = maxwindMph;
    }

    public Double getMaxwindKph() {
        return maxwindKph;
    }

    public void setMaxwindKph(Double maxwindKph) {
        this.maxwindKph = maxwindKph;
    }

    public Integer getTotalprecipMm() {
        return totalprecipMm;
    }

    public void setTotalprecipMm(Integer totalprecipMm) {
        this.totalprecipMm = totalprecipMm;
    }

    public Integer getTotalprecipIn() {
        return totalprecipIn;
    }

    public void setTotalprecipIn(Integer totalprecipIn) {
        this.totalprecipIn = totalprecipIn;
    }

    public Integer getAvgvisKm() {
        return avgvisKm;
    }

    public void setAvgvisKm(Integer avgvisKm) {
        this.avgvisKm = avgvisKm;
    }

    public Integer getAvgvisMiles() {
        return avgvisMiles;
    }

    public void setAvgvisMiles(Integer avgvisMiles) {
        this.avgvisMiles = avgvisMiles;
    }

    public Integer getAvghumidity() {
        return avghumidity;
    }

    public void setAvghumidity(Integer avghumidity) {
        this.avghumidity = avghumidity;
    }

    public Condition_ getCondition() {
        return condition;
    }

    public void setCondition(Condition_ condition) {
        this.condition = condition;
    }

    public Double getUv() {
        return uv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
    }

}
