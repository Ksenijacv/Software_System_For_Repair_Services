/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.IzvestajOServisu;
import domenskeKlase.StavkaIzvestaja;
import java.sql.SQLException;

/**
 *
 * @author Ksenija
 */
public class SOIzmeniIzvestaj extends ApstraktnaSO {

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
        IzvestajOServisu i = (IzvestajOServisu) ado;
        if (i.getStavke().size() == 0) {
            throw new Exception("Izvestaj mora imati barem jednu stavku!");
        }
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        IzvestajOServisu i = (IzvestajOServisu) ado;
        DBBroker.getInstance().update(ado);
        StavkaIzvestaja si = new StavkaIzvestaja();
        si.setIzvestaj(i);
        DBBroker.getInstance().delete(si);
        for (StavkaIzvestaja stavkaIzvestaja : i.getStavke()) {
            stavkaIzvestaja.setIzvestaj(i);
            DBBroker.getInstance().insert(stavkaIzvestaja);

        }

    }

}
