package afomic.com.pgpayment.helper;

import java.util.HashMap;
import java.util.Map;

public class FacultyHelper {
    private static Map<String, String[]> departmentStore = new HashMap<>();

    static {
        departmentStore.put("Faculty of Admin", new String[]{"Department of Accounting "});
        departmentStore.put("Faculty of Agriculture", new String[]{"Department of Accounting "});
        departmentStore.put("Faculty of Arts", new String[]{"Department of Accounting "});
        departmentStore.put("Faculty of Basic Clinical Sciences", new String[]{"Department of Accounting "});
        departmentStore.put("Faculty of Clinical Sciences", new String[]{"Department of Accounting "});
        departmentStore.put("Faculty of Clinical Sciences", new String[]{"Department of Accounting "});
    }

    public static String[] getDepartments(String faculty) {
        return departmentStore.get(faculty);
    }
}
