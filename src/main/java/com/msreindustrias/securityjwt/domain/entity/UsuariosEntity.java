package com.msreindustrias.securityjwt.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "usuarios")
public class UsuariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuarios;

    @NotNull
    @Column(name = "id_persona")
    private long idPersona;

    @NotNull
    @Column(name = "id_rol")
    private long idRol;

    @NotNull
    @Column(name = "usuario")
    @Size(max=50)
    private String usuario;

    @NotNull
    @Column(name = "correo")
    @Size(max=100)
    private String correo;

    @NotNull
    @Column(name = "password")
    @Size(max=65)
    private String password;

    @NotNull
    @Column(name = "estado")
    @Size(max=20)
    private String estado;

    @NotNull
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @NotNull
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public void genearPassword() {
        String minusculas= "abcdefghijklmnopqrstuvwxyz";
        String mayusculas="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String dijitos="0123456789";
        String especiales="@#$%&?";
        String passwordGenerado="";
        for(int i=0; i<2; i++){
            Random aleatorio= new Random();
            int posmin= aleatorio.nextInt(minusculas.length());
            int posmay=aleatorio.nextInt(mayusculas.length());
            int posDigitos= aleatorio.nextInt(dijitos.length());
            int posEspeciales= aleatorio.nextInt(especiales.length());

            passwordGenerado+=minusculas.substring(posmin,posmin+1)+
                    mayusculas.substring(posmay,posmay+1)+
                    dijitos.substring(posDigitos,posDigitos+1)+
                    especiales.substring(posEspeciales,posEspeciales+1);
        }

        this.password = passwordGenerado;
    }
}
