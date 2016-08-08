package com.norbertovasconcelos.drawapp.Models;

/**
 * Created by norbertovasconcelos on 08/08/16.
 */

public class DrawObject {
    private float x;
    private float y;
    private float size;
    private float pressure;

    public DrawObject(float x, float y, float size, float pressure) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.pressure = pressure;
    }
}
