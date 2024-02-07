/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.StavkaIzvestaja;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ksenija
 */
public class SOVratiStavkeIzvestaja extends ApstraktnaSO {

    private ArrayList<StavkaIzvestaja> lista;

    public ArrayList<StavkaIzvestaja> getLista() {
        return lista;
    }

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        ArrayList<ApstraktniObjekat> stavke = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaIzvestaja>) (ArrayList<?>) stavke;
    }

}
