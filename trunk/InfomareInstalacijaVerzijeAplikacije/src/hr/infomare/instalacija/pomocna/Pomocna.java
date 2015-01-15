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
    private static Connection konekcijaORA;
    private static Connection konekcijaH2;
    private static Connection konekcijaMS;

    public Pomocna() {
        super();
    }

    public static void setKonekcijaORA(Connection konekcija) {
        Pomocna.konekcijaORA = konekcija;
    }

    public static Connection getKonekcijaORA() {
        return konekcijaORA;
    }

    public static void spojiSeNaBazuORA() {
        try {
            konekcijaORA = DriverManager.getConnection("jdbc:oracle:thin:@ora11:1521:orcl11", "dbo", "mareinfo");
            konekcijaORA.setAutoCommit(false);
            System.out.println("Spojen na ORA");
        } catch (SQLException e) {
            porukaError(null, e.getMessage());
        }
    }

    public static Connection getKonekcijaMS() {
        return konekcijaMS;
    }

    public static void setKonekcijaMS(Connection konekcija) {
        Pomocna.konekcijaMS = konekcija;
    }


    public static void spojiSeNaBazuMS() {
        try {
            // jdbc:sqlserver://;servername=server_name;integratedSecurity=true;authenticationScheme=JavaKerberos
            konekcijaMS =
                DriverManager.getConnection("jdbc:sqlserver://;servername=LEUT;databaseName=GPSDB;user=magicapp;password=infomare");
            konekcijaMS.setAutoCommit(false);
            System.out.println("Spojen na MS razvoj");
        } catch (SQLException e) {
            porukaError(null, e.getMessage());

        }
    }


    public static void setKonekcijaH2(Connection konekcijaH2) {
        Pomocna.konekcijaH2 = konekcijaH2;
    }

    public static Connection getKonekcijaH2() {
        return konekcijaH2;
    }

    public static void spojiSeNaBazuH2() {
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:" + "C:/Test/baza;TRACE_LEVEL_FILE=0";
            String user = "damirl";
            String password = "mareinfo";
            konekcijaH2 = DriverManager.getConnection(url, user, password);
            konekcijaH2.setAutoCommit(false);
        } catch (SQLException ex) {
            porukaError(null, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            porukaError(null, ex.getMessage());
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
            upit = konekcijaORA.prepareStatement(upitSQL);
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
