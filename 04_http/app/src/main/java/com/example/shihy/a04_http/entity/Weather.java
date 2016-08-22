package com.example.shihy.a04_http.entity;

/**
 * Created by shihy on 16/8/18.
 */
public class Weather {
    private String pname;
    private String cname;
    private String detail;
    private Integer tem1;
    private Integer tem2;
    private String wind;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getTem1() {
        return tem1;
    }

    public void setTem1(Integer tem1) {
        this.tem1 = tem1;
    }

    public Integer getTem2() {
        return tem2;
    }

    public void setTem2(Integer tem2) {
        this.tem2 = tem2;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
