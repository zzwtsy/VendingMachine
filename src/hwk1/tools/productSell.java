package hwk1.tools;

public class productSell {
    public float pay(float userPayMoney, float accountsPayable) {
        if (userPayMoney > accountsPayable) {
            return (userPayMoney - accountsPayable);
        } else if (userPayMoney - accountsPayable == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
