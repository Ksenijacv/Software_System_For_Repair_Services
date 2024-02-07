/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import broker.DBBroker;
import domenskeKlase.ApstraktniObjekat;
import domenskeKlase.Mesto;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.shape.Mesh;

/**
 *
 * @author Ksenija
 */
public class SOVratiMesta extends ApstraktnaSO {

    private ArrayList<Mesto> lista;

    public ArrayList<Mesto> getLista() {
        return lista;
    }

    @Override
    protected void validate(ApstraktniObjekat ado) throws Exception {
    }

    @Override
    protected void execute(ApstraktniObjekat ado) throws SQLException, Exception {
        ArrayList<ApstraktniObjekat> mesta=DBBroker.getInstance().selectBezUslova(ado);
        lista=(ArrayList<Mesto>)(ArrayList<?>) mesta;
    }

}
