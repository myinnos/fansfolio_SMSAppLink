package in.myinnos.textmeapp_fansfolio;

public class JsFunctionCallFormatter {
    public JsFunctionCallFormatter() {
    }

    public static String paramToString(Object param) {
        String str = "";
        if(param instanceof String) {
            str = (String)param;
            str = str.replace("\\", "\\\\");
            str = str.replace("\"", "\\\"");
            str = str.replace("\n", "\\n");
            str = String.format("\"%s\"", new Object[]{str});
        } else {
            try {
                double nfe = Double.parseDouble(param.toString());
                str = param.toString();
            } catch (NumberFormatException var4) {
                ;
            }
        }

        return str;
    }

    public static String toString(String functionName, Object... args) {
        StringBuilder paramsStr = new StringBuilder();
        Object[] arr$ = args;
        int len$ = args.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object param = arr$[i$];
            if(paramsStr.length() > 0) {
                paramsStr.append(", ");
            }

            paramsStr.append(paramToString(param));
        }

        return String.format("%s(%s)", new Object[]{functionName, paramsStr});
    }
}
