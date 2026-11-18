public class ProductCalculations implements IProductCalculations{
    private static final double VAT_Rate=0.15;
    private static final double COMMISION_Rate=0.05;
    private final double price;
    public ProductCalculations(double price){
        this.price=price;

    }

    @Override
    public double GetVat() {
        return price*VAT_Rate;
    }

    @Override
    public double GetCommission() {
        return price*COMMISION_Rate;
    }

    @Override
    public double GetTotal() {
        return price+GetVat()+GetCommission();
    }
}
