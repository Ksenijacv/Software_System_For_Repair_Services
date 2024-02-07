/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.RezervniDeo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ksenija
 */
public class SOSacuvajDeo extends ApstraktnaSO {

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
        ArrayList<ApstraktniObjekat> delovi = DBBroker.getInstance().selectBezUslova(ado);
        ArrayList<RezervniDeo> lista = (ArrayList<RezervniDeo>) (ArrayList<?>) delovi;

        RezervniDeo rd = (RezervniDeo) ado;

        for (RezervniDeo rezervniDeo : lista) {
            if (rezervniDeo.getNazivRezervnogDela().toLowerCase().equals(rd.getNazivRezervnogDela().toLowerCase())) {
                throw new Exception("Vec postoji deo sa ovim nazivom!");
            }
        }

        if (rd.getCenaRezervnogDela() <= 0) {
            throw new Exception("Cena ne moze biti manja ili jednaka 0!");
        }
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        DBBroker.getInstance().insert(ado);
    }

}
