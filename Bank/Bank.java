package Bank;
import Customer.*;

import java.util.*;


public class Bank implements Maker, Operator {

    public static void main(String[] args) {




    }

    class IterateCustomerObjects implements MyIterator { //Member Inner Class

        Iterator<Customer> it = customers.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }
        @Override
        public Object next() {
            return it.next();

        }
        @Override
        public boolean remove() {

            it.remove();
            return true;
        }
    }

    //static nested class
    static class StaticIterateCustomerObjects implements MyIterator {

        Iterator<Customer> it = customers.iterator();

        public boolean hasNext() {

            return it.hasNext();
        }

        public Object next() {

            return it.next();
        }

        public boolean remove() {

            it.remove();
            return true;
        }
    }
//Anonymous Inner Class

     MyIterator customerIterator = new MyIterator (){

        Iterator<Customer> it = customers.iterator();
        @Override
        public boolean hasNext() {
            return it.hasNext();

        }
        @Override
        public Object next() {

           return it.next();
        }
        @Override
        public boolean remove() {
            it.remove();
            return true;
        }
    };

    static List<LoanProduct> loanProducts = new ArrayList<>();

    static List<Customer> customers = new ArrayList<Customer>();

    static HashMap<LoanProduct, Customer> customerLoan = new HashMap<LoanProduct, Customer>();

    public boolean registerCustomer(Customer customer) {

        customers.add(customer);
        return true;
    }

    public Customer findCustomer(int customerId) {
            for (Customer c:customers){
                if(c.customerId==customerId){
                    return c;
                }
            }
        return null;
    }

    public void printAllCustomers() {

        customers.forEach(c-> System.out.println(c.customerName));
    }

    public boolean findCustomer(Customer customer) {

        return customers.contains(customer);
    }


    public boolean deleteCustomer(int customerId) {
            for (Customer c: customers){
                if(c.customerId==customerId){
                    return customers.remove(c);
                }

            }
        return false;
    }

    public boolean addNewLoanProduct(LoanProduct loanProduct) {
        return loanProducts.add(loanProduct);

    }
    public boolean addNewLoanProduct(HomeLoan loanProduct){
        return loanProducts.add(loanProduct);
    }
    public boolean addNewLoanProduct(EducationLoan loanProduct){
        return loanProducts.add(loanProduct);
    }
    public boolean addNewLoanProduct(ConsumerVehicleLoan loanProduct){
        return loanProducts.add(loanProduct);
    }

    public boolean removeLoanProduct(String loanProductCode) {
        for (LoanProduct l : loanProducts){
            if(l.loanProductCode==loanProductCode){
                return loanProducts.remove(l);
            }

        }
        return false;

    }

    public void printAllLoanProducts() {
        loanProducts.forEach(l-> System.out.println(l.loanProductCode+" "+l.loanProductName));

    }

    public void printLoanProductDetails(String loanProductId) {
        loanProducts.forEach(l-> {if (l.loanProductId==loanProductId) {
            System.out.println(l.loanProductCode+" "+l.loanProductId+" "+l.loanProductName);
        }});

    }

    public void calculateLTVForLoanProducts(LoanProduct loanProduct, int propertyValue) {
        loanProduct.LTVCalculationAsPerCollateralType(propertyValue);
    }
    
}
