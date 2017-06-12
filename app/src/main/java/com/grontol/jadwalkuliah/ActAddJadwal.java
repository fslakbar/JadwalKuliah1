package com.grontol.jadwalkuliah;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.grontol.jadwalkuliah.db.DataHelper;
import com.grontol.jadwalkuliah.view.JadwalView;
import com.grontol.jadwalkuliah.view.RincianView;

/**
 * Created by GrT on 5/30/2017.
 */

public class ActAddJadwal extends BaseActToolbar implements View.OnClickListener
{
    LinearLayout llHariContainer;
    JadwalView jadwalView;
    
    EditText edtNamaJadwal;
    View vSave, vDelete;
    
    boolean isEdit;
    String idJadwal, namaJadwal;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.lyt_add_jadwal, frmContent);
        
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (isEdit)
        {
            idJadwal = getIntent().getStringExtra("idJadwal");
            namaJadwal = getIntent().getStringExtra("namaJadwal");
        }
        
        llHariContainer = (LinearLayout)findViewById(R.id.llHariContainer);
        jadwalView = new JadwalView(this, llHariContainer, idJadwal);
        
        edtNamaJadwal = (EditText)findViewById(R.id.edtNamaJadwal); 
        
        vSave = findViewById(R.id.vSave);
        vDelete = findViewById(R.id.vHapusJadwal);
        vSave.setOnClickListener(this);
        vDelete.setOnClickListener(this);
    
        if (isEdit)
        {
            edtNamaJadwal.setText(namaJadwal);
        }
        else
        {
            vDelete.setVisibility(View.GONE);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        jadwalView.onActivityResult(requestCode, resultCode, data);
        
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    @Override
    public void onClick(View v)
    {
        if (v == vSave)
        {
            if (edtNamaJadwal.getText().toString().isEmpty())
            {
                toast("Isi nama jadwalnya dulu dong...");
            }
            else
            {
                save();
            }
        }
        else if (v == vDelete)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hapus");
            builder.setMessage("Mau hapus beneran?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    DataHelper.deleteJadwal(ActAddJadwal.this, idJadwal);
                    
                    toast("Jadwal udah kehapus");
                    finish();
                }
            });
            builder.setNegativeButton("Tidak", null);
            builder.show();
        }
    }
    
    private void save()
    {
        if (isEdit)
        {
            DataHelper.editJadwal(this, idJadwal, edtNamaJadwal.getText().toString());
        }
        else
        {
            idJadwal = DataHelper.insertJadwal(this, edtNamaJadwal.getText().toString());
        }
    
        DataHelper.insertRincian(this, idJadwal, jadwalView.getRincian());
        toast("Jadwal udah kesave");
        finish();
    }
}
