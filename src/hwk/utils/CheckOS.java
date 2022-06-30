package hwk.utils;

public class CheckOS {
    /**
     * 检测操作系统
     *
     * @return 操作系统名称
     */
    public static String checkOS() {
        return System.getProperty("os.name");
    }
}
