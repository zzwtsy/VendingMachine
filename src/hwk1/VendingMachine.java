package hwk1;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author zzwtsy
 */
public class VendingMachine {
    final String reg = "\\d+";
    final Scanner sc = new Scanner(System.in);
    final double[][] LOG_CHANGE = new double[1000][1];
    final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    final String[][] LOG_DRINKS_NAME = new String[1000][1];
    final int[][] LOG_DRINKS_NUMBER = new int[1000][1];
    final double[][] LOG_USER_PAY_MONEY = new double[1000][1];
    final String[][] LOG_USER_SALE_STATUS = new String[1000][1];
    final String[][] SALE_TIME = new String[1000][1];
    String adminPwd;
    String saleStatus;
    double userPayMoney;
    int logCount = 0;
    int buyNumber;
    int drinksSerialNumber;
    String[] data = null;
    String[][] namePrice = null;
    int[] number = null;

    /**
     * 记录日志方法
     */
    public void writeLog() {
        //商品名称
        LOG_DRINKS_NAME[logCount - 1][0] = namePrice[drinksSerialNumber][0];
        //用户购买商品数量
        LOG_DRINKS_NUMBER[logCount - 1][0] = buyNumber;
        //用户付款金额
        LOG_USER_PAY_MONEY[logCount - 1][0] = userPayMoney;
        //商品售卖状态
        LOG_USER_SALE_STATUS[logCount - 1][0] = saleStatus;
    }

    /**
     * 显示日志内容方法
     */
    public void displayLog() {
        System.out.printf("%-10s\t %-10s\t %-10s\t %-10s\t %-10s\t %-10s \n\n", "名称", "购买数量", "用户付款", "找零", "售卖状态", "售卖时间:");
        for (int i = 0; i < logCount; i++) {
            System.out.printf("%-10s\t %-10s\t %-10s\t %-10s\t %-10s\t %-10s \n", LOG_DRINKS_NAME[i][0], LOG_DRINKS_NUMBER[i][0], LOG_USER_PAY_MONEY[i][0] + "元", LOG_CHANGE[i][0] + "元", LOG_USER_SALE_STATUS[i][0], SALE_TIME[i][0]);
        }
        System.out.println();
        maintain();
    }

    /**
     * 修改产品名称
     */
    public void modifyProductName(){
        while (true) {
            System.out.println("序号" + "\t" + "名称");
            for (int i = 0; i < data.length; i++) {
                System.out.println((i + 1) + ".\t" + namePrice[i][0]);
            }
            System.out.print("请输入对应序号(输入0终止修改):");
            int choice = sc.nextInt();
            if (choice < 0 | choice > data.length) {
                System.out.println("输入错误！！！请重新输入");
                continue;
            } else if (choice == 0) {
                productControlMenu();
            }
            int temp = choice - 1;
            System.out.print("请输入新的名称:");
            String newProductName = sc.next();
            namePrice[temp][0] = newProductName;
            productControlMenu();
            break;
        }
    }

    /**
     * 修改产品序号
     */
    public void modifyProductNumber() {
        while (true) {
            System.out.println("序号" + "\t" + "名称\t\t" + "数量\t");
            for (int i = 0; i < data.length; i++) {
                System.out.println((i + 1) + ".\t" + namePrice[i][0] + "\t\t" + number[i] + "\t");
            }
            System.out.print("请输入对应序号(输入0终止修改):");
            int choice = sc.nextInt();
            if (choice < 0 | choice > data.length) {
                System.out.println("输入错误！！！请重新输入");
                continue;
            } else if (choice == 0) {
                productControlMenu();
            }
            int temp = choice - 1;
            System.out.print("请输入新的数量:");
            int newProductNumber = sc.nextInt();
            number[temp] = newProductNumber;
            productControlMenu();
            break;
        }
    }

