package gov.nist.hla.gridlabd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import gov.nist.pages.ucef.LinearConversionType;

/**
 * This interface defines a set of static methods that convert values between their GridLAB-D and HLA representations.
 *
 * @author Thomas Roth
 */
public interface ConversionMethods {
    /**
     * Convert a unix time into a GridLAB-D time stamp. The time stamp will be in GMT as future releases of GridLAB-D
     * will continue to support GMT as an additional time zone. The conversion from GMT to native simulation time will
     * be performed inside the GridLAB-D core libraries.
     *
     * @param unixTime A unix time to convert into a time stamp.
     * @return A GridLAB-D time stamp with the GMT time zone.
     */
    public static String toTimeStamp(long unixTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(new Date(unixTime*1000));
    }

    /**
     * Convert a GridLAB-D time stamp into unix time. If the time stamp has no time zone, it will default to GMT.
     *
     * @param timeStamp A GridLAB-D time stamp to convert into unix time.
     * @return The unix time equivalent of the time stamp.
     * @throws ParseException if the time stamp does not adhere to the GridLAB-D time format.
     */
    public static long toUnixTime(String timeStamp)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.parse(timeStamp).getTime()/1000;
    }

    /**
     * Perform linear conversion on a GridLAB-D value to produce its HLA equivalent.
     *
     * @param conversion A linear conversion rule that defines the coefficients.
     * @param gldValue A value retrieved from the GridLAB-D simulation.
     * @return The equivalent value that should be sent to the HLA federation.
     */
    public static double toHlaValue(double gldValue, LinearConversionType conversion) {
        return conversion.getScale() * gldValue + conversion.getOffset();
    }

    /**
     * Perform linear conversion on an HLA value to produce its GridLAB-D equivalent.
     *
     * @param conversion A linear conversion rule that defines the coefficients.
     * @param hlaValue A value received from the HLA federation.
     * @return The equivalent value that should be sent to the GridLAB-D simulation.
     */
    public static double toGldValue(double hlaValue, LinearConversionType conversion) {
        return (hlaValue - conversion.getOffset()) / conversion.getScale();
    }
}
