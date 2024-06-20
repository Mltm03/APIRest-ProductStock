package com.example.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springboot.di.app.springbootdi.models.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private List <Product> data;

    public ProductRepositoryImpl() {
        this.data=Arrays.asList(
            new Product(1L, "Memoria corsair", 300L),
            new Product(2L, "Placa gráfica asus", 850L),
            new Product(3L, "Procesador intel core i7", 180L),
            new Product(4L, "Teclado logitech g915", 490L)
        
        );
    }

    @Override // override solo indica que es un metodo "sobreescrito"
    // se le dice asi porque viene de la interfaz en este caso ProductRepository 
    public List <Product> findAll(){
        return data; 
    }
    
    @Override
    public Product findById (Long id){
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
    
}

