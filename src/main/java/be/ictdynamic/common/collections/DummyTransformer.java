package be.ictdynamic.common.collections;

import org.apache.commons.collections15.Transformer;

/**
 * Class SimpleObjectTransformer.
 *
 * @author Geroen Dierckx
 * @version $Revision$
 * @param <O> From and to are the same object types
 * @since 6-jul-2007 - 9:00:57
 */
public class DummyTransformer<O> implements Transformer<O, O> {

    /**
     * {@inheritDoc}
     */
    public O transform(O value) {
        return value;
    }
}
