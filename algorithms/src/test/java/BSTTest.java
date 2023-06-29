import java.util.Collections;
import java.util.List;

import com.algopfml.hw1.bst.BinarySearchTree;
import com.algopfml.hw1.bst.Node;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.algopfml.hw1.bst.BinarySearchTree.getAllValues;
import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BSTTest {
    @Test
    @Parameters(method = "treeProvider")
    public void testMin(BinarySearchTree bst) {
        List<Integer> values = getAllValues(bst);
        int min = Collections.min(values);
        assertEquals(min, bst.findMinNode().getValue());
    }

    @Test
    @Parameters(method = "treeProvider")
    public void testMax(BinarySearchTree bst) {
        List<Integer> values = getAllValues(bst);
        int max = Collections.max(values);
        assertEquals(max, bst.findMaxNode().getValue());
    }

    @Test
    @Parameters(method = "treeAndValueProvider")
    public void testFind(BinarySearchTree bst, int valueToFind) {
        //todo:
        Node foundValue = bst.findByValue(valueToFind);
        assertEquals(foundValue.getValue(), valueToFind);
    }

    @Test
    @Parameters(method = "treeAndNodeProvider")
    public void testDelete(BinarySearchTree bst, Node nodeToDelete) {
        Node deleted = bst.delete(nodeToDelete);
        assertEquals(nodeToDelete, deleted);
    }

    @SuppressWarnings("unused")
    Object[][] treeProvider() {
        return new Object[][]{
                {
                        new BinarySearchTree(
                                new Node(
                                        3,
                                        Node.leafFrom(2),
                                        Node.leafFrom(5)
                                )
                        )
                }
        };
    }

    @SuppressWarnings("unused")
    Object[][] treeAndValueProvider() {
        return new Object[][]{
                {
                        new BinarySearchTree(
                                new Node(
                                        3,
                                        Node.leafFrom(2),
                                        Node.leafFrom(5)
                                )
                        ),
                        5
                }
        };
    }

    Object[][] treeAndNodeProvider() {
        Node n = new Node(3,
                Node.leafFrom(2),
                Node.leafFrom(5));
        return new Object[][]{
                {
                        new BinarySearchTree(n), n

                }
        };
    }
}
