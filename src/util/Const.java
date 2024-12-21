package util;

import data.Config;
import data.Page;

public class Const {
    public static Config config = new Config();
    public static Page PAGE = new Page().setPageSize(20).setCurrentPage(1);
}
