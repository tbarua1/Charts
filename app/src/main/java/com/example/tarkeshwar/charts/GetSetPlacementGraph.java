package com.example.tarkeshwar.charts;

import java.util.Objects;

/**
 * Created by tarkeshwar on 18/1/17.
 */

public class GetSetPlacementGraph {
    private int TOPUP,ITES;

    public int getTOPUP() {
        return TOPUP;
    }

    public void setTOPUP(int TOPUP) {
        this.TOPUP = TOPUP;
    }

    public int getITES() {
        return ITES;
    }

    public void setITES(int ITES) {
        this.ITES = ITES;
    }

    @Override
    public String toString() {
        return "GetSetPlacementGraph{" +
                "TOPUP=" + TOPUP +
                ", ITES=" + ITES +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetSetPlacementGraph that = (GetSetPlacementGraph) o;

        if (TOPUP != that.TOPUP) return false;
        return ITES == that.ITES;

    }

    @Override
    public int hashCode() {
        int result = TOPUP;
        result = 31 * result + ITES;
        return result;
    }
}
