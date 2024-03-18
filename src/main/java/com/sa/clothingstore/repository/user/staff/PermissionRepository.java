package com.sa.clothingstore.repository.user.staff;

import com.sa.clothingstore.model.user.staff.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
