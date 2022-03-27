package Bank;

import Customer.Customer;

public interface Operator {
    void printAllLoanProducts();

    void printLoanProductDetails(String loanProductId);

    void calculateLTVForLoanProducts(LoanProduct loanProduct, double propertyValue);

    Customer findCustomer(String customerId);

    boolean findCustomer(Customer customer);

    void printAllCustomers();

}
