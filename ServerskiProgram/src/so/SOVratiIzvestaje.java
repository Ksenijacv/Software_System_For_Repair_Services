/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.IzvestajOServisu;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ksenija
 */
public class SOVratiIzvestaje extends ApstraktnaSO{
    
    private ArrayList<IzvestajOServisu> lista;

    public ArrayList<IzvestajOServisu> getLista() {
        return lista;
    }
    
    
    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        ArrayList<ApstraktniObjekat> prijave=DBBroker.getInstance().selectBezUslova(ado);
        lista=(ArrayList<IzvestajOServisu>)(ArrayList<?>) prijave;
        
    }
    
}
