package com.apiRestCRUD.apiRestCRUD.controller;

import com.apiRestCRUD.apiRestCRUD.dao.ProductDAO;
import com.apiRestCRUD.apiRestCRUD.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping
    public List<Producto> getAllProducts(){
        return productDAO.findAll();
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto product){
        return productDAO.save(product);
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id){
        return productDAO.findById(id).orElse(null);
    }

    @PutMapping
    public Producto updateProduct(@PathVariable Long id,@RequestBody Producto product){
        Producto p = productDAO.findById(id).orElse(null);

        product.setNombre(p.getNombre());
        product.setPrecio(p.getPrecio());
        return productDAO.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        Producto p = productDAO.findById(id).orElseThrow(() ->  new RuntimeException());
        productDAO.delete(p);
    }
}
