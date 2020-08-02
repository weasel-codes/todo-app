package co.nitin.todo.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.nitin.todo.model.entity.UserRole;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Integer>{

    @Query("select a.role from UserRole a, User b where b.mobile=?1 and a.user.userId=b.userId")
    public List<String> findRoleByUserMobile(Long mobile);
}
