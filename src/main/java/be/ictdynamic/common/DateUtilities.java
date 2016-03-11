package be.ictdynamic.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class DateUtilities.
 *
 * @author Wim Van den Brande
 * @since 11/03/2016 - 13:56
 */
public class DateUtilities {
    private static final Logger LOG = LoggerFactory.getLogger(DateUtilities.class);

    public static long getFirstDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) + 1);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        LOG.debug("getFirstDayOfWeek = " + format.format(calendar.getTime()));

        return calendar.getTime().getTime();
    }
}
