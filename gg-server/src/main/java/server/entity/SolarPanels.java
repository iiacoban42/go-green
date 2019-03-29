package server.entity;

import database.entity.ContinuousAction;
import database.manager.UserManager;
import database.manager.ContinuousActionManager;

import java.util.Date;
import java.util.List;

public class SolarPanels {

    /**
     * Calculates amount of CO2 saved by producing energy by solar panels. The following source has been used for calculating the saved CO2 per solar panel:
     * https://www.gaslicht.com/energiebesparing/stroom-opwekken-met-zonnepanelen
     * @param username
     * @return amount of CO2 saved in grams
     */
    public long savedCO2(String username) {
        ContinuousAction ca = ContinuousActionManager.getActiveCaByUser(username);
        int amountSP= ca.getNumSolarPanels();
        int savedCO2perSP = 630*1000/(6*365);
        long savedCO2 = 0;

        long now = System.currentTimeMillis();
        Date lastCashInDate = ca.getDateLastCashedIn();
        long lastCashInMillis = lastCashInDate.getTime();

        if (now - lastCashInMillis > (1000 * 60 * 60 * 24)) {
            savedCO2 = amountSP * savedCO2perSP;
            ContinuousActionManager.cashInCa(ca.getId());
        }

        return savedCO2;
    }

//    public int newAmountSP (String username, int numSolarPanels) {
//
//    }
}
