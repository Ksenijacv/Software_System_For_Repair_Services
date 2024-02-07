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
public class SOVratiDelove extends ApstraktnaSO{
    private ArrayList<RezervniDeo> lista;
    
    
    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        ArrayList<ApstraktniObjekat> delovi=DBBroker.getInstance().selectBezUslova(ado);
        lista=(ArrayList<RezervniDeo>)(ArrayList<?>)delovi;
    }

    public ArrayList<RezervniDeo> getLista() {
        return lista;
    }
    
    
    
}
