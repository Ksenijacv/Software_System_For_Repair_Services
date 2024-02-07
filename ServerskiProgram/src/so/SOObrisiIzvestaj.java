/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.IzvestajOServisu;
import domenskeKlase.StavkaIzvestaja;

/**
 *
 * @author Ksenija
 */
public class SOObrisiIzvestaj extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
        
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws Exception {
        IzvestajOServisu i = (IzvestajOServisu) ado;
        StavkaIzvestaja si = new StavkaIzvestaja();
        si.setIzvestaj(i);
        DBBroker.getInstance().delete(si);
        DBBroker.getInstance().delete(ado);
    }
    
}
