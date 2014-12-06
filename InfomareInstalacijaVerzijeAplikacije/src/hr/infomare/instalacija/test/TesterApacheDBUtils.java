package hr.infomare.instalacija.test;

import hr.infomare.instalacija.pomocna.Pomocna;

public class TesterApacheDBUtils {
    public TesterApacheDBUtils() {
        super();
    }

    public static void main(String[] args) {
        TesterApacheDBUtils testerApacheDBUtils = new TesterApacheDBUtils();
        Pomocna.spojiSeNaBazu();
    }
}
