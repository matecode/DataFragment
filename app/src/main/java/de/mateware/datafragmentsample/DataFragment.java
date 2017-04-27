package de.mateware.datafragmentsample;

import de.mateware.datafragment.DataFragmentBase;

/**
 * Created by mate on 27.04.2017.
 */

public class DataFragment extends DataFragmentBase {

    private int i = 0;

    public void addOne(){
        i++;
    }

    public int get() {
        return i;
    }
}
