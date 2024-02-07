/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverkontroler;

import domenskeKlase.IzvestajOServisu;
import domenskeKlase.Klijent;
import domenskeKlase.Mesto;
import domenskeKlase.PrijavaKvara;
import domenskeKlase.RezervniDeo;
import domenskeKlase.Serviser;
import domenskeKlase.StavkaIzvestaja;
import java.util.ArrayList;
import so.ApstraktnaSO;
import so.SOIzmeniIzvestaj;
import so.SOIzmeniKlijenta;
import so.SOLogin;
import so.SOObrisiIzvestaj;
import so.SOObrisiMesto;
import so.SOSacuvajDeo;
import so.SOSacuvajIzvestaj;
import so.SOSacuvajKlijenta;
import so.SOSacuvajPrijavuKvara;
import so.SOVratiDelove;
import so.SOVratiIzvestaje;
import so.SOVratiKlijente;
import so.SOVratiMesta;
import so.SOVratiPrijave;
import so.SOVratiStavkeIzvestaja;

/**
 *
 * @author Ksenija
 */
public class ServerKontroler {

    private static ServerKontroler instance;

    public ServerKontroler() {
    }

    public static ServerKontroler getInstance() {
        if (instance == null) {
            instance = new ServerKontroler();
        }
        return instance;
    }

    public void izmeniIzvestaj(IzvestajOServisu izvestajOServisu) throws Exception {
        SOIzmeniIzvestaj so = new SOIzmeniIzvestaj();
        so.templateExecute(izvestajOServisu);
    }

    public void izmeniKlijenta(Klijent klijent) throws Exception {
        SOIzmeniKlijenta so = new SOIzmeniKlijenta();
        so.templateExecute(klijent);
    }

    public Serviser login(Serviser serviser) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(serviser);
        return so.getServiser();
    }

    public void sacuvajDeo(RezervniDeo rezervniDeo) throws Exception {
        SOSacuvajDeo so = new SOSacuvajDeo();
        so.templateExecute(rezervniDeo);
    }

    public void sacuvajKlijenta(Klijent klijent) throws Exception {
        SOSacuvajKlijenta so = new SOSacuvajKlijenta();
        so.templateExecute(klijent);
    }

    public void sacuvajPrijavuKvara(PrijavaKvara prijavaKvara) throws Exception {
        SOSacuvajPrijavuKvara so = new SOSacuvajPrijavuKvara();
        so.templateExecute(prijavaKvara);
    }

    public ArrayList<IzvestajOServisu> vratiIzvestaje() throws Exception {
        SOVratiIzvestaje so = new SOVratiIzvestaje();
        so.templateExecute(new IzvestajOServisu());
        return so.getLista();
    }

    public ArrayList<Klijent> vratiKlijente() throws Exception {
        SOVratiKlijente so = new SOVratiKlijente();
        so.templateExecute(new Klijent());
        return so.getLista();
    }

    public ArrayList<Mesto> vratiMesta() throws Exception {
        SOVratiMesta so = new SOVratiMesta();
        so.templateExecute(new Mesto());
        return so.getLista();
    }

    public ArrayList<PrijavaKvara> vratiPrijave() throws Exception {
        SOVratiPrijave so = new SOVratiPrijave();
        so.templateExecute(new PrijavaKvara());
        return so.getLista();
    }

    public ArrayList<StavkaIzvestaja> vratiStavke(IzvestajOServisu izvestajOServisu) throws Exception {
        SOVratiStavkeIzvestaja so = new SOVratiStavkeIzvestaja();
        StavkaIzvestaja si = new StavkaIzvestaja();
        si.setIzvestaj(izvestajOServisu);
        so.templateExecute(si);
        return so.getLista();
    }

    public void sacuvajIzvestaj(IzvestajOServisu izvestajOServisu) throws Exception {
        SOSacuvajIzvestaj so=new SOSacuvajIzvestaj();
        so.templateExecute(izvestajOServisu);
    }

    public ArrayList<RezervniDeo> vratiDelove() throws Exception {
        SOVratiDelove so=new SOVratiDelove();
        so.templateExecute(new RezervniDeo());
        return so.getLista();
    }
    
    public void obrisiMesto(Mesto mesto) throws Exception {
        SOObrisiMesto so = new SOObrisiMesto();
        so.templateExecute(mesto);
    }

    public void obrisiIzvestaj(IzvestajOServisu izvestajOServisu) throws Exception {
        SOObrisiIzvestaj so = new SOObrisiIzvestaj();
        so.templateExecute(izvestajOServisu);
    }
   

}
