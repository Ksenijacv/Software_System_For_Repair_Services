/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliklijent;

import domenskeKlase.IzvestajOServisu;
import formemain.PretragaIzvestaja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import klijentkontroler.KlijentKontroler;

/**
 *
 * @author Ksenija
 */
public class ModelTabeleIzvestaji extends AbstractTableModel {
    
    private ArrayList<IzvestajOServisu> lista;
    String[] kolone = {"Prijava", "Datum vreme izvestaja", "Ukupna cena", "Nacin placanja"};
    private String param = "";
    
    public ModelTabeleIzvestaji() {
        try {
            lista = KlijentKontroler.getInstance().vratiIzvestaje();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleIzvestaji.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setParam(String param) {
        this.param = param;
        refreshTable();
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
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IzvestajOServisu i = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        switch (columnIndex) {
            case 0:
                return i.getPrijava().getPrijavaID();
            case 1:
                return sdf.format(i.getDatumVremeIzvestaja());
            case 2:
                return i.getUkupnaCena();
            case 3:
                return i.getPlacanje().toString();
            default:
                return null;
        }
    }
    
    private void refreshTable() {
        
        if (!param.equals("")) {
            ArrayList<IzvestajOServisu> novaLista = new ArrayList<>();
            for (IzvestajOServisu izvestajOServisu : lista) {
                if (izvestajOServisu.getPrijava().getKlijent().toString().toLowerCase().contains(param.toLowerCase())) {
                    novaLista.add(izvestajOServisu);
                }
            }
            lista = novaLista;
            if (novaLista.size() > 0) {
                PretragaIzvestaja.setPoruka("Sistem je pronasao izvestaje po zadatoj vrednosti");
            } else {
                PretragaIzvestaja.setPoruka("Sistem nije pronasao izvestaje po zadatoj vrednosti");
            }
        }
        fireTableDataChanged();
        
    }
    
    public ArrayList<IzvestajOServisu> getLista() {
        return lista;
    }
    
    public IzvestajOServisu getSelecetedIzvestaj(int row) {
        return lista.get(row);
    }
    
}
