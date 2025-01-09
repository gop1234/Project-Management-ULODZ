package Data;

import java.util.UUID;

public class InvoiceUtils {
    public static String generateInvoiceNumber() {
        return "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
