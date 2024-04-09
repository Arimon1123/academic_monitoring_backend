package com.example.backend_academic_monitoring.Controller;


import com.example.backend_academic_monitoring.Config.CookieHelper;
import com.example.backend_academic_monitoring.Config.CustomUserDetailsService;
import com.example.backend_academic_monitoring.Config.JwtUtil;
import com.example.backend_academic_monitoring.DTO.*;
import com.example.backend_academic_monitoring.Service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Value("${jwt.accesTokenCookieName}")
    private String cookieName;
    @Value("${server.host}")
    private String host;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO<?>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                                                    HttpServletResponse httpServletResponse)
            throws Exception {
        try {
            LOGGER.info("{}", authenticationRequest);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
                            , authenticationRequest.getPassword())
            );

            UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            LOGGER.info("{}", userdetails);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(userdetails);
            CookieHelper.create(httpServletResponse, cookieName, token, false, -1, host);
            LOGGER.info("{}", userdetails);
            return ResponseEntity.ok(new ResponseDTO<>(true, "Login Successful", 200));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(false, "Invalid Credentials", 400));
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {

        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        Cookie cookie = new Cookie("token", token);
        ResponseEntity.ok(new AuthenticationResponse(token));
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        return new HashMap<String, Object>(claims);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATIVE','ROLE_TEACHER','ROLE_PARENT')")
    @GetMapping("/details")
    public ResponseEntity<Object> getUserDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Optional<UserDTO> user = Optional.ofNullable(userService.getUserByUsername(username));
        return ResponseEntity.ok(new ResponseDTO<>(user.orElse(null), "Details Retrieved Successfully", 200));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATIVE','ROLE_TEACHER','ROLE_PARENT')")
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse httpServletResponse) {
        CookieHelper.clear(httpServletResponse, cookieName, host);
        return ResponseEntity.ok("Sesion cerrada");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATIVE','ROLE_TEACHER','ROLE_PARENT')")
    @GetMapping("/role")
    public ResponseEntity<ResponseDTO<Object>> getUserRoleDetails(@RequestParam String role) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            UserDetailsDTO user = userService.getUserRoleDetails(username, role);
            return ResponseEntity.ok(new ResponseDTO<>(user, "Details Retrieved Successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

}

