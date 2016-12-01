package com.springapp.model.utile;

/**
 * Created by michaelgoode on 08/11/2016.
 */
public class Machine {

    String name;
    int minSpeed;
    int maxSpeed;
    int actualMinSpeed;
    int actualMaxSpeed;
    int minPressure;
    int maxPressure;
    boolean vacuum;
    boolean blower;
    boolean dryNatGas;
    boolean otherGas;
    int maxTemp;
    enum LubricationType {DRY, OIL};
    LubricationType lubrication;
    int bore;
    int currentOutlePortAngle;
    int currentInletPortAngle;
    int numberOfBlades;
    float bladeThickness;
    float cylinderDiameter;
    float rotorDiameter;
    float cylinderLength;
    int originalDisplacement;
    int originalDisplacementSpeed;
    int lubricationDome;
    int lubrication8020;
    int lubricationTK;
    int lubricationOriginalSpeed;
    public enum CoolingType {AIR,WATER};
    CoolingType cooling;
    int lubricationPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getActualMinSpeed() {
        return actualMinSpeed;
    }

    public void setActualMinSpeed(int actualMinSpeed) {
        this.actualMinSpeed = actualMinSpeed;
    }

    public int getActualMaxSpeed() {
        return actualMaxSpeed;
    }

    public void setActualMaxSpeed(int actualMaxSpeed) {
        this.actualMaxSpeed = actualMaxSpeed;
    }

    public int getMinPressure() {
        return minPressure;
    }

    public void setMinPressure(int minPressure) {
        this.minPressure = minPressure;
    }

    public int getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(int maxPressure) {
        this.maxPressure = maxPressure;
    }

    public boolean isVacuum() {
        return vacuum;
    }

    public void setVacuum(boolean vacuum) {
        this.vacuum = vacuum;
    }

    public boolean isBlower() {
        return blower;
    }

    public void setBlower(boolean blower) {
        this.blower = blower;
    }

    public boolean isDryNatGas() {
        return dryNatGas;
    }

    public void setDryNatGas(boolean dryNatGas) {
        this.dryNatGas = dryNatGas;
    }

    public boolean isOtherGas() {
        return otherGas;
    }

    public void setOtherGas(boolean otherGas) {
        this.otherGas = otherGas;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public LubricationType getLubrication() {
        return lubrication;
    }

    public void setLubrication(LubricationType lubrication) {
        this.lubrication = lubrication;
    }

    public int getBore() {
        return bore;
    }

    public void setBore(int bore) {
        this.bore = bore;
    }

    public int getCurrentOutlePortAngle() {
        return currentOutlePortAngle;
    }

    public void setCurrentOutlePortAngle(int currentOutlePortAngle) {
        this.currentOutlePortAngle = currentOutlePortAngle;
    }

    public int getCurrentInletPortAngle() {
        return currentInletPortAngle;
    }

    public void setCurrentInletPortAngle(int currentInletPortAngle) {
        this.currentInletPortAngle = currentInletPortAngle;
    }

    public int getNumberOfBlades() {
        return numberOfBlades;
    }

    public void setNumberOfBlades(int numberOfBlades) {
        this.numberOfBlades = numberOfBlades;
    }

    public float getBladeThickness() {
        return bladeThickness;
    }

    public void setBladeThickness(float bladeThickness) {
        this.bladeThickness = bladeThickness;
    }

    public float getCylinderDiameter() {
        return cylinderDiameter;
    }

    public void setCylinderDiameter(float cylinderDiameter) {
        this.cylinderDiameter = cylinderDiameter;
    }

    public float getRotorDiameter() {
        return rotorDiameter;
    }

    public void setRotorDiameter(float rotorDiameter) {
        this.rotorDiameter = rotorDiameter;
    }

    public float getCylinderLength() {
        return cylinderLength;
    }

    public void setCylinderLength(float cylinderLength) {
        this.cylinderLength = cylinderLength;
    }

    public int getOriginalDisplacement() {
        return originalDisplacement;
    }

    public void setOriginalDisplacement(int originalDisplacement) {
        this.originalDisplacement = originalDisplacement;
    }

    public int getOriginalDisplacementSpeed() {
        return originalDisplacementSpeed;
    }

    public void setOriginalDisplacementSpeed(int originalDisplacementSpeed) {
        this.originalDisplacementSpeed = originalDisplacementSpeed;
    }

    public int getLubricationDome() {
        return lubricationDome;
    }

    public void setLubricationDome(int lubricationDome) {
        this.lubricationDome = lubricationDome;
    }

    public int getLubrication8020() {
        return lubrication8020;
    }

    public void setLubrication8020(int lubrication8020) {
        this.lubrication8020 = lubrication8020;
    }

    public int getLubricationTK() {
        return lubricationTK;
    }

    public void setLubricationTK(int lubricationTK) {
        this.lubricationTK = lubricationTK;
    }

    public int getLubricationOriginalSpeed() {
        return lubricationOriginalSpeed;
    }

    public void setLubricationOriginalSpeed(int lubricationOriginalSpeed) {
        this.lubricationOriginalSpeed = lubricationOriginalSpeed;
    }

    public CoolingType getCooling() {
        return cooling;
    }

    public void setCooling(CoolingType cooling) {
        this.cooling = cooling;
    }

    public int getLubricationPoints() {
        return lubricationPoints;
    }

    public void setLubricationPoints(int lubricationPoints) {
        this.lubricationPoints = lubricationPoints;
    }
}
