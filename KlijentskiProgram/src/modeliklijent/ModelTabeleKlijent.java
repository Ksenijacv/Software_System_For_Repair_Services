/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliklijent;

import domenskeKlase.Klijent;
import formemain.PretragaKlijenata;
import formemain.PretragaPrijava;
import klijentkontroler.KlijentKontroler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ksenija
 */
public class ModelTabeleKlijent extends AbstractTableModel {

    private ArrayList<Klijent> lista;
    private String[] kolone = {"Ime prezime klijenta", "Mesto", "Kontakt"};
    private String parametar = "";

    public ModelTabeleKlijent() {
        try {
            lista = KlijentKontroler.getInstance().vratiKlijente();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Klijent k = lista.get(row); //izmeniti

        switch (column) {
            case 0:
                return k.getImePrezime();
            case 1:
                return k.getMesto().toString();
            case 2:
                return k.getKontakt();
            default:
                return null;
        }
    }

    public Klijent getSelectedKlijent(int row) {
        return lista.get(row);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = KlijentKontroler.getInstance().vratiKlijente();
            if (!parametar.equals("")) {
                ArrayList<Klijent> novaLista = new ArrayList<>();
                for (Klijent k : lista) {
                    if (k.getImePrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(k);
                    }
                }
                lista = novaLista;

                if (novaLista.size() > 0) {
                    PretragaKlijenata.setPoruka("Sistem je pronasao klijente po zadatoj vrednosti");
                } else {
                    PretragaKlijenata.setPoruka("Sistem nije pronasao klijente po zadatoj vrednosti");
                }
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
