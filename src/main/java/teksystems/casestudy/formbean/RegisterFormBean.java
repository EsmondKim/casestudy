package teksystems.casestudy.formbean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterFormBean {

    //this id will be null in the case of a create
    //and will be populated with the userId in the case of an edit
    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String confirmPassword;

}
