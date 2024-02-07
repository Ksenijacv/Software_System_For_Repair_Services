/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.IzvestajOServisu;
import domenskeKlase.PrijavaKvara;
import domenskeKlase.StavkaIzvestaja;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ksenija
 */
public class SOSacuvajIzvestaj extends ApstraktnaSO {

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
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int izvestajID = rs.getInt(1);
        i.setIzvestajID(izvestajID);

        for (StavkaIzvestaja stavkaIzvestaja : i.getStavke()) {
            stavkaIzvestaja.setIzvestaj(i);
            DBBroker.getInstance().insert(stavkaIzvestaja);
        }

        PrijavaKvara pk = i.getPrijava();
        pk.setStatus(2);
        DBBroker.getInstance().update(pk);

    }

}
