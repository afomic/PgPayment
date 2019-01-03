package afomic.com.pgpayment.helper;

import java.util.HashMap;
import java.util.Map;

public class FacultyHelper {
    private static Map<String, String[]> departmentStore = new HashMap<>();
    private static Map<String, Integer> schoolFees = new HashMap<>();

    static {
        departmentStore.put("Faculty of Admin", new String[]{"Department of Accounting "});
        departmentStore.put("Faculty of Agriculture", new String[]{"Department of Animal Science "});
        departmentStore.put("Faculty of Arts", new String[]{"Department of English "});
        departmentStore.put("Faculty of Basic Clinical Sciences", new String[]{"Department of Nursing "});
        departmentStore.put("Faculty of Clinical Sciences", new String[]{"Department of Medicine "});
        departmentStore.put("Faculty of  Environment and  Development Management", new String[]{"Department of Building"});

        schoolFees.put("Faculty of Admin",19700);
        schoolFees.put("Faculty of Agriculture",27700);
        schoolFees.put("Faculty of Arts",19700);
        schoolFees.put("Faculty of Basic Clinical Sciences",30700);
        schoolFees.put("Faculty of Clinical Sciences",30700);
        schoolFees.put("Faculty of  Environment and  Development Management",30700);

    }

    public static String[] getDepartments(String faculty) {
        String[] departmentList = departmentStore.get(faculty);
        if (departmentList != null) {
            return departmentList;
        }
        return new String[]{};
    }
}
