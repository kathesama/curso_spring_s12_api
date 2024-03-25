package com.kahesama.demo.curso_spring_s12_api.entities;

import com.kahesama.demo.curso_spring_s12_api.model.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JavaType;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "create_at")
    private Date createAt; // Se generará automáticamente al crear la entidad

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt; // Se actualizará automáticamente al modificar la entidad

    /**
     * En la clase que lleva la relación (user) se hacen todas las especificaciones y luego en la clase subordinada
     * (roles) solo se hace referencia a la tabla principal de cuál es el campo que se va a vincular.
     * */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name="userId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"),
            uniqueConstraints = { @UniqueConstraint(columnNames = {"userId", "roleId"})}
    )
    private List<RoleEntity> roles = new ArrayList<>();

    @Column(unique = true)
    private String username;

    @Column(columnDefinition = "boolean default false") // Valor por defecto: false
    private Boolean enabled;

    @Column
    private String password;
}
