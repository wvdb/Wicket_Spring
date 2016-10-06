package be.ictdynamic.common.collections.diff;

/**
 * Class DiffEquals.
 *
 * @author Geroen Dierckx
 * @version $Revision$
 * @since Oct 27, 2009
 * @param <T> The type of element
 */
public interface DiffEquals<T> {
    boolean isEqual(T t1, T t2);
}