    /**
     * 修改产品价格
     */
    public void modifyProductPrice() {
        while (true) {
            System.out.println("序号" + "\t" + "名称\t\t" + "价格\t");
            for (int i = 0; i < data.length; i++) {
                System.out.println((i + 1) + ".\t" + namePrice[i][0] + "\t\t" + namePrice[i][1] + "元\t");
            }
            System.out.print("请输入对应序号(输入0终止修改):");
            int choice = sc.nextInt();
            if (choice < 0 | choice > data.length) {
                System.out.println("输入错误！！！请重新输入");
                continue;
            } else if (choice == 0) {
                productControlMenu();
            }
            int temp = choice - 1;
            System.out.print("请输入新的价格:");
            String newProductPrice = sc.next();
            namePrice[temp][1] = newProductPrice;
            productControlMenu();
            break;
        }
    }

    /**
     * 产品管理菜单
     */
    public void productControlMenu() {
        System.out.println("1.修改产品名称\n2.修改产品数量\n3.修改产品价格\n0.返回");
        System.out.print("请输入选项对应序号:");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> modifyProductName();
            case 2 -> modifyProductNumber();
            case 3 -> modifyProductPrice();
            case 0 -> maintain();
            default -> System.out.println("输入错误！！！请重新输入");
        }
    }


    /**
     * 初始化数据
     */
    public void initialize() {
        System.out.print("请输入初始化数据:");
        String init = sc.next();
        data = init.split("\\|");
        //商品名称，价格
        namePrice = new String[data.length][2];
        //商品数量
        number = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            String[] detail = data[i].split(":");
            //商品名称
            namePrice[i][0] = detail[0];
            //商品价格
            namePrice[i][1] = detail[1];
            //商品数量
            number[i] = Integer.parseInt(detail[2]);
        }
    }

    /**
     * 维护菜单
     */
    public void maintain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.显示售卖记录\n2.产品管理\n3.修改管理员密码\n0.返回");
        System.out.print("请输入数字:");
        int choice = sc.nextInt();
        switch (choice) {
            //显示销售日志
            case 1 -> displayLog();
            //产品管理
            case 2 -> productControlMenu();
            //修改密码
            case 3 -> modifyUserPwd();
            //返回开始菜单
            case 0 -> startInterface();
            default -> System.out.println("输入错误,请重新输入!!!");
        }
    }

    /**
     * 修改管理密码
     */
    public void modifyUserPwd(){
        VendingMachine vendingMachine = new VendingMachine();
        Console con = System.console();
        System.out.print("请输入原密码:");
        String originalAdminPwd = new String(con.readPassword());
        if (originalAdminPwd.equals(vendingMachine.adminPwd)) {
            while (true) {
                System.out.print("请输入新密码(包含英文字母):");
                String newAdminPwd = new String(con.readPassword());
                if (newAdminPwd.equals(adminPwd)) {
                    System.out.println("与原密码相同！！！");
                    continue;
                }
                if (!newAdminPwd.matches(reg)) {
                    System.out.print("确认密码:");
                    String temp = new String(con.readPassword());
                    if (temp.equals(newAdminPwd)) {
                        adminPwd = newAdminPwd;
                        System.out.println("密码设置成功");
                        break;
                    } else {
                        System.out.println("两次密码不一致！！！");
                        maintain();
                    }
                } else {
                    System.out.println("密码强度低，请重新输入！！！");
                }
            }
        } else {
            System.out.println("密码错误！！！");
            maintain();
        }
        maintain();
    }

    public void test() {
        number[drinksSerialNumber] = number[drinksSerialNumber] - buyNumber;
        SALE_TIME[logCount][0] = DATE.format(new Date());
        logCount += 1;
        saleStatus = "失败";
        userPayMoney = 0;
        writeLog();
        salesInterface();//返回售卖界面
    }

    /**
     * 售卖产品方法
     */
    public void pay()  {
        double count;
        //String类型转换int型。计算用户应付款金额
        double commodityPrices = Double.parseDouble(namePrice[drinksSerialNumber][1]) * buyNumber;
        System.out.print("请投币" + commodityPrices + "元:(输入0中止付款)");
        //接收用户投币
        userPayMoney = sc.nextDouble();
        if (userPayMoney == 0) {
            test();
        }
        while (true) {
            if (userPayMoney > commodityPrices) {
                System.out.println("正在为您找零" + (userPayMoney - commodityPrices) + "元");
                System.out.println("请取走您的饮料,欢迎下次光临。");
                break;
            } else if (userPayMoney - commodityPrices == 0) {
                System.out.println("请取走您的饮料,欢迎下次光临。");
                break;
            } else {
                System.out.print("投币不足!!!" + "\n" + "请继续投币" + (commodityPrices - userPayMoney) + "元(输入0中止付款):");
                count = sc.nextDouble();
                if (count == 0) {
                    test();
                }
                userPayMoney += count;
            }
        }
        number[drinksSerialNumber] = number[drinksSerialNumber] - buyNumber;
        SALE_TIME[logCount][0] = DATE.format(new Date());
        logCount += 1;
        saleStatus = "成功";
        writeLog();
        //找零日志记录
        LOG_CHANGE[logCount][0] = (userPayMoney - commodityPrices);
        System.out.println("***购买小票***");
        //打印购买小票
        System.out.println("商品名称:" + namePrice[drinksSerialNumber][0] +
                "\n单价:\t" + namePrice[drinksSerialNumber][1] +
                "元\n数量:\t" + buyNumber +
                "瓶\n总价:\t" + commodityPrices +
                "元\n找零:\t" + (userPayMoney - commodityPrices) +
                "元\n购买日期:" + SALE_TIME[logCount - 1][0]);
        salesInterface();//返回售卖界面
    }

    /**
     * 产品售卖界面
     */
    public void salesInterface() {
        if (namePrice == null || number == null) {
            System.out.print("售卖数据未初始化,即将进入初始化页面");
            initialize();
            System.out.print("售卖数据初始化完成,即将进入售卖页面");
        }
        System.out.println("***饮料售卖机***");
        System.out.printf("%-3s\t %-10s\t %-10s\t %-10s \n\n", "序号", "名称", "价格", "数量");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%3s\t %-10s\t %-10s\t %-10s \n", (i + 1) + ".", namePrice[i][0], namePrice[i][1], number[i]);
        }
        System.out.println();
        while (true) {
            System.out.print("请输入饮品对应的序号:");
            String choice = sc.next();
            //输入管理员密码返回初始界面
            if (choice.equals(adminPwd)) {
                startInterface();
            }
            //判断用户输入的商品序号是否错误
            if (Integer.parseInt(choice) < 0 | Integer.parseInt(choice) > data.length) {
                System.out.println("输入错误！！！请重新输入");
                continue;
            }
            drinksSerialNumber = Integer.parseInt(choice) - 1;
            System.out.print("请输入购买数量:");
            String temp = sc.next();
            buyNumber = Integer.parseInt(temp);
            //判断用户输入购买的商品数量是否大于库存数量
            if (buyNumber > number[drinksSerialNumber]) {
                System.out.println("所选商品余量不足!!!" + "\n" + "请重新选购");
                continue;
            }
            break;
        }
        pay();
    }

    /**
     * 开始界面
     */
    public void startInterface()  {
        while (true) {
            System.out.println("1.系统维护\n2.进入售卖界面\n3.初始化系统\n0.退出程序");
            System.out.print("请输入数字:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> maintain();
                case 2 -> salesInterface();
                case 3 -> initialize();
                case 0 -> System.exit(0);
                default -> System.out.println("输入错误，请重新输入!!!");
            }
        }
    }

    /**
     * 主方法
     */
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        System.out.println("***饮料售卖机***");
        //要求用户设置管理员密码
        while (true) {
            System.out.print("请输入要设定的管理员密码(包含英文字母):");
            Console con = System.console();
            vm.adminPwd = new String(con.readPassword());
            if (!vm.adminPwd.matches(vm.reg)) {
                System.out.print("确认密码:");
                String temp = new String(con.readPassword());
                if (temp.equals(vm.adminPwd)) {
                    System.out.println("密码设置成功");
                    break;
                } else {
                    System.out.println("两次密码不一致！！！");
                }
            } else {
                System.out.println("密码强度低，请重新输入！！！");
            }
        }
        System.out.println("初始化完成\n正在进入系统中");
        vm.startInterface();//初始界面
    }
}