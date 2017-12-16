package com.cppbt.imaisnaini.equeue.ui.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.cppbt.imaisnaini.equeue.R;
import com.cppbt.imaisnaini.equeue.ui.fragment.JadwalDokterFragment;
import com.cppbt.imaisnaini.equeue.ui.fragment.PesanAntrianFragment;
import com.cppbt.imaisnaini.equeue.ui.fragment.RiwayatAntrianFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.toolbar_main)
 //   Toolbar mToolbar;
    @BindView(R.id.toolbar_main_tvTitle)
    TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
        initFragment(new PesanAntrianFragment(), R.string.title_fragment_pesan_antrian);
    }

    private void initViews(){
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        final ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDefaultDisplayHomeAsUpEnabled(false);


        createDrawer(this, mToolbar);
    }

    private void initFragment(Fragment fragment, int title){
        getSupportFragmentManager().beginTransaction().replace(R.id.acivity_main_flMain, fragment).commit();
        mTvTitle.setText(title);
    }


    public Drawer createDrawer(final Activity activity, Toolbar toolbar){
        PrimaryDrawerItem pesanAntrianItem = new PrimaryDrawerItem().withIdentifier(0).withName(R.string.title_fragment_pesan_antrian);
        PrimaryDrawerItem jadwalDokterItem = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.title_fragment_jadwal_dokter);
        PrimaryDrawerItem riwayatAntrianItem = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.title_fragment_riwayat_antrian);

        DrawerBuilder mDrawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withSelectedItem(-1)
                .addDrawerItems(
                        pesanAntrianItem,
                        jadwalDokterItem,
                        riwayatAntrianItem
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        long i = drawerItem.getIdentifier();
                        if (i == 0){
                            initFragment(new PesanAntrianFragment(), R.string.title_fragment_pesan_antrian);
                        }else if(i == 1){
                            initFragment(new JadwalDokterFragment(), R.string.title_fragment_jadwal_dokter);
                        }else if(i == 2){
                            initFragment(new RiwayatAntrianFragment(), R.string.title_fragment_riwayat_antrian);
                        }
                        return false;
                    }
                });
        return mDrawer.build();
    }
}
