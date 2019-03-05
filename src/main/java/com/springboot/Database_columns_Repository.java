package com.springboot;

import org.springframework.data.repository.CrudRepository;

import com.springboot.Database_columns;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface Database_columns_Repository extends CrudRepository<Database_columns, Integer> {

}
