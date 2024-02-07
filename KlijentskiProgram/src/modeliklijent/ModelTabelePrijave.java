/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliklijent;

import domenskeKlase.PrijavaKvara;
import formemain.PretragaPrijava;
import java.text.SimpleDateFormat;
import klijentkontroler.KlijentKontroler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ksenija
 */
public class ModelTabelePrijave extends AbstractTableModel {

    private ArrayList<PrijavaKvara> lista;
    private ArrayList<PrijavaKvara> pomocna;
    private String[] kolone = {"Datum vreme prijave", "Klijent", "Serviser"};
    private String parametar = "";

    public ModelTabelePrijave() {
        try {
            lista = new ArrayList<>();
            pomocna = KlijentKontroler.getInstance().vratiPrijave();
            for (PrijavaKvara prijavaKvara : pomocna) {
                if (prijavaKvara.getStatus() == 1) {
                    lista.add(prijavaKvara);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ModelTabelePrijave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModelTabelePrijave(ArrayList<PrijavaKvara> prijave) {
        try {
            lista = KlijentKontroler.getInstance().vratiPrijave();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabelePrijave.class.getName()).log(Level.SEVERE, null, ex);
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
        PrijavaKvara p = lista.get(row); //izmeniti
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        switch (column) {
            case 0:
                return sdf.format(p.getDatumVremePrijave());
            case 1:
                return p.getKlijent().toString();
            case 2:
                return p.getServiser().toString();
            default:
                return null;
        }
    }

    public PrijavaKvara getSelectedPrijava(int row) {
        return lista.get(row);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            if (!parametar.equals("")) {
                ArrayList<PrijavaKvara> novaLista = new ArrayList<>();
                for (PrijavaKvara prijava : lista) {
                    if (prijava.getKlijent().getImePrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(prijava);
                    }
                }
                if (lista.size() > 0) {
                    lista = novaLista;
                }
                if (pomocna.size() > 0) {
                    pomocna = novaLista;
                }

                if (novaLista.size() > 0) {
                    PretragaPrijava.setPoruka("Sistem je pronasao prijave po zadatoj vrednosti");
                } else {
                    PretragaPrijava.setPoruka("Sistem nije pronasao prijave po zadatoj vrednosti");
                }

            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
