package teksystems.casestudy.formbean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import teksystems.casestudy.validation.EmailUnique;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class RegisterFormBean {

    //this id will be null in the case of a create
    //and will be populated with the userId in the case of an edit
    private Integer id;

    @NotBlank(message = "Email is required.")
    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Email format invalid.")
    @EmailUnique(message = "Your email is already in the database.")
    private String email;

    @NotBlank(message = "First Name is required.")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    private String lastName;

    @Length(min =3, max =15, message="Password must be between 3 and 15 characters.")
    @NotBlank(message = "Password is required.")
    private String password;

    @NotBlank(message = "Confirm Password is required.")
    private String confirmPassword;

    @AssertTrue(message= "Checkbox is required.")
    private boolean checkbox;

}
