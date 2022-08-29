package ro.itschool.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDTO {

    private Long id;
    private String username;
    private String fullName;
    private String email;

    private List<String> roles = new ArrayList<>();
    private Set<CartDTO> carts = new HashSet<>();
}