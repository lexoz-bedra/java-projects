import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import segmenttree.SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static segmenttree.SegmentTree.getAllValues;
import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class DOTest {
    @Test
    @Parameters(method = "TreeAndBordersProvider")
    public void testSum(SegmentTree st, int l, int r) {
        assertEquals(26, st.sum(l, r));
    }

    @Test
    @Parameters(method = "TreePlaceAndValueProvider")
    public void testSet(SegmentTree st, int place, int value) {
        SegmentTree st2 = new SegmentTree(new ArrayList<>(Arrays.asList(1, 2, 3, 15, 5, 6, 7, 8)));
        st.set(3, 15);
        assertEquals(st2.sum(0, 7), st.sum(0, 7));
    }

  /*  @Test
    @Parameters(method = "TreeBordersAndValueProvider")
    public void testSetAll(SegmentTree st, int l, int r, int val) {
        SegmentTree st2 = new SegmentTree(new ArrayList<>(Arrays.asList(1, 2, 3, 8, 8, 8, 8, 8)));
        st.set(3, 6, 8);
        assertEquals(st2.sum(0, 7), st.sum(0, 7));
    }*/

    Object[][] TreeAndBordersProvider() {
        return new Object[][] {
                {
                    new SegmentTree(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))), 4, 7
                }
        };
    }

    Object[][] TreePlaceAndValueProvider() {
        return new Object[][] {
                {
                        new SegmentTree(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))), 3, 15
                }
        };
    }

    Object[][] TreeBordersAndValueProvider() {
        return new Object[][] {
                {
                    new SegmentTree(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))), 3, 6, 8
                }
        };
    }

}
