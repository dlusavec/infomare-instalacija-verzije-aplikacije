package hr.infomare.instalacija.test;

import hr.infomare.instalacija.pojo.Skripta;
import hr.infomare.instalacija.pojo.Tecaj;
import hr.infomare.instalacija.pomocna.Pomocna;

import java.sql.SQLException;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class TesterApacheDBUtils {
    public TesterApacheDBUtils() {
        super();
    }

    public static void main(String[] args) throws SQLException {
        TesterApacheDBUtils testerApacheDBUtils = new TesterApacheDBUtils();
        Pomocna.spojiSeNaBazu();
        //select();
        Skripta skripta = new Skripta();
        skripta.setNaziv("10_backup.sql");
        skripta.setRbr(10);
        skripta.setTxt("CREATE TABLE FK001_12122014 AS SELECT * FROM FK001;");
        // insert(skripta);
        update(skripta);
    }

    public static void select() throws SQLException {
        QueryRunner qr = new QueryRunner();
        ResultSetHandler<List<Tecaj>> rsh = new BeanListHandler<Tecaj>(Tecaj.class);
        String upitSQL =
            "SELECT VALUTA,  GODINA,  MJESEC,  DAN,  KUPOVNI,  SREDNJI,  PRODAJNI FROM TECAJ WHERE " +
            "VALUTA=? AND GODINA =? AND MJESEC =?";
        List<Tecaj> listaTecaja = qr.query(Pomocna.getKonekcija(), upitSQL, rsh, new Object[] { "EUR", 2014, 1 });
        for (Tecaj tecaj : listaTecaja) {
            System.out.println(tecaj.getDan().toString() + " kupovni->>" + tecaj.getKupovni());
        }
    }

    public static void insert(Skripta skripta) throws SQLException {
        QueryRunner qr = new QueryRunner();
        int i =
            qr.update(Pomocna.getKonekcija(), "insert into skripta values (?,?,?)", skripta.getRbr(),
                      skripta.getNaziv(), skripta.getTxt());
        Pomocna.getKonekcija().commit();
    }

    public static void update(Skripta skripta) throws SQLException {
        QueryRunner qr = new QueryRunner();
        int i =
            qr.update(Pomocna.getKonekcija(), "update skripta set naziv=? where rbr=?", skripta.getNaziv() + "update",
                      skripta.getRbr());
        Pomocna.getKonekcija().commit();
    }
}
