package hwk1;

public class productSell {
    public float pay(float userPayMoney, int productNumbers) {
        double count;
        //计算用户应付款金额
        float accountsPayable = userPayMoney * productNumbers;
        if (userPayMoney > accountsPayable) {
            return (userPayMoney - accountsPayable);
        } else if (userPayMoney - accountsPayable == 0) {
            return 0;
        } else {
            return -1;
        }
//        number[drinksSerialNumber] = number[drinksSerialNumber] - buyNumber;
//        SALE_TIME[logCount][0] = DATE.format(new Date());
//        logCount += 1;
//        saleStatus = "成功";
//        writeLog();
//        //找零日志记录
//        LOG_CHANGE[logCount][0] = (userPayMoney - commodityPrices);
//        System.out.println("***购买小票***");
//        //打印购买小票
//        System.out.println("商品名称:" + namePrice[drinksSerialNumber][0] +
//                "\n单价:\t" + namePrice[drinksSerialNumber][1] +
//                "元\n数量:\t" + buyNumber +
//                "瓶\n总价:\t" + commodityPrices +
//                "元\n找零:\t" + (userPayMoney - commodityPrices) +
//                "元\n购买日期:" + SALE_TIME[logCount - 1][0]);
//        salesInterface();//返回售卖界面
    }
}
