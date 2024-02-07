/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.Klijent;
import domenskeKlase.Mesto;
import domenskeKlase.PrijavaKvara;

/**
 *
 * @author Ksenija
 */
public class SOObrisiMesto extends ApstraktnaSO {

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
        
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws Exception {
        Mesto m = (Mesto) ado;
        Klijent k = new Klijent();
        k.setMesto(m);
        DBBroker.getInstance().delete(k);
        DBBroker.getInstance().delete(ado);
        
    }
    
}
