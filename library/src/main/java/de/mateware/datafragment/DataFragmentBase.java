package de.mateware.datafragment;

import android.content.Context;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public static <DATAFRAGMENTTYPE extends DataFragmentBase> DATAFRAGMENTTYPE createOrRetainDataFragment(
            FragmentManager fragmentManager, Class<DATAFRAGMENTTYPE> datafragmenttypeClass) {
        return createOrRetainDataFragment(fragmentManager, datafragmenttypeClass, null);
    }

    public static <DATAFRAGMENTTYPE extends DataFragmentBase> DATAFRAGMENTTYPE createOrRetainDataFragment(
            FragmentManager fragmentManager, Class<DATAFRAGMENTTYPE> datafragmenttypeClass, Bundle bundle) {
        DATAFRAGMENTTYPE dataFragment = retainDataFragment(fragmentManager, datafragmenttypeClass);
        if (dataFragment == null) {
            dataFragment = createDataFragment(fragmentManager, datafragmenttypeClass, bundle);
        }
        return dataFragment;
    }


    public static <DATAFRAGMENTTYPE extends DataFragmentBase> DATAFRAGMENTTYPE createDataFragment(FragmentManager fragmentManager,
                                                                                                  Class<DATAFRAGMENTTYPE> datafragmenttypeClass) {
        return createDataFragment(fragmentManager, datafragmenttypeClass, null);
    }


    public static <DATAFRAGMENTTYPE extends DataFragmentBase> DATAFRAGMENTTYPE createDataFragment(FragmentManager fragmentManager,
                                                                                                  Class<DATAFRAGMENTTYPE> datafragmenttypeClass,
                                                                                                  Bundle bundle) {
        try {
            DATAFRAGMENTTYPE dataFragment = datafragmenttypeClass.newInstance();
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

    public static <DATAFRAGMENTTYPE extends DataFragmentBase> DATAFRAGMENTTYPE retainDataFragment(FragmentManager fragmentManager,
                                                                                                  Class<DATAFRAGMENTTYPE> datafragmenttypeClass) {
        return (DATAFRAGMENTTYPE) fragmentManager.findFragmentByTag(datafragmenttypeClass.getSimpleName());
    }


}
