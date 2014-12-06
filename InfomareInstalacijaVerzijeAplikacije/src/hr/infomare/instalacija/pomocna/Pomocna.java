package hr.infomare.instalacija.pomocna;

import java.awt.Component;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Pomocna {
    private static Connection konekcija;

    public Pomocna() {
        super();
    }

    public static void setKonekcija(Connection konekcija) {
        Pomocna.konekcija = konekcija;
    }

    public static Connection getKonekcija() {
        return konekcija;
    }

    public static void spojiSeNaBazu() {
        try {
            konekcija = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "dbo", "mareinfo");
            konekcija.setAutoCommit(false);
        } catch (SQLException e) {
            porukaError(null, e.getMessage());
        }
    }

    public static void porukaInfo(Component c, String poruka) {
        JOptionPane.showMessageDialog(c, poruka, "Informacija", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void porukaError(Component c, String poruka) {
        JOptionPane.showMessageDialog(c, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
    }

    public static void ispis(Object object) {
        if (object == null) {
            System.err.println("Ispis objekta null reference...");
        } else {
            Class klasa = object.getClass();
            Field[] polja = klasa.getDeclaredFields();
            for (Field polje : polja) {
                polje.setAccessible(true);
                try {
                    System.out.println("----------------------------------------");
                    System.out.println(polje.getName() + ": " + polje.get(object));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    public static void instalirajValuteuCombo(JComboBox comboBox) {
        String upitSQL = "select distinct valuta from tecaj order by valuta";
        PreparedStatement upit = null;
        ResultSet rs = null;

        try {
            upit = konekcija.prepareStatement(upitSQL);
            rs = upit.executeQuery();
            while (rs.next()) {
                comboBox.addItem(rs.getObject(1));
            }
            comboBox.setSelectedItem("EUR");
            rs.close();
            upit.close();
        } catch (SQLException e) {
            porukaError(null, e.getMessage());
        }
    }
    
    
}
