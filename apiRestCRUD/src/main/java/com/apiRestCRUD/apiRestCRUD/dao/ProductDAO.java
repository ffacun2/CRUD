package com.apiRestCRUD.apiRestCRUD.dao;

import com.apiRestCRUD.apiRestCRUD.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Producto, Long> {

}
