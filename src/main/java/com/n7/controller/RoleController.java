package com.n7.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class RoleController {
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/roles")
    public ResponseEntity<?> getAllRole() {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "role")
    public ResponseEntity<?> creatRole(){
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "role")
    public ResponseEntity<?> updateRole() {
        return ResponseEntity.ok().body(null);
    }
}
