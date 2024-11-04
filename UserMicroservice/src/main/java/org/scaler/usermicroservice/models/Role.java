package org.scaler.usermicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Role extends BaseModel{
    private String value;

//    public Role(String value){
//        this.value = value;
//    }

}
