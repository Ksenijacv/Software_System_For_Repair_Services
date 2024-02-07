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
 * @author Ksenija
 */
public class SOVratiPrijave extends ApstraktnaSO {

    private ArrayList<PrijavaKvara> lista;

    public ArrayList<PrijavaKvara> getLista() {
        return lista;
    }

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        ArrayList<ApstraktniObjekat> prijave = DBBroker.getInstance().selectBezUslova(ado);
        lista = (ArrayList<PrijavaKvara>) (ArrayList<?>) prijave;

    }

}
