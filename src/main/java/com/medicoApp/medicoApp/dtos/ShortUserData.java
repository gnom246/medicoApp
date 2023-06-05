package com.medicoApp.medicoApp.dtos;

import com.medicoApp.medicoApp.entities.ROLE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShortUserData {
    Long id;
    String userName;
    ROLE role;
}
