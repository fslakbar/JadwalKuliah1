package com.grontol.jadwalkuliah.db;

import android.content.Context;

import com.grontol.jadwalkuliah.item.ItemJadwal;
import com.grontol.jadwalkuliah.item.ItemRincian;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrT on 5/30/2017.
 */

public class DataHelper
{
    public static void editJadwal(Context c, String idJadwal, String namaJadwal)
    {
        String q = "UPDATE jadwal SET nama_jadwal = '" + namaJadwal.replace("'", "''") + "' WHERE id_jadwal = '" + idJadwal + "'";
        SQLiteDataManager.write(c, q);
    }
    
    public static void deleteJadwal(Context c, String idJadwal)
    {
        String q = "DELETE FROM jadwal WHERE id_jadwal = '" + idJadwal + "'";
        SQLiteDataManager.write(c, q);
    
        String q2 = "DELETE FROM rincian WHERE id_jadwal = '" + idJadwal + "'";
        SQLiteDataManager.write(c, q2);
    }
    
    public static String insertJadwal(Context c, String namaJadwal)
    {
        String idjadwal = SQLiteDataManager.getNextId(c, "jadwal", "id_jadwal");
        String q = "INSERT INTO jadwal VALUES('" + idjadwal + "', '" + namaJadwal.replace("'", "''") + "')";
        SQLiteDataManager.write(c, q);
        
        return idjadwal;
    }
    
    public static List<ItemJadwal> getJadwal(Context c)
    {
        List<ItemJadwal> res = new ArrayList<>();
        
        String q = "SELECT * FROM jadwal";
        String[][] ss = SQLiteDataManager.read(c, q);
        
        for (String[] s : ss)
        {
            res.add(new ItemJadwal(s[0], s[1]));
        }
        
        return res;
    }
    
    public static void insertRincian(Context c, String idJadwal, List<ItemRincian> l)
    {
        String q1 = "DELETE FROM rincian WHERE id_jadwal = '" + idJadwal + "'";
        SQLiteDataManager.write(c, q1);
        
        for (ItemRincian it : l)
        {
            String q2 = "INSERT INTO rincian (nama, hari, jam, dosen, ruang, id_jadwal) VALUES (";
            q2 += "'" + it.getNama().replace("'", "''") + "', ";
            q2 += "'" + it.getHari().replace("'", "''") + "', ";
            q2 += "'" + it.getJam() + "', ";
            q2 += "'" + it.getDosen().replace("'", "''") + "', ";
            q2 += "'" + it.getRuang().replace("'", "''") + "', ";
            q2 += "'" + idJadwal + "')";
            
            SQLiteDataManager.write(c, q2);
        }
    }
    
    public static List<ItemRincian> getRincian(Context c, String idJadwal, String hari)
    {
        List<ItemRincian> l = new ArrayList<>();
        
        hari = hari.replace("'", "''");
        
        String q = "SELECT * FROM rincian WHERE id_jadwal = '" + idJadwal + "' AND hari = '" + hari + "' ORDER BY substr(jam, 1, 2), substr(jam, 4, 5)";
        String[][] res = SQLiteDataManager.read(c, q);
        
        for (String[] s : res)
        {
            l.add(new ItemRincian(s));
        }
        
        return l;
    }
}
