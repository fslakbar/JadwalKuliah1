package com.grontol.jadwalkuliah.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grontol.jadwalkuliah.ActAddRincian;
import com.grontol.jadwalkuliah.R;
import com.grontol.jadwalkuliah.db.DataHelper;
import com.grontol.jadwalkuliah.item.ItemRincian;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrT on 5/30/2017.
 */

public class HariView implements View.OnClickListener, RincianView.RincianHandler
{
    Activity context;
    View v;
    
    TextView txtHari;
    LinearLayout llJadwalContainer;
    
    View vAdd;
    
    String idJadwal;
    String hari;
    
    List<RincianView> rincianViews;
    
    public HariView(Activity c)
    {
        context = c;
        v = LayoutInflater.from(c).inflate(R.layout.item_hari, null);
        
        txtHari = (TextView)v.findViewById(R.id.txtHari);
        llJadwalContainer = (LinearLayout)v.findViewById(R.id.llJadwalContainer);
        
        rincianViews = new ArrayList<>();
        
        vAdd = v.findViewById(R.id.vAdd);
        vAdd.setOnClickListener(this);
    }
    
    public void init(String idJadwal, String hari)
    {
        this.hari = hari;
        txtHari.setText(hari);
        
        if (idJadwal != null)
        {
            List<ItemRincian> rincian = DataHelper.getRincian(context, idJadwal, hari);
            
            for (ItemRincian it : rincian)
            {
                RincianView r = new RincianView(context, it, this);
                rincianViews.add(r);
                llJadwalContainer.addView(r.getView());
            }
        }
    }
    
    public View getView()
    {
        return v;
    }
    
    public String getHari()
    {
        return hari;
    }
    
    @Override
    public void onClick(View v)
    {
        if (v == vAdd)
        {
            Intent i = new Intent(context, ActAddRincian.class);
            i.putExtra("hari", hari);
            context.startActivityForResult(i, 5555);
        }
    }
    
    public void onResult(ItemRincian it)
    {
        RincianView r = new RincianView(context, it, this);
        rincianViews.add(r);
        llJadwalContainer.addView(r.getView());
    }
    
    @Override
    public void onDelete(RincianView r)
    {
        rincianViews.remove(r);
        llJadwalContainer.removeView(r.getView());
    }
    
    public List<ItemRincian> getRincian()
    {
        List<ItemRincian> res = new ArrayList<>();
        
        for (RincianView r : rincianViews)
        {
            res.add(r.getIt());
        }
        
        return res;
    }
}
