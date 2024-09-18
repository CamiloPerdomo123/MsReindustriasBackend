package com.msreindustrias.securityjwt.infrastructure.controller;

import com.msreindustrias.securityjwt.application.ResourceNotFoundException;
import com.msreindustrias.securityjwt.application.dto.in.DatosPersonalesRequestDto;
import com.msreindustrias.securityjwt.application.dto.in.LoginRequestDto;
import com.msreindustrias.securityjwt.application.dto.out.JWTAuthResponseDto;
import com.msreindustrias.securityjwt.application.port.input.IJwtService;
import com.msreindustrias.securityjwt.application.port.input.IUsuariosService;
import com.msreindustrias.securityjwt.domain.entity.DatosPersonalesEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IJwtService authService;


    @ApiOperation(value = "Endpoint que permite crear usuarios")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "v1/interno/usuario/registro")
    public ResponseEntity<?> CrearUsuarioController(@RequestBody DatosPersonalesRequestDto request) {
        try {
            // Asumiendo que tienes un método trazaEvaluacionesWeb en service
            usuariosService.guardarUsuario(request);
            return new ResponseEntity<>("Usuario Creado Con exito", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "Endpoint que permite listar los usuarios")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "v1/interno/usuario/list")
    public List<DatosPersonalesEntity> ListarUsuarioController() {

        // Asumiendo que tienes un método trazaEvaluacionesWeb en service
        List<DatosPersonalesEntity> datosPersonalesEntityList = usuariosService.listUsuarios();
        return datosPersonalesEntityList;

    }

    @ApiOperation(value = "Endpoint que permite iniciar session")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "v1/interno/api/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        try {
            // Autenticar al usuario y generar el token
            JWTAuthResponseDto response = authService.autenticacionUsuario(loginRequestDto);

            // Retornar el token si la autenticación fue exitosa
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
