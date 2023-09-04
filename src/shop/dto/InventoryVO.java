package shop.dto;

public class InventoryVO {

    private int pno;

    private int amount;


    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InventoryVO{" +
                "pno=" + pno +
                ", amount=" + amount +
                '}';
    }
}