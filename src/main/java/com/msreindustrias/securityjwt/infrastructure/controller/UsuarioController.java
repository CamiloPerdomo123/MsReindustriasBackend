package com.msreindustrias.securityjwt.infrastructure.controller;

import com.msreindustrias.securityjwt.application.dto.in.DatosPersonalesRequestDto;
import com.msreindustrias.securityjwt.application.dto.in.LoginRequestDto;
import com.msreindustrias.securityjwt.application.dto.out.JWTAuthResponseDto;
import com.msreindustrias.securityjwt.application.port.input.IUsuariosService;
import com.msreindustrias.securityjwt.application.security.CustomUserDetailsService;
import com.msreindustrias.securityjwt.application.security.JwtService;
import com.msreindustrias.securityjwt.application.security.JwtTokenProvider;
import com.msreindustrias.securityjwt.domain.entity.DatosPersonalesEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private JwtService jwtService;

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

    @ApiOperation(value = "Endpoint que permite listar iniciar session")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/auth/iniciarSesion")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginDTO) {

        try {
            JWTAuthResponseDto jwtAuthResonseDTO = jwtService.authenticateUser(loginDTO);

            return ResponseEntity.ok(jwtAuthResonseDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
