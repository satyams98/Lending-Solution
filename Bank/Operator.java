package Bank;

import Customer.Customer;

public interface Operator {
    void printAllLoanProducts();

    void printLoanProductDetails(String loanProductId);

    void calculateLTVForLoanProducts(LoanProduct loanProduct, int propertyValue);

    Customer findCustomer(int customerId);

    boolean findCustomer(Customer customer);

    void printAllCustomers();

}
