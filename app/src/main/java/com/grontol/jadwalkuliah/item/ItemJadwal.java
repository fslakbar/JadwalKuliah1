package com.grontol.jadwalkuliah.item;

/**
 * Created by GrT on 5/30/2017.
 */

public class ItemJadwal
{
    private int idJadwal;
    private String namaJadwal;
    
    public ItemJadwal(String idJadwal, String namaJadwal)
    {
        this.idJadwal = Integer.parseInt(idJadwal);
        this.namaJadwal = namaJadwal;
    }
    
    public int getIdJadwal()
    {
        return idJadwal;
    }
    
    public void setIdJadwal(int idJadwal)
    {
        this.idJadwal = idJadwal;
    }
    
    public String getNamaJadwal()
    {
        return namaJadwal;
    }
    
    public void setNamaJadwal(String namaJadwal)
    {
        this.namaJadwal = namaJadwal;
    }
}
