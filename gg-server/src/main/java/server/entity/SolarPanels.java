package server.entity;

import database.entity.ContinuousAction;
import database.manager.UserManager;
import database.manager.ContinuousActionManager;

import java.util.Date;
import java.util.List;

import static database.manager.ContinuousActionManager.endCa;

public class SolarPanels {

    /**
     * Calculates amount of CO2 saved by producing energy by solar panels. The following source has been used for calculating the saved CO2 per solar panel:
     * https://www.gaslicht.com/energiebesparing/stroom-opwekken-met-zonnepanelen
     * @param username
     * @return amount of CO2 saved in grams
     */
    public Integer savedCO2(String username) {
        if (ContinuousActionManager.getActiveCaByUser(username) != null) {
            ContinuousAction ca = ContinuousActionManager.getActiveCaByUser(username);
            int amountSP = ca.getNumSolarPanels();
            int savedCO2perSP = 630 * 1000 / (6 * 365);
            int savedCO2 = 0;

            Date lastCashInDate = ca.getDateLastCashedIn();
            long lastCashInMillis = lastCashInDate.getTime();

            if (twentyFourHoursPassed(lastCashInMillis)) {
                savedCO2 = amountSP * savedCO2perSP;
                ContinuousActionManager.cashInCa(ca.getId());
            }

            return savedCO2;
        }
            return null;
    }

    public void newAmountSP (String username, int numSolarPanels) {
        int totalScore = 0;
        if (ContinuousActionManager.getActiveCaByUser(username) != null) {
            ContinuousActionManager.getActiveCaByUser(username).getTotalScore();
            endCa(ContinuousActionManager.getActiveCaByUser(username).getId());
        }

        ContinuousAction ca = new ContinuousAction(username, "solar panels",0, numSolarPanels);
        int scorePerDay = savedCO2(username);
        ca.setScorePerDay(scorePerDay);
        ca.setTotalScore(totalScore);
    }

    public boolean twentyFourHoursPassed(long lastCashIn) {
        long now = System.currentTimeMillis();
        if (now - lastCashIn > (1000 * 60 * 60 * 24)) {
            return true;
        }
        return false;
    }
}
