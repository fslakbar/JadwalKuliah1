package com.grontol.jadwalkuliah.item;

import java.io.Serializable;

/**
 * Created by GrT on 5/30/2017.
 */

public class ItemRincian implements Serializable
{
    private String idRincian;
    private String nama;
    private String hari;
    private String jam;
    private String dosen;
    private String ruang;
    
    public ItemRincian()
    {
        
    }
    
    public ItemRincian(String[] s)
    {
        idRincian = s[0];
        nama = s[1];
        hari = s[2];
        jam = s[3];
        dosen = s[4];
        ruang = s[5];
    }
    
    public String getIdRincian()
    {
        return idRincian;
    }
    
    public void setIdRincian(String idRincian)
    {
        this.idRincian = idRincian;
    }
    
    public String getNama()
    {
        return nama;
    }
    
    public void setNama(String nama)
    {
        this.nama = nama;
    }
    
    public String getHari()
    {
        return hari;
    }
    
    public void setHari(String hari)
    {
        this.hari = hari;
    }
    
    public String getJam()
    {
        return jam;
    }
    
    public void setJam(String jam)
    {
        this.jam = jam;
    }
    
    public String getDosen()
    {
        return dosen;
    }
    
    public void setDosen(String dosen)
    {
        this.dosen = dosen;
    }
    
    public String getRuang()
    {
        return ruang;
    }
    
    public void setRuang(String ruang)
    {
        this.ruang = ruang;
    }
}
