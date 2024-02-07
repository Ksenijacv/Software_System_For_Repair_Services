/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.Serviser;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ksenija
 */
public class SOLogin extends ApstraktnaSO {

    private Serviser serviser;

    public Serviser getServiser() {
        return serviser;
    }

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        ArrayList<ApstraktniObjekat> serviser = DBBroker.getInstance().selectBezUslova(ado);
        
        ArrayList<Serviser> lista = (ArrayList<Serviser>) (ArrayList<?>) serviser;
        
        Serviser s = (Serviser) ado;
        for (Serviser serviser1 : lista) {
            if (s.getKorisnickoIme().equals(serviser1.getKorisnickoIme())
                    && s.getLozinka().equals(serviser1.getLozinka())) {
                this.serviser = serviser1;
            }
        }
    }

}
