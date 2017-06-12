package com.grontol.jadwalkuliah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.grontol.jadwalkuliah.item.ItemRincian;

/**
 * Created by GrT on 5/30/2017.
 */

public class ActAddRincian extends BaseActToolbar implements View.OnClickListener
{
    EditText edtMakul, edtDosen, edtRuang;
    Spinner spnJam;
    
    View vSave;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.lyt_add_rincian, frmContent);
        
        edtMakul = (EditText)findViewById(R.id.edtMakul);
        edtDosen = (EditText)findViewById(R.id.edtDosen);
        edtRuang = (EditText)findViewById(R.id.edtRuang);
        
        spnJam = (Spinner)findViewById(R.id.spnJam);
        
        vSave = findViewById(R.id.vSave);
        vSave.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v)
    {
        if (v == vSave)
        {
            if (edtMakul.getText().toString().isEmpty())
            {
                toast("Makul isi lah...");
            }
            else if (edtDosen.getText().toString().isEmpty())
            {
                toast("Dosennya kok gak ada...???");
            }
            else if (edtRuang.getText().toString().isEmpty())
            {
                toast("Kuliahmu di mana? Ruangnya isi dong...");
            }
            else
            {
                ItemRincian it = new ItemRincian();
                it.setNama(edtMakul.getText().toString());
                it.setDosen(edtDosen.getText().toString());
                it.setRuang(edtRuang.getText().toString());
                it.setJam(spnJam.getSelectedItem().toString());
                it.setHari(getIntent().getStringExtra("hari"));
    
                Intent i = new Intent();
                i.putExtra("it", it);
                
                setResult(RESULT_OK, i);
                finish();
            }
        }
    }
}
