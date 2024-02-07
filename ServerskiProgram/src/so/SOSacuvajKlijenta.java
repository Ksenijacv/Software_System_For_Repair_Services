/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import java.sql.SQLException;

/**
 *
 * @author Ksenija
 */
public class SOSacuvajKlijenta extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
