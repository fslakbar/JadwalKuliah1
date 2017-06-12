package com.grontol.jadwalkuliah.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.grontol.jadwalkuliah.R;
import com.grontol.jadwalkuliah.item.ItemRincian;

/**
 * Created by GrT on 5/30/2017.
 */

public class RincianView implements View.OnClickListener
{
    Context context;
    View v;
    
    TextView txtJam, txtMakul, txtDosen, txtRuang;
    View vDelete;
    
    RincianHandler handler;
    
    ItemRincian it;
    
    public RincianView(Context context, ItemRincian it, RincianHandler handler)
    {
        this.context = context;
        this.handler = handler;
        
        this.it = it;
        
        v = LayoutInflater.from(context).inflate(R.layout.item_rincian, null);
        
        txtJam = (TextView) v.findViewById(R.id.txtJam);
        txtMakul = (TextView) v.findViewById(R.id.txtMakul);
        txtDosen = (TextView) v.findViewById(R.id.txtDosen);
        txtRuang = (TextView) v.findViewById(R.id.txtRuang);
        
        txtJam.setText(it.getJam());
        txtMakul.setText(it.getNama());
        txtDosen.setText(it.getDosen());
        txtRuang.setText(it.getRuang());
        
        vDelete = v.findViewById(R.id.vDelete);
        vDelete.setOnClickListener(this);
    }
    
    public View getView()
    {
        return v;
    }
    
    public ItemRincian getIt()
    {
        return it;
    }
    
    @Override
    public void onClick(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Hapus");
        builder.setMessage("Mau hapus beneran?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                handler.onDelete(RincianView.this);
            }
        });
        builder.setNegativeButton("Tidak", null);
        builder.show();
    }
    
    public interface RincianHandler
    {
        void onDelete(RincianView r);
    }
}
