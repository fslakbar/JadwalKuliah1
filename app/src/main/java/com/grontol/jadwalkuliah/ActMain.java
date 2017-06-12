package com.grontol.jadwalkuliah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActMain extends BaseActDrawer
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.lyt_main, frmContent);
    }
}
