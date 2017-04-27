package de.mateware.datafragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mate on 27.04.2017.
 */

public abstract class DataFragmentActivity<DATAFRAGMENT extends DataFragmentBase> extends AppCompatActivity {
    DATAFRAGMENT dataFragment;
    Class<DATAFRAGMENT> datafragmentClass;

    public DataFragmentActivity(Class<DATAFRAGMENT> datafragmentClass) {
        this.datafragmentClass = datafragmentClass;
    }

    public void createOrRetainDataFragment() {
        createOrRetainDataFragment(null);
    }

    public void createOrRetainDataFragment(Bundle bundle) {
        retainDataFragment();
        if (dataFragment == null) {
            createDataFragment(bundle);
        }
    }

    public void retainDataFragment() {
        dataFragment = DataFragmentBase.retainDataFragment(getSupportFragmentManager(),datafragmentClass);
    }

    public void createDataFragment() {
        createDataFragment(null);
    }

    public void createDataFragment(Bundle bundle) {
        dataFragment = DataFragmentBase.createDataFragment(getSupportFragmentManager(),datafragmentClass,bundle);
    }

    public DATAFRAGMENT getDataFragment() {
        return dataFragment;
    }


}
