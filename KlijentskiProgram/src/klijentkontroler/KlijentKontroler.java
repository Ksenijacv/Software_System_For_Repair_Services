/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijentkontroler;


import domenskeKlase.IzvestajOServisu;
import domenskeKlase.Klijent;
import domenskeKlase.Mesto;
import domenskeKlase.PrijavaKvara;
import domenskeKlase.RezervniDeo;
import domenskeKlase.Serviser;
import domenskeKlase.StavkaIzvestaja;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer_operacije.StatusOdgovora;
import transfer_operacije.Operacije_radna_memorija;

/**
 *
 * @author Ksenija
 */
public class KlijentKontroler {

    private static KlijentKontroler instance;

    public KlijentKontroler() {
    }

    public static KlijentKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentKontroler();
        }
        return instance;
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        KlijentskiZahtev req = new KlijentskiZahtev(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        ServerskiOdgovor res = (ServerskiOdgovor) in.readObject();
        if (res.getResponseStatus().equals(StatusOdgovora.Error)) {
            throw res.getError();
        } else {
            return res.getData();
        }
    }

    public Serviser login(Serviser s) throws Exception {
        return (Serviser) sendRequest(Operacije_radna_memorija.LOGIN, s);
    }

    public ArrayList<Klijent> vratiKlijente() throws Exception {
        return (ArrayList<Klijent>) sendRequest(Operacije_radna_memorija.VRATI_KLIJENTE, null);
    }

    public void sacuvajPrijavu(PrijavaKvara pk) throws Exception {
        sendRequest(Operacije_radna_memorija.SACUVAJ_PRIJAVU_KVARA, pk);
    }

    public void sacuvajDeo(RezervniDeo rd) throws Exception {
        sendRequest(Operacije_radna_memorija.SACUVAJ_DEO, rd);
    }

    public ArrayList<PrijavaKvara> vratiPrijave() throws Exception {
        return (ArrayList<PrijavaKvara>) sendRequest(Operacije_radna_memorija.VRATI_PRIJAVAE, null);
    }

    public ArrayList<RezervniDeo> vratiDelove() throws Exception {
        return (ArrayList<RezervniDeo>) sendRequest(Operacije_radna_memorija.VRATI_DELOVE, null);
    }

    public void sacuvajIzvestaj(IzvestajOServisu i) throws Exception {
        sendRequest(Operacije_radna_memorija.SACUVAJ_IZVESTAJ, i);
    }

    public ArrayList<IzvestajOServisu> vratiIzvestaje() throws Exception {
        return (ArrayList<IzvestajOServisu>) sendRequest(Operacije_radna_memorija.VRATI_IZVESTAJE, null);
    }

    public ArrayList<StavkaIzvestaja> vratiStavke(IzvestajOServisu i) throws Exception {
        return (ArrayList<StavkaIzvestaja>) sendRequest(Operacije_radna_memorija.VRATI_STAVKE_IZVESTAJA, i);
    }

    public void izmeniIzvestaj(IzvestajOServisu i) throws Exception {
        sendRequest(Operacije_radna_memorija.IZMENI_IZVESTAJ, i);
    }

    public ArrayList<Mesto> vratiMesta() throws Exception {
        return (ArrayList<Mesto>) sendRequest(Operacije_radna_memorija.VRATI_MESTA, null);
    }

    public void sacuvajKlijenta(Klijent klijent) throws Exception {
        sendRequest(Operacije_radna_memorija.SACUVAJ_KLIJENTA, klijent);
    }

    public void izmeniKlijenta(Klijent k) throws Exception {
        sendRequest(Operacije_radna_memorija.IZMENI_KLIJENTA, k);
    }

   

    public void obrisiIzvestaj(IzvestajOServisu i) throws Exception {
        sendRequest(Operacije_radna_memorija.OBRISI_IZVESTAJ, i);
    }

  

}
