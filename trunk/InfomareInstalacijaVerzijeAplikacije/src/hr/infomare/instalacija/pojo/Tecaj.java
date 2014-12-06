package hr.infomare.instalacija.pojo;

import java.math.BigDecimal;

public class Tecaj {
    private String valuta;
    private Integer godina;
    private Integer mjesec;
    private Integer dan;
    private BigDecimal kupovni;
    private BigDecimal srednji;
    private BigDecimal prodajni;

    public Tecaj() {
        super();
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getValuta() {
        return valuta;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    public Integer getGodina() {
        return godina;
    }

    public void setMjesec(Integer mjesec) {
        this.mjesec = mjesec;
    }

    public Integer getMjesec() {
        return mjesec;
    }

    public void setDan(Integer dan) {
        this.dan = dan;
    }

    public Integer getDan() {
        return dan;
    }

    public void setKupovni(BigDecimal kupovni) {
        this.kupovni = kupovni;
    }

    public BigDecimal getKupovni() {
        return kupovni;
    }

    public void setSrednji(BigDecimal srednji) {
        this.srednji = srednji;
    }

    public BigDecimal getSrednji() {
        return srednji;
    }

    public void setProdajni(BigDecimal prodajni) {
        this.prodajni = prodajni;
    }

    public BigDecimal getProdajni() {
        return prodajni;
    }
}
