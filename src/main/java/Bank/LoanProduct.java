package Bank;
import Random.*;

import java.io.Serializable;


public class LoanProduct implements Serializable {
    public String loanProductCode;
    public String loanProductId;
    public LoanProduct() {
         loanProductId = RandomClass.generateRandomAlphaNumeric(6);
    }
    public String loanProductName;
    public boolean assetBased;
    public String loanSecurityType;
    public double minTenure;
    public double maxTenure;
    public double minLoanAmount;
    public double maxLoanAmount;
    public double roi;
    public double LTV;
    public String status;
    public String emiStatus;
    public double emiPerMonth;

    double LTVCalculationAsPerCollateralType(double propertyValue) {
        LTV = (maxLoanAmount / propertyValue) * 100;
        return LTV;
    }
}

class HomeLoan extends LoanProduct{
    PropertyType propertyType;
    NatureOfProperty natureOfProperty;
    PropertyPurpose propertyPurpose;
    PropertyOwnership propertyOwnership;
    double marketValue;
    double builtUpArea;
    double carpetArea;
    int propertyAge;

    double LTVCalculationAsPerCollateralType(){
        LTV =  (maxLoanAmount / marketValue)*100;
        return LTV;
    }


}

class ConsumerVehicleLoan extends LoanProduct{
    AssetCategory assetCategory;
    AssetVariant assetVariant;
    String assetModel;
    String manufacturer;
    int yearOfManufacture;
    double assetCost;
    double downPayment;

    double LTVCalculationAsPerCollateralType(){
        LTV =  (maxLoanAmount / assetCost)*100;
        return LTV;
    }

}

class EducationLoan extends LoanProduct{
    String courseName;
    String collegeName;
    CourseType courseType;
    DegreeType degreeType;
    EducationStream educationStream;
    double totalFees;

    double LTVCalculationAsPerCollateralType(){

        LTV =  (maxLoanAmount / totalFees);
        return LTV;
    }

}
