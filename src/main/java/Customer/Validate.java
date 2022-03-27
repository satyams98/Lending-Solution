package Customer;

public class Validate {


    static boolean dataTypeValidation(Object field, String type) {
        return field.getClass().getSimpleName().equals(type);
    }

    static boolean dataLength(Object field, int size){
           String fieldValue = String.valueOf(field);

           return  (fieldValue.length()<=size);
        }

    static boolean specialCharacters (String field, boolean shouldContainSpecialChars){
             return true;
    }

    static boolean specialCharacters (String field, boolean shouldContainSpecialChars, String specialChars) {

        for (int i = 0; i < specialChars.length(); i++) {
            if (field.contains(String.valueOf(specialChars.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    static  boolean domainValue (String field, String[]  validList){
        for (String e : validList) {
            if (e.equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }

    static boolean validateEmail (String email){
             int countDot =0;
             int countAtRate =0;
             int countSpace = 0;

             for (int i=0;i<email.length();i++){
                 if(email.charAt(i) == '.'){
                     countDot++;
                 }
                 if (email.charAt(i) == ' '){
                     countSpace++;
                 }
                 if(email.charAt(i) == '@'){
                     countAtRate++;
                 }
             }
             if(countDot==1 && countAtRate==1 && countSpace==0){
                return true;
             }
             return false;

    }

}
