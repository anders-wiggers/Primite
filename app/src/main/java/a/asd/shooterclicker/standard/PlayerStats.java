package a.asd.shooterclicker.standard;

import java.util.ArrayList;
import java.util.List;

import a.asd.shooterclicker.framework.Observer;

public class PlayerStats {
    private int critChance;
    private double critModifier;

    private double percentFlatDamageBoost;
    private double percentAttackSpeedBoost;

    private double percentDamageToRifle;
    private double percentDamageToMini;
    private double percentDamageToSniper;

    private double percentHeat;
    private double percentFrost;
    private double percentInpact;
    private double percentPoison;

    private double expGain;
    private double buksGain;

    private double critMultiplyer;

    public void rebootStats() {
        critChance = 0;
        critModifier = 2.0;
        critMultiplyer = 1.0;

        percentFlatDamageBoost = 1.0;
        percentAttackSpeedBoost = 1.0;

        percentDamageToRifle = 1.0;
        percentDamageToMini = 1.0;
        percentDamageToSniper = 1.0;

        percentHeat = 0.0;
        percentFrost = 0.0;
        percentInpact = 0.0;
        percentPoison = 0.0;

        expGain = 1.0;
        buksGain = 1.0;
    }

    public int getCritChance() {
        return (int) (critChance * critMultiplyer);
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public double getCritModifier() {
        return critModifier;
    }

    public void setCritModifier(double critModifier) {
        this.critModifier = critModifier;
    }

    public double getPercentFlatDamageBoost() {
        return percentFlatDamageBoost;
    }

    public void setPercentFlatDamageBoost(double percentFlatDamageBoost) {
        this.percentFlatDamageBoost = percentFlatDamageBoost;
    }

    public double getPercentAttackSpeedBoost() {
        return percentAttackSpeedBoost;
    }

    public void setPercentAttackSpeedBoost(double percentAttackSpeedBoost) {
        this.percentAttackSpeedBoost = percentAttackSpeedBoost;
    }

    public double getPercentDamageToRifle() {
        return percentDamageToRifle;
    }

    public void setPercentDamageToRifle(double percentDamageToRifle) {
        this.percentDamageToRifle = percentDamageToRifle;
    }

    public double getPercentDamageToMini() {
        return percentDamageToMini;
    }

    public void setPercentDamageToMini(double percentDamageToMini) {
        this.percentDamageToMini = percentDamageToMini;
    }

    public double getPercentDamageToSniper() {
        return percentDamageToSniper;
    }

    public void setPercentDamageToSniper(double percentDamageToSniper) {
        this.percentDamageToSniper = percentDamageToSniper;
    }

    public double getPercentHeat() {
        return percentHeat;
    }

    public void setPercentHeat(double percentHeat) {
        this.percentHeat = percentHeat;
    }

    public double getPercentFrost() {
        return percentFrost;
    }

    public void setPercentFrost(double percentFrost) {
        this.percentFrost = percentFrost;
    }

    public double getPercentInpact() {
        return percentInpact;
    }

    public void setPercentInpact(double percentInpact) {
        this.percentInpact = percentInpact;
    }

    public double getPercentPoison() {
        return percentPoison;
    }

    public void setPercentPoison(double percentPoison) {
        this.percentPoison = percentPoison;
    }

    public double getExpGain() {
        return expGain;
    }

    public void setExpGain(double expGain) {
        this.expGain = expGain;
    }

    public double getBuksGain() {
        return buksGain;
    }

    public void setBuksGain(double buksGain) {
        this.buksGain = buksGain;
    }

    public void increaseCritChange(int amount){
        critChance += amount;
    }

    public void increaseCritModifier(double amount){
        critModifier += amount;
    }

    public double getCritMultiplyer() {
        return critMultiplyer;
    }

    public void setCritMultiplyer(double critMultiplyer) {
        this.critMultiplyer = critMultiplyer;
    }

}
