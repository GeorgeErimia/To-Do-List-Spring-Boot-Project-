package com.georgeerimia.todoproject.Repository;

import com.georgeerimia.todoproject.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
