package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String PATH = "WEB-INF/jsp/%s.jsp";
    public static String getPath(String jspFileName){
        if (jspFileName == null || jspFileName.isEmpty()){
            return null;
        }
        return PATH.formatted(jspFileName);
    }
}
