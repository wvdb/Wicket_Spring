package be.ictdynamic.common.collections;

/**
 * Interface Sortable.
 *
 * @author Geroen Dierckx
 * @version $Revision$
 * @since 19-apr-2007
 */
public interface Sortable {

    /**
     * Returns the value this sortable object is order by.
     *
     * @return Comparable referencing the value this sortable object is order by.
     */
    Comparable getSortValue();
}