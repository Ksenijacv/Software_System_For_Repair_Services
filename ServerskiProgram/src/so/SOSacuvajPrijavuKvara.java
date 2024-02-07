/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.PrijavaKvara;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luka
 */
public class SOSacuvajPrijavuKvara extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
       
        
        
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
