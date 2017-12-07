package com.izzatul.bismillahta.materi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.izzatul.bismillahta.R;

/**
 * Created by Izzatul on 11/21/2017.
 */

public class MateriView extends Activity{
    public static final String APP_TAG = "com.izzatul.bismillahta";

    private TextView judul_materi, deskripsi_materi;

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        this.setContentView(R.layout.list_materi);

        this.judul_materi = this.findViewById(R.id.tv_judul_materi);
        this.deskripsi_materi = this.findViewById(R.id.deskripsi_materi);
    }
}
