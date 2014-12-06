package hr.infomare.instalacija.test;

import hr.infomare.instalacija.pojo.Skripta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class Tester {
    public Tester() {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Tester tester = new Tester();

        /*String datoteka = "C:\\Skripte\\DB_32087\\10_ALTR_FP041_ADD_OPC.sql";
        ucitajDatotekuUString(datoteka);*/
        // popisDatotekaUMapi("C:\\Skripte\\DB_32087\\");
        //sortiranje();
        /*   List<String> listaDatoteka = popisDatotekaUMapi("C:\\Skripte\\DB_32087\\");
        List<Skripta> listaSkripti = new ArrayList<Skripta>();
        for (String datoteka : listaDatoteka) {
            Skripta skripta = new Skripta();
            skripta.setRbr(rbrDatoteke(datoteka));
            skripta.setNaziv(datoteka);
            skripta.setTxt(ucitajDatotekuUString("C:\\Skripte\\DB_32087\\"+datoteka));
            listaSkripti.add(skripta);
        }

        sortiranjeSkriptiPoRbr(listaSkripti);
        for (Skripta skripta : listaSkripti) {
            System.out.println(skripta.getRbr());
       }*/
        //System.out.println(dodajSlashNaStazuMape("C:\\Skripte\\DB_32087"));
        // ucitajMagicINI();
        File src = new File("C:\\Skripte\\DB_32087");
        File dest = new File("C:\\Skripte\\Kopija");
        kopirajMapu(src, dest);

    }

    public static String ucitajDatotekuUString(String datoteka) throws FileNotFoundException,
                                                                       UnsupportedEncodingException, IOException {
        FileInputStream fis = new FileInputStream(datoteka);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "CP1250"));
        String linija = br.readLine();
        while (linija != null) {
            sb.append(linija);
            sb.append('\n');
            linija = br.readLine();
        }
        //System.out.println(sb.toString());
        return linija;
    }

    public static List<String> popisDatotekaUMapi(String mapa) {
        List<String> listaDatoteka = new ArrayList<String>();
        File[] datoteke = new File(mapa).listFiles();
        String ektenzija;
        for (File datoteka : datoteke) {
            ektenzija = ektenzijaDatoteke(datoteka.getName());
            if (datoteka.isFile() && (ektenzija.equalsIgnoreCase("sql") || ektenzija.equalsIgnoreCase("txt"))) {
                listaDatoteka.add(datoteka.getName());
                //ektenzijaDatoteke(datoteka.getName());
                rbrDatoteke(datoteka.getName());
            }
        }
        /*
         * for (String s : listaDatoteka) {
         *   System.out.println(s);
        }*/
        return listaDatoteka;
    }

    public static String ektenzijaDatoteke(String datoteka) {
        // System.out.println(datoteka.substring(datoteka.lastIndexOf(".") + 1));
        return datoteka.substring(datoteka.lastIndexOf(".") + 1);
    }

    public static int rbrDatoteke(String datoteka) {
        //System.out.println(Integer.valueOf(datoteka.substring(0, datoteka.indexOf("_"))));
        return Integer.valueOf(datoteka.substring(0, datoteka.indexOf("_")));
    }

    public static void sortiranjeSkriptiPoRbr(List<Skripta> listaSkripti) {
        Collections.sort(listaSkripti, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                Skripta skripta1 = (Skripta) obj1;
                Skripta skripta2 = (Skripta) obj2;
                return skripta1.getRbr().compareTo(skripta2.getRbr());
            }
        });
    }

    public static String dodajSlashNaStazuMape(String mapa) {
        String zadnjiZnak = mapa.substring(mapa.length() - 1);
        String novaMapa = mapa;
        if (!(zadnjiZnak.equals("\\") || zadnjiZnak.equals("/")))
            novaMapa = novaMapa.trim() + "\\";
        //System.out.println(novaMapa);
        return novaMapa;
    }

    public static void ucitajMagicINI() throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("C:\\Skripte\\DB_32087\\99_Magic.ini");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            //System.out.println(prop.getProperty("AccessDBMS"));           
            Enumeration em = prop.keys();
              while(em.hasMoreElements()){
              String str = (String)em.nextElement();
              System.out.println(str + "->> " + prop.get(str));
              }
              

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    public static void kopirajMapu(File src, File dest)
    	throws IOException{
 
    	if(src.isDirectory()){
 
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		   System.out.println("Mapa kopirana iz " 
                              + src + "  u " + dest);
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   kopirajMapu(srcFile,destFile);
    		}
 
    	}else{
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	        System.out.println("Datoteka kopirana iz " + src + " u " + dest);
    	}
    }
}
