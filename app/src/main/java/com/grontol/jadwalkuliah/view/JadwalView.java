package com.grontol.jadwalkuliah.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;

import com.grontol.jadwalkuliah.item.ItemRincian;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrT on 5/30/2017.
 */

public class JadwalView
{
    String[] haris = 
            {
                    "Senin",
                    "Selasa",
                    "Rabu",
                    "Kamis",
                    "Jum'at",
                    "Sabtu"
            };
    
    Activity context;
    LinearLayout container;
    HariView[] hariView;
    
    String idJadwal;
    
    public JadwalView(Activity context, LinearLayout container, String idJadwal)
    {
        this.context = context;
        this.container = container;
        this.idJadwal = idJadwal;
        
        initHari();
    }
    
    private void initHari()
    {
        hariView = new HariView[haris.length];
        
        for (int a = 0; a < haris.length; a++)
        {
            hariView[a] = new HariView(context);
            hariView[a].init(idJadwal, haris[a]);
            
            container.addView(hariView[a].getView());
        }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 5555 && resultCode == context.RESULT_OK)
        {
            ItemRincian it = (ItemRincian) data.getSerializableExtra("it");
            
            for (HariView h : hariView)
            {
                if (h.getHari().equals(it.getHari()))
                {
                    h.onResult(it);
                }
            }
        }
    }
    
    public List<ItemRincian> getRincian()
    {
        List<ItemRincian> res = new ArrayList<>();
        
        for (HariView h : hariView)
        {
            res.addAll(h.getRincian());
        }
        
        return res;
    }
}
