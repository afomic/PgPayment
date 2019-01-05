package afomic.com.pgpayment.helper;

import java.util.HashMap;
import java.util.Map;

public class FacultyHelper {
    private static Map<String, String[]> departmentStore = new HashMap<>();
    private static Map<String, Integer> schoolFees = new HashMap<>();

    static {
        departmentStore.put("Faculty of Admin", new String[]{"Department of Accounting",
                "Department of Public Administration",
                "Department of Accounting"});
        departmentStore.put("Faculty of Agriculture", new String[]{"Department of Agricultural Economics",
                "Department of Agricultural Extension and Rural Development", "" +
                "Department of Animal Science",
                "Department of Crop Production", "Department of Soil Science",
                "Department of Family, Nutrition & Consumer Sciences"});
        departmentStore.put("Faculty of Arts", new String[]{"Department of Religious Studies",
                "Department of Philosophy",
                "Department of English",
                "Department of History",
                "Department of Foreign Languages",
                "Department of Fine Arts",
                "Department of Dramatic Arts",
                "Department of Music",
                "Department of Institute of Cultural Studies"
        });

        departmentStore.put("Faculty of Technology", new String[]{"Department of Agricultural and Environmental Engineering",
                "Department of Chemical Engineering",
                "Department of Civil Engineering",
                "Department of Computer Science and Engineering",
                "Department of Electronic and Electrical Engineering",
                "Department of Food Science and Technology",
                "Department of Mechanical Engineering",
                "Department of Materials Science and Engineering"
        });

        departmentStore.put("Faculty of Basic Medical Sciences", new String[]{"Department of Anatomy and Cell Biology",
                ""});
        departmentStore.put("Faculty of Clinical Sciences", new String[]{"Department of Medicine "});
        departmentStore.put("Faculty of  Environment and  Development Management",
                new String[]{"Department of Fine and Applied Arts",
                        "Department of Architecture",
                        "Department of Building",
                        "Department of Estate Management",
                        "Department of Quantity Surveying",
                        "Department of Urban and Regional Planning",
                        "Department of Fine and Applied Arts"
                });

        schoolFees.put("Faculty of Admin", 19700);
        schoolFees.put("Faculty of Agriculture", 27700);
        schoolFees.put("Faculty of Arts", 19700);
        schoolFees.put("Faculty of Basic Clinical Sciences", 30700);
        schoolFees.put("Faculty of Clinical Sciences", 30700);
        schoolFees.put("Faculty of  Environment and  Development Management", 30700);

    }

    public static String[] getDepartments(String faculty) {
        String[] departmentList = departmentStore.get(faculty);
        if (departmentList != null) {
            return departmentList;
        }
        return new String[]{};
    }

    public static int getFacultySchoolFees(String faculty) {
        Integer amount = schoolFees.get(faculty);
        if (amount == null) {
            return 0;
        }
        return amount;
    }
}
