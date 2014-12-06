package hr.infomare.instalacija.test;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class TesterApache {
    public TesterApache() {
        super();
    }

    public static void main(String[] args) throws IOException {
        TesterApache testerApache = new TesterApache();
        /*String datoteka = "C:\\Skripte\\DB_32087\\10_ALTR_FP041_ADD_OPC.sql";
        ucitajDatotekuUString(datoteka);*/
        //List<String> listaDatoteka = popisDatotekaUMapi("C:\\Skripte\\DB_32087\\");
    }

    /**
     *
     * @param stazaDatoteke
     * @return Sadrzaj datoteke kao string, po kodnoj stranici Cp1250
     * @throws IOException
     *  Limit je 1GB datoteka. Metoda uvijek zatvara datoteku.
     */
    public static String ucitajDatotekuUString(String stazaDatoteke) throws IOException {
        File datoteka = new File(stazaDatoteke);
        String sadrzajDatoteke = null;
        sadrzajDatoteke = FileUtils.readFileToString(datoteka, "Cp1250"); // Metoda uvijek sama zatvori datoteku
        //System.out.println(sadrzajDatoteke);
        return sadrzajDatoteke;
    }

    /**
     *
     * @param stazaMape
     * @return String lista datoteka u mapi
     * Ne ukljucuje pod mape
     */
    public static List<String> popisDatotekaUMapi(String stazaMape) {
        File mapa = new File(stazaMape);
        String[] ekstenzije = { "txt", "sql" };
        Collection<File> listaDatoteka = new ArrayList<File>();
        List<String> listaDatotekaString = new ArrayList<String>();
        listaDatoteka = FileUtils.listFiles(mapa, ekstenzije, false);
        for (File datoteka : listaDatoteka) {
            listaDatotekaString.add(datoteka.getName());
            //System.out.println(datoteka.getName());
        }
        return listaDatotekaString;
    }

    /**
     *
     * @param datoteka
     * @return rbr datoteke, tj. broj prije prvog znaka _ u nazivu, ako nema broja vraca 0
     */
    public static int rbrDatoteke(String datoteka) {
        try {
            //System.out.println(Integer.valueOf(datoteka.substring(0, datoteka.indexOf("_"))));
            return Integer.valueOf(datoteka.substring(0, datoteka.indexOf("_")));
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     *
     * @param mapa
     * @return vraca stazu mape sa slah-om na kraju ako ga nema
     */
    public static String dodajSlashNaStazuMape(String mapa) {
        String zadnjiZnak = mapa.substring(mapa.length() - 1);
        String novaMapa = mapa;
        if (!(zadnjiZnak.equals("\\") || zadnjiZnak.equals("/")))
            novaMapa = novaMapa.trim() + "\\";
        //System.out.println(novaMapa);
        return novaMapa;
    }

    /**
     *
     * @param datoteka
     * @return ektenziju datoteke
     */
    public static String ektenzijaDatoteke(String datoteka) {
        // System.out.println(datoteka.substring(datoteka.lastIndexOf(".") + 1));
        return FilenameUtils.getExtension(datoteka);
    }

}
