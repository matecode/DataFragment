package de.mateware.datafragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by mate on 27.04.2017.
 */

public abstract class DataFragmentBase extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public static <DATAFRAGMENT extends DataFragmentBase> DATAFRAGMENT createOrRetainDataFragment(
            FragmentManager fragmentManager, Class<DATAFRAGMENT> datafragmenttypeClass) {
        return createOrRetainDataFragment(fragmentManager, datafragmenttypeClass, null);
    }

    public static <DATAFRAGMENT extends DataFragmentBase> DATAFRAGMENT createOrRetainDataFragment(
            FragmentManager fragmentManager, Class<DATAFRAGMENT> datafragmenttypeClass, Bundle bundle) {
        DATAFRAGMENT dataFragment = retainDataFragment(fragmentManager, datafragmenttypeClass);
        if (dataFragment == null) {
            dataFragment = createDataFragment(fragmentManager, datafragmenttypeClass, bundle);
        }
        return dataFragment;
    }


    public static <DATAFRAGMENT extends DataFragmentBase> DATAFRAGMENT createDataFragment(FragmentManager fragmentManager,
                                                                                                  Class<DATAFRAGMENT> datafragmenttypeClass) {
        return createDataFragment(fragmentManager, datafragmenttypeClass, null);
    }


    public static <DATAFRAGMENT extends DataFragmentBase> DATAFRAGMENT createDataFragment(FragmentManager fragmentManager,
                                                                                                  Class<DATAFRAGMENT> datafragmenttypeClass,
                                                                                                  Bundle bundle) {
        try {
            DATAFRAGMENT dataFragment = datafragmenttypeClass.newInstance();
            if (bundle != null) dataFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                           .add(dataFragment, datafragmenttypeClass.getSimpleName())
                           .commitNow();
            return dataFragment;
        } catch (java.lang.InstantiationException e) {
            Log.e(Utils.TAG, "", e);
        } catch (IllegalAccessException e) {
            Log.e(Utils.TAG, "", e);
        }
        return null;
    }

    public static <DATAFRAGMENT extends DataFragmentBase> DATAFRAGMENT retainDataFragment(FragmentManager fragmentManager,
                                                                                                  Class<DATAFRAGMENT> datafragmenttypeClass) {
        return (DATAFRAGMENT) fragmentManager.findFragmentByTag(datafragmenttypeClass.getSimpleName());
    }


}
