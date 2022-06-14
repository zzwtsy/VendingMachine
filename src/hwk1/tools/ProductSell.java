package hwk1.tools;

public class ProductSell {
    /**
     *
     * @param userPayMoney 用户付款的金额
     * @param accountsPayable 用户需要付款的金额
     * @return 0：无需找零，-1：用户付款的金额少于用户需要付款的金额，default：返回找零金额
     */
    public static float pay(float userPayMoney, float accountsPayable) {
        if (userPayMoney > accountsPayable) {
            return (userPayMoney - accountsPayable);
        } else if (userPayMoney - accountsPayable == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
