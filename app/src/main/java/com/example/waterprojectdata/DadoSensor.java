package com.example.waterprojectdata;

import java.util.Date;

public class DadoSensor {

    int ph_value;
    int temp;
    Date time_created;

    public DadoSensor(int ph_value, int temp, Date time_created) {
        this.ph_value = ph_value;
        this.temp = temp;
        this.time_created = time_created;
    }

    public int getPh_value() {
        return ph_value;
    }

    public int getTemp() {
        return temp;
    }

    public Date getTime_created() {
        return time_created;
    }

    public void setPh_value(int ph_value) {
        this.ph_value = ph_value;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setTime_created(Date time_created) {
        this.time_created = time_created;
    }
}
