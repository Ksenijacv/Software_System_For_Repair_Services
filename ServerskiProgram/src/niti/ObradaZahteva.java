/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domenskeKlase.IzvestajOServisu;
import domenskeKlase.Klijent;
import domenskeKlase.Mesto;
import domenskeKlase.PrijavaKvara;
import domenskeKlase.RezervniDeo;
import domenskeKlase.Serviser;
import domenskeKlase.StavkaIzvestaja;
import serverkontroler.ServerKontroler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer_operacije.StatusOdgovora;
import transfer_operacije.Operacije_radna_memorija;

/**
 *
 * @author Ksenija
 */
public class ObradaZahteva extends Thread {
    
    private Socket socket;
    
    ObradaZahteva(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev req = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private ServerskiOdgovor handleRequest(KlijentskiZahtev req) {
        ServerskiOdgovor res = new ServerskiOdgovor(null, null, StatusOdgovora.Success);
        
        try {
            switch (req.getOperation()) {
                case Operacije_radna_memorija.IZMENI_IZVESTAJ:
                    ServerKontroler.getInstance().izmeniIzvestaj((IzvestajOServisu) req.getData());
                    break;
                case Operacije_radna_memorija.IZMENI_KLIJENTA:
                    ServerKontroler.getInstance().izmeniKlijenta((Klijent) req.getData());
                    break;
                case Operacije_radna_memorija.LOGIN:
                    Serviser s = ServerKontroler.getInstance().login((Serviser) req.getData());
                    res.setData(s);
                    if (res.getData() == null) {
                        throw new Exception("Sistem nije pronasao servisera!");
                    } else {
                        break;
                    }
                case Operacije_radna_memorija.SACUVAJ_DEO:
                    ServerKontroler.getInstance().sacuvajDeo((RezervniDeo) req.getData());
                    break;
                case Operacije_radna_memorija.SACUVAJ_KLIJENTA:
                    ServerKontroler.getInstance().sacuvajKlijenta((Klijent) req.getData());
                    break;
                case Operacije_radna_memorija.SACUVAJ_PRIJAVU_KVARA:
                    ServerKontroler.getInstance().sacuvajPrijavuKvara((PrijavaKvara) req.getData());
                    break;
                case Operacije_radna_memorija.VRATI_IZVESTAJE:
                    ArrayList<IzvestajOServisu> izvestaji = ServerKontroler.getInstance().vratiIzvestaje();
                    res.setData(izvestaji);
                    break;
                case Operacije_radna_memorija.VRATI_KLIJENTE:
                    ArrayList<Klijent> klijenti = ServerKontroler.getInstance().vratiKlijente();
                    res.setData(klijenti);
                    break;
                case Operacije_radna_memorija.VRATI_MESTA:
                    ArrayList<Mesto> mesta = ServerKontroler.getInstance().vratiMesta();
                    res.setData(mesta);
                    break;
                case Operacije_radna_memorija.VRATI_PRIJAVAE:
                    ArrayList<PrijavaKvara> prijave = ServerKontroler.getInstance().vratiPrijave();
                    res.setData(prijave);
                    break;
                case Operacije_radna_memorija.VRATI_STAVKE_IZVESTAJA:
                    ArrayList<StavkaIzvestaja> stavke = ServerKontroler.getInstance().vratiStavke((IzvestajOServisu) req.getData());
                    res.setData(stavke);
                    break;
                case Operacije_radna_memorija.SACUVAJ_IZVESTAJ:
                    ServerKontroler.getInstance().sacuvajIzvestaj((IzvestajOServisu)req.getData());
                    break;
                case Operacije_radna_memorija.VRATI_DELOVE:
                    ArrayList<RezervniDeo> delovi=ServerKontroler.getInstance().vratiDelove();
                    res.setData(delovi);
                    break;
                case Operacije_radna_memorija.OBRISI_IZVESTAJ:
                    ServerKontroler.getInstance().obrisiIzvestaj((IzvestajOServisu) req.getData());
                    break;
                
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setResponseStatus(StatusOdgovora.Error);
        }
        return res;
    }
}
