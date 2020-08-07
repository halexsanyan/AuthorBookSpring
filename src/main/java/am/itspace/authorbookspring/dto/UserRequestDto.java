package am.itspace.authorbookspring.dto;

import am.itspace.authorbookspring.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private int id;
    @NotBlank(message = "Name is requaired")
    private String name;
    @NotBlank(message = "Surname is requaired")
    private String surname;
    @NotBlank(message = "Email is requaired")
    @Email(message = "Email isn't valid")
    private String username;
    @Size(min = 6,message = "Passwore lenght sould be at least 6 symbol")
    private String password;
    @Size(min = 6,message = "Passwore lenght sould be at least 6 symbol")
    private String confirmPassword;

    private Role role;

}
