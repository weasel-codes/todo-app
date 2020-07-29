package co.nitin.todo.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.nitin.todo.model.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{
}
