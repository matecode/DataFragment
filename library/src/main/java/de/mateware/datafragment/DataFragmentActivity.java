package de.mateware.datafragment;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by mate on 27.04.2017.
 */

public abstract class DataFragmentActivity<DATAFRAGMENT extends DataFragmentBase> extends AppCompatActivity {
    DATAFRAGMENT        dataFragment;
    Class<DATAFRAGMENT> datafragmentClass;

    public DataFragmentActivity(Class<DATAFRAGMENT> datafragmentClass) {
        this.datafragmentClass = datafragmentClass;
    }

    public DATAFRAGMENT getDataFragment() {
        if (dataFragment == null && datafragmentClass != null)
            dataFragment = DataFragmentBase.createOrRetainDataFragment(getSupportFragmentManager(), datafragmentClass);
        return dataFragment;
    }


}
